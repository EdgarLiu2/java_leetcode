package edgar.leetcode;

/**
 * 
 * 两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 * 
 * @author Administrator
 *
 */
public class Solution0002_AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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

	public static void main(String[] args) {
		/**
		 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)' 输出：7 -> 0 -> 8 原因：342 + 465 = 807
		 * 
		 * [2,4,3] [5,6,4] [7,0,8]
		 * 
		 * [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
		 * [5,6,4]
		 * [6,6,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
		 * 
		 * [1,8] [0]
		 */
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}