package edgar.leetcode;

import java.util.Objects;

/**
 * @author Edgar.Liu
 */
public class ListNode {
	public int val;
	public ListNode next;
	
	public ListNode() {
		
	}

	public ListNode(int x) {
		val = x;
	}
	
	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ListNode listNode = (ListNode) o;
		return val == listNode.val && Objects.equals(next, listNode.next);
	}

	@Override
	public int hashCode() {
		return Objects.hash(val, next);
	}

	public static ListNode buildList(int[] values) {
		ListNode head = new ListNode(0);
		ListNode pNode = head;

        for (int value : values) {
            ListNode node = new ListNode(value);
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
			buffer.append(head.val);
		}
		
		while(head.next != null) {
			buffer.append(" -> ");
			buffer.append(head.next.val);
			head = head.next;
		}
		
		System.out.println(buffer);
	}
}
