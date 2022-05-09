package edgar.interview.jvm;

import java.lang.ref.WeakReference;

import edgar.util.SleepUtil;

public class WeakReferenceDemo {

	private static void testSoftReference() {
		WeakReference<Integer> m = new WeakReference<>(10);
		System.out.println(m.get());
		
		// GC后弱引用就会被释放
		System.gc();
		SleepUtil.secondSleep(1);
		System.out.println(m.get());
	}
	
	public static void main(String[] args) {
		testSoftReference();
	}

}
