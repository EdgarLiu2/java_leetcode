package edgar.interview.thread;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReference1 {
	
	static AtomicMarkableReference<Order> orderRef = new AtomicMarkableReference<>(new Order(), false);

	public static void main(String[] args) {

		for(int i = 0; i < 100; i++) {
			new Thread(() -> {
				Order old = orderRef.getReference();
				
				Order newOrder = new Order();
				newOrder.sequence = old.sequence + 1;
				newOrder.time = System.currentTimeMillis();
				
				orderRef.compareAndSet(old, newOrder, false, true);
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
