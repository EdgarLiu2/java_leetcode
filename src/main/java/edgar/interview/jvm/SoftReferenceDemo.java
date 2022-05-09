package edgar.interview.jvm;

import java.lang.ref.SoftReference;

import edgar.util.SleepUtil;

/**
 * 当对象被一个软引用指向时候，只要系统内存不够用的时候，GC就会回收它。所以非常适合再缓存中使用。
 * 
 * @author liuzhao
 *
 */
public class SoftReferenceDemo {
	
	/*
	 * -Xmx30m
	 */
	private static void testSoftReference() {
		final int MBytes = 1024 * 1024;
		
		SoftReference<byte[]> m = new SoftReference<>(new byte[10 * MBytes]);
		
		System.out.println(m.get());
		// heap有足够内存，不会释放
		System.gc();
		
		SleepUtil.secondSleep(1);
		// heap有足够内存，不会释放
		System.out.println(m.get());
		
		byte[] b = new byte[20 * MBytes];
		// heap没有足够内存，会自动释放
		System.out.println(m.get());
	}

	public static void main(String[] args) {
		testSoftReference();
	}

}
