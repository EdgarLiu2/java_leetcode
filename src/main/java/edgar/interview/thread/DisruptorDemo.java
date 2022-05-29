package edgar.interview.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import edgar.util.SleepUtil;

public class DisruptorDemo {
	
	private final static Logger logger = LoggerFactory.getLogger(DisruptorDemo.class);
	public final static int BUFFER_SIZE = 1024;
	
	static void disruptorDemo1() {
		LongEventFactory factory = new LongEventFactory();
		
		// Construct the Disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<>(factory, BUFFER_SIZE, Executors.defaultThreadFactory());
		
		// Connect the handler
		disruptor.handleEventsWith(new LongEventHandler());
		
		// Start the Disruptor
		RingBuffer<LongEvent> ringBuffer = disruptor.start();
		
		// 官方示例
		// Grab the next sequence
		long sequence = ringBuffer.next();
		try {
			// Get the entry in the Disruptor
			LongEvent event = ringBuffer.get(sequence);
			
			// fill with data
			event.setValue(8888);
		} finally {
			ringBuffer.publish(sequence);
		}
		
		SleepUtil.secondSleep(1);
		disruptor.shutdown();
	}
	
	static void disruptorDemo2() {
		LongEventFactory factory = new LongEventFactory();
		
		// Construct the Disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<>(factory, BUFFER_SIZE, Executors.defaultThreadFactory());
		
		// Connect the handler
		disruptor.handleEventsWith(new LongEventHandler());
		
		// Start the Disruptor
		RingBuffer<LongEvent> ringBuffer = disruptor.start();
		
		// EventTranslator 无参数
		EventTranslator<LongEvent> translator1 = new EventTranslator<>() {

			@Override
			public void translateTo(LongEvent event, long sequence) {
				event.setValue(1111);
			}
			
		};
		ringBuffer.publishEvent(translator1);
		
		// EventTranslator 1个参数
		EventTranslatorOneArg<LongEvent, Long> translator2 = (event, sequence, arg1) -> event.setValue(arg1);
		ringBuffer.publishEvent(translator2, 2222L);
		
		// EventTranslator 2个参数
		EventTranslatorTwoArg<LongEvent, Long, Long> translator3 = (event, sequence, arg1, arg2) -> event.setValue(arg1 + arg2);
		ringBuffer.publishEvent(translator3, 2222L, 1111L);
		
		// EventTranslator 3个参数
		EventTranslatorThreeArg<LongEvent, Long, Long, Long> translator4 = (event, sequence, arg1, arg2, arg3) -> event.setValue(arg1 + arg2 + arg3);
		ringBuffer.publishEvent(translator4, 2222L, 1111L, 1111L);
		
		SleepUtil.secondSleep(1);
		disruptor.shutdown();
	}
	
	static void disruptorDemo3() {
		LongEventFactory factory = new LongEventFactory();
		
		/*
		 * Construct the Disruptor
		 * 
		 * ProducerType.SINGLE Producer线程不安全
		 * ProducerType.MULTI Producer线程安全
		 * 
		 * 等待策略：
		 * YieldWaitStrategy: 常用，尝试100次，然后Thread.yield()让出CPU
		 * SleepingWaitStrategy: 常用，sleep
		 * BlockingWaitStrategy: 常用，通过线程阻塞方式，等待生产者唤醒
		 * TimeoutBlockingWaitStrategy: 不常用，通过线程阻塞方式。设置了等待时间，超时后会抛异常
		 * LiteBlockingWaitStrategy: 不常用，通过线程阻塞方式
		 * LiteTimeoutBlockingWaitStrategy: 不常用，通过线程阻塞方式。设置了等待时间，超时后会抛异常
		 * BusySpinWaitStrategy: 不常用，线程自旋等待，浪费CPU
		 * PhasedBackoffWaitStrategy: 不常用，根据时间参数和传入的等待策略决定使用哪种等待策略
		 * 
		 */
		Disruptor<LongEvent> disruptor = new Disruptor<>(factory, BUFFER_SIZE, Executors.defaultThreadFactory(),
				ProducerType.MULTI, new BlockingWaitStrategy());
		
		// Connect the handler
		EventHandler<LongEvent> eventHandler = new LongEventHandler();
		disruptor.handleEventsWith(eventHandler);
		
		// 异常处理
		disruptor.handleExceptionsFor(eventHandler).with(new ExceptionHandler<LongEvent>() {

			@Override
			public void handleEventException(Throwable ex, long sequence, LongEvent event) {
				String msg = String.format("disruptor.handleEventException sequence={} event={}", sequence, event);
				logger.error(msg, ex);
			}

			@Override
			public void handleOnStartException(Throwable ex) {
				logger.error("disruptor.handleOnStartException", ex);
			}

			@Override
			public void handleOnShutdownException(Throwable ex) {
				logger.error("disruptor.handleOnShutdownException", ex);
			}
		});
		
		// Start the Disruptor
		RingBuffer<LongEvent> ringBuffer = disruptor.start();
		
		final int THREAD_COUNT = 50;
		CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT);
		ExecutorService service = Executors.newCachedThreadPool();
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			final int threadNum = i;
			
			service.submit(() -> {
				logger.info("threadId={}, is ready to start.", threadNum);
				
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					logger.error("CyclicBarrier is interrupted", e);
				}
				
				// 发送100个事件
				for (int j = 0; j < 100; j++) {
					ringBuffer.publishEvent((event, sequence, arg1, arg2) -> event.setValue(arg1 * 100 + arg2),
							threadNum, j);
				}
			});
		}

		SleepUtil.secondSleep(2);
		service.shutdown();
		disruptor.shutdown();
		logger.info("LongEventHandler.count) = {}", LongEventHandler.count);
	}

	public static void main(String[] args) {
		disruptorDemo1();
		disruptorDemo2();
		disruptorDemo3();
	}

}

/**
 * Disruptor中每个消息都是一个事件
 * @author liuzhao
 *
 */
class LongEvent {
	private long value;

	/**
	 * @return the value
	 */
	public long getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LongEvent [value=" + value + "]";
	}
	
}

/**
 * Event工厂，用于填充队列
 * 
 * @author liuzhao
 *
 */
class LongEventFactory implements EventFactory<LongEvent> {

	@Override
	public LongEvent newInstance() {
		return new LongEvent();
	}
	
}

/**
 * EventHandler消费者，处理容器中的元素
 * 
 * @author liuzhao
 *
 */
class LongEventHandler implements EventHandler<LongEvent> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static AtomicLong count = new AtomicLong();

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		count.incrementAndGet();
		
		logger.info("event={} sequence={}", event, sequence);
	}
	
}