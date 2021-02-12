package edgar.interview.jvm;

import java.util.concurrent.TimeUnit;

import org.openjdk.jol.info.ClassLayout;

/**
 * Class Header(Markword + Class pointer) + Instance Data + Padding
 * Markword：Lock信息，GC信息，hashcode
 * 
 * Java对象的内存布局：对象头（Header）、实例数据（Instance Data）和对齐填充（Padding）。无论是32位还是64位的HotSpot，使用的都是8字节对齐。 
 * 也就是说每个java对象，占用的字节数都是8的整数倍。（对象头 + 实例数据 + padding） % 8等于0且0 <= padding < 8。
 * 1. 基本数据类型占用的字节数，JVM规范中有明确的规定，无论是在32位还是64位的虚拟机，占用的内存大小是相同的。
 * 2. reference类型在32位JVM下占用4个字节，但是在64位下可能占用4个字节或8个字节，这取决于是否启用了64位JVM的指针压缩参数UseCompressedOops。
 * 3. new Object()这个对象在32位JVM上占8个字节，在64位JVM上占16个字节。
 * 4. 开启(-XX:+UseCompressedOops)指针压缩，对象头占12字节; 关闭(-XX:-UseCompressedOops)指针压缩,对象头占16字节。
 * 5. 64位JVM上，数组对象的对象头占用24个字节，启用压缩之后占用16个字节。之所以比普通对象占用内存多是因为需要额外的空间存储数组的长度。
 * 6. 对象内存布局中的实例数据，不包括类的static字段的大小，因为static字段是属于类的，被该类的所有对象共享。
 * 
 * java -XX:+PrintCommandLineFlags -version
 * 打印所有的XX参数：
 * java -XX:+PrintFlagsFinal -version
 * -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
 * 
 * @author Administrator
 *
 */

//The markOop describes the header of an object.
//
//Note that the mark is not a real oop but just a word.
//It is placed in the oop hierarchy for historical reasons.
//
//Bit-format of an object header (most significant first, big endian layout below):
//
//32 bits:
//--------
//          hash:25 ------------>| age:4    biased_lock:1 lock:2 (normal object)
//          JavaThread*:23 epoch:2 age:4    biased_lock:1 lock:2 (biased object)
//          size:32 ------------------------------------------>| (CMS free block)
//          PromotedObject*:29 ---------->| promo_bits:3 ----->| (CMS promoted object)
//
//64 bits:
//--------
//unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
//JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
//PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
//size:64 ----------------------------------------------------->| (CMS free block)
//
//unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
//JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
//narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
//unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)
//
//- hash contains the identity hash value: largest value is
// 31 bits, see os::random().  Also, 64-bit vm's require
// a hash value no bigger than 32 bits because they will not
// properly generate a mask larger than that: see library_call.cpp
// and c1_CodePatterns_sparc.cpp.
//
//- the biased lock pattern is used to bias a lock toward a given
// thread. When this pattern is set in the low three bits, the lock
// is either biased toward a given thread or "anonymously" biased,
// indicating that it is possible for it to be biased. When the
// lock is biased toward a given thread, locking and unlocking can
// be performed by that thread without using atomic operations.
// When a lock's bias is revoked, it reverts back to the normal
// locking scheme described below.
//
// Note that we are overloading the meaning of the "unlocked" state
// of the header. Because we steal a bit from the age we can
// guarantee that the bias pattern will never be seen for a truly
// unlocked object.
//
// Note also that the biased state contains the age bits normally
// contained in the object header. Large increases in scavenge
// times were seen when these bits were absent and an arbitrary age
// assigned to all biased objects, because they tended to consume a
// significant fraction of the eden semispaces and were not
// promoted promptly, causing an increase in the amount of copying
// performed. The runtime system aligns all JavaThread* pointers to
// a very large value (currently 128 bytes (32bVM) or 256 bytes (64bVM))
// to make room for the age bits & the epoch bits (used in support of
// biased locking), and for the CMS "freeness" bit in the 64bVM (+COOPs).
//
// [JavaThread* | epoch | age | 1 | 01]       lock is biased toward given thread
// [0           | epoch | age | 1 | 01]       lock is anonymously biased
//
//- the two lock bits are used to describe three states: locked/unlocked and monitor.
//
// [ptr             | 00]  locked             ptr points to real header on stack
// [header      | 0 | 01]  unlocked           regular object header
// [ptr             | 10]  monitor            inflated lock (header is wapped out)
// [ptr             | 11]  marked             used by markSweep to mark an object
//                                            not valid at any other time
//
// We assume that stack/thread pointers have the lowest two bits cleared.

public class ObjectLayout {
	
	public static void main(String[] args) {
		
		normalObjectLayout();
		beforeAndAfterHashcode();
		beforeAndAfterSync();
		
	}
	
	public static void normalObjectLayout() {
		ClassLayout layout = null;
		
		/*
		 * 
		 *     OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
		 *           0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
		 *           4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
		 *           8     4        (object header)                           48 60 17 00 (01001000 01100000 00010111 00000000) (1531976)
		 *         12     4        (loss due to the next object alignment)
		 *     Instance size: 16 bytes
		 *     Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
		 *     
		 */
		System.out.println("T1 has 0 field");
		T1 t1 = new T1();
		layout = ClassLayout.parseInstance(t1);
		System.out.println(layout.toPrintable());
		
		System.out.println("T2 has 1 int field");
		T2 t2 = new T2();
		layout = ClassLayout.parseInstance(t2);
		System.out.println(layout.toPrintable());
		
		System.out.println("T3 has 1 int and 1 string field");
		T3 t3 = new T3();
		layout = ClassLayout.parseInstance(t3);
		System.out.println(layout.toPrintable());
	}
	
	public static void beforeAndAfterHashcode() {
		ClassLayout layout = null;
		System.out.println("Object Before & After hashcode");
		
		Object o = new Object();
		layout = ClassLayout.parseInstance(o);
		System.out.println(layout.toPrintable());
		
		o.hashCode();
		layout = ClassLayout.parseInstance(o);
		System.out.println(layout.toPrintable());

	}
	
	
	/**
	 * 偏向锁 => 轻量级锁（自旋锁）=> 重量级锁 
	 * 1. 将threadId写到Markword
	 * 2. 锁撤销，一起抢自旋锁
	 * 3. 升级重量级锁
	 */
	public static void beforeAndAfterSync() {
		ClassLayout layout = null;
		System.out.println("Object Before & After synchronized keyword ");
		
		try {
			// JDK 1.8 偏向锁启动需要4s
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		T3 o = new T3();
		layout = ClassLayout.parseInstance(o);
		/*
		 * OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
		 *       0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
		 *       
		 * 00000001，最后3位001，无锁态
		 */
		System.out.println(layout.toPrintable());
		
		synchronized (o) {

			/**
			 *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
			 *        0     4                    (object header)                           88 f3 3f 37 (10001000 11110011 00111111 00110111) (926937992)
			 *        
			 *  10001000，最后3位000，轻量级锁，自旋锁
			 *  
			 *  最后3位101，偏向锁，当前线程指针记录在Markword的前54位
			 */
			layout = ClassLayout.parseInstance(o);
			System.out.println(layout.toPrintable());
		}
	}

}

class T1 {
	
}

class T2 {
	int m;
}

class T3 {
	int m;
	String s;
}
