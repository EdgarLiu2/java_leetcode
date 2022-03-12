package edgar.leetcode;

/**
 * 92. 反转链表 II
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * 
 * @author liuzhao
 *
 */
public class Solution0092_ReverseLinkedListII {
	
	public static ListNode reverseBetween(ListNode head, int left, int right) {
		
		if (left == right) {
			return head;
		}
		
		// 头节点
		ListNode newHead = new ListNode(0, head);
		
		// 反转区前一个节点
		ListNode preNode = newHead;
		
		// 向前走left - 1步，走到left节点前
		for (int i = 0; i < left - 1; i++) {
			preNode = preNode.next;
		}

		
		// 需要反转right - left个节点
		ListNode current = preNode.next;
		ListNode next = null;
		for (int i = 0; i < right - left; i++) {
			next = current.next;
			current.next = next.next;
			next.next = preNode.next;
			preNode.next = next;
		}

		return newHead.next;
    }

	public static void main(String[] args) {
		int[] input;
		ListNode list;
		ListNode reversedList;

		/*
		 * 输入：head = [1,2,3,4,5], left = 2, right = 4
		 * 输出：[1,4,3,2,5]
		 */
		input = new int[]{1,2,3,4,5};
		list = ListNode.buildList(input);
		reversedList = reverseBetween(list, 2, 4);
		reversedList.print();
		
		/*
		 * 输入：head = [5], left = 1, right = 1
		 * 输出：[5]
		 */
		input = new int[]{5};
		list = ListNode.buildList(input);
		reversedList = reverseBetween(list, 1, 1);
		reversedList.print();
		
		/*
		 * 输入：head = [3,5], left = 1, right = 2
		 * 输出：[5,3]
		 */
		input = new int[]{3,5};
		list = ListNode.buildList(input);
		reversedList = reverseBetween(list, 1, 2);
		reversedList.print();
	}

}
