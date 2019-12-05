package edgar.leetcode;

/**
 * 
 * 2. 两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 * 
 * @author Administrator
 *
 */
public class Solution0002_AddTwoNumbers {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode l3 = new ListNode(-1), l3p = l3;
		int carry = 0;

		while (l1 != null && l2 != null) {
			int a = l1.val + l2.val + carry;
			if (a >= 10) {
				a -= 10;
				carry = 1;
			} else {
				carry = 0;
			}
			l3p.next = new ListNode(a);
			l3p = l3p.next;
			l1 = l1.next;
			l2 = l2.next;
		}

		ListNode l4 = l1 != null ? l1 : l2;
		while (l4 != null) {
			int a = l4.val + carry;
			if (a >= 10) {
				a -= 10;
				carry = 1;
			} else {
				carry = 0;
			}
			l3p.next = new ListNode(a);
			l3p = l3p.next;
			l4 = l4.next;
		}

		if (carry == 1) {
			l3p.next = new ListNode(1);
		}

		return l3.next;
	}
	
	public static ListNode arrayToListNode(int[] nums) {
		ListNode head = new ListNode(-1);
		ListNode pNode = head;
		
		for (int i = 0; i < nums.length; i++) {
			ListNode node = new ListNode(nums[i]);
			pNode.next = node;
			pNode = node;
		}
		
		printListNode(head.next);
		
		return head.next;
	}
	
	public static void printListNode(ListNode head) {
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

	public static void main(String[] args) {
		/**
		 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)' 输出：7 -> 0 -> 8 原因：342 + 465 = 807
		 * 
		 * [2,4,3]
		 * [5,6,4]
		 * [7,0,8]
		 * 
		 * [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
		 * [5,6,4]
		 * [6,6,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
		 * 
		 * [1,8]
		 * [0]
		 * [1,8]
		 */
		int[] a1 = {2,4,3};
		int[] a2 = {5,6,4};
		ListNode a1_2 = addTwoNumbers(arrayToListNode(a1), arrayToListNode(a2));
		printListNode(a1_2);
		
		int[] b1 = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		int[] b2 = {5,6,4};
		ListNode b1_2 = addTwoNumbers(arrayToListNode(b1), arrayToListNode(b2));
		printListNode(b1_2);
		
		int[] c1 = {1,8};
		int[] c2 = {0};
		ListNode c1_2 = addTwoNumbers(arrayToListNode(c1), arrayToListNode(c2));
		printListNode(c1_2);
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}