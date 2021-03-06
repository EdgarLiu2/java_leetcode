package edgar.leetcode;

public class ListNode {
	int val;
	ListNode next;
	
	public ListNode() {
		
	}

	public ListNode(int x) {
		val = x;
	}
	
	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
	
	public static ListNode buildList(int[] values) {
		ListNode head = new ListNode(0);		
		ListNode pNode = head;
		
		for (int i = 0; i < values.length; i++) {
			ListNode node = new ListNode(values[i]);
			pNode.next = node;
			pNode = node;
		}
		
		return head.next;
	}
	
	public void print() {
		ListNode.print(this);
	}
	
	public static void print(ListNode head) {
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
