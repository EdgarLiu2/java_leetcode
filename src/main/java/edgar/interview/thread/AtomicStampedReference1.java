package edgar.interview.thread;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReference1 {
	
	static AtomicStampedReference<Order> orderRef = new AtomicStampedReference<>(new Order(), 0);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 20201128_马老师【多线程训练营】 54:26

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