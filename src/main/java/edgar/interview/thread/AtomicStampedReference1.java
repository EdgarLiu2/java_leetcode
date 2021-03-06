package edgar.interview.thread;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReference1 {
	
	static AtomicStampedReference<Order> orderRef = new AtomicStampedReference<>(new Order(), 0);

	public static void main(String[] args) {

		for(int i = 0; i < 100; i++) {
			new Thread(() -> {
				Order old = orderRef.getReference();
				int stamp = orderRef.getStamp();
				
				Order newOrder = new Order();
				newOrder.sequence = old.sequence + 1;
				newOrder.time = System.currentTimeMillis();
				
				orderRef.compareAndSet(old, newOrder, stamp, stamp + 1);
			}).start();
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(orderRef.getReference());
	}

}

class Order {
	long sequence;
	long time;
	
	@Override
	public String toString() {
		return "Order [sequence=" + sequence + ", time=" + time + "]";
	}
	
}