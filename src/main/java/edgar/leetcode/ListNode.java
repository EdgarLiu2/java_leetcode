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
	
	public static ListNode buildCycleList(int[] values, int pos) {
		ListNode head = buildList(values);
		
		if (pos == -1) {
			// 链尾节点不指向其它节点
			return head;
		}
		
		// 找到第pos个节点
		ListNode posNode = head;
		while (pos > 0) {
			posNode = posNode.next;
			pos--;
		}
		
		// 找到尾节点
		ListNode tailNode = posNode;
		while (tailNode.next != null) {
			tailNode= tailNode.next;
		}
		
		tailNode.next = posNode;
		
		return head;
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
