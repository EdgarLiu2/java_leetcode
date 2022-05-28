package edgar.try_new.jmh;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


@Warmup(iterations = 1, time = 3) // 预热
@Fork(5) // 几个并发进行测试
@BenchmarkMode(Mode.Throughput) // 基准测试模式
@Measurement(iterations = 2, time = 3) // 测试2遍，调用方法3次
public class JMHTest {

	@Benchmark
	public void testStream() {
		JMHDemo.stream();
	}

	@Benchmark
	public void testParallelStream() {
		JMHDemo.parallelStream();
		;
	}

	public static void main(String... args) throws IOException, RunnerException {
//		Main.main(args);
		Options options = new OptionsBuilder().include(JMHTest.class.getSimpleName()).build();
		new Runner(options).run();
	}
}
