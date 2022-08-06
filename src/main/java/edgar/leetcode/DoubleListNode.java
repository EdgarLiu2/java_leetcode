package edgar.leetcode;

public class DoubleListNode<T> {
	public T val;
	public DoubleListNode next;
	public DoubleListNode previous;

	public DoubleListNode() {

	}

	public DoubleListNode(T x) {
		val = x;
	}

	public DoubleListNode(T val, DoubleListNode next, DoubleListNode previous) {
		this.val = val;
		this.next = next;
		this.previous = previous;
	}
	
	public void print() {
		DoubleListNode.print(this);
	}
	
	public static void print(DoubleListNode head) {
		StringBuilder buffer = new StringBuilder();
		
		if(head == null) {
			return;
		} else {
			buffer.append(String.valueOf(head.val));
		}
		
		while(head.next != null) {
			buffer.append(" -> " + head.next.val);
			head = head.next;
		}
		
		System.out.println(buffer);
	}
}
