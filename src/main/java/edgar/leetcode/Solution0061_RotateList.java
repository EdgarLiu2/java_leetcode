package edgar.leetcode;

/**
 * 61. 旋转链表
 * https://leetcode-cn.com/problems/rotate-list/
 * 
 * @author Administrator
 *
 */
public class Solution0061_RotateList {
	
	public static ListNode rotateRight(ListNode head, int k) {
		
		if (k == 0 || head == null || head.next == null) {
			return head;
		}
		
		// 新的头节点
		ListNode newHead = head;
		// 老的尾节点
		ListNode oldTail = null;
		
		// 计算数组长度
		int length = 0;
		while (newHead != null) {
			length++;
			oldTail = newHead;
			newHead = newHead.next;
		}
		
		// 实际移动k mod length
		k = k % length;
		
		if (k == 0) {
			// 不需要移动
			return head;
		}
		
		// 需要找到 length - k 个node，新的尾节点
		ListNode newTail = head;
		for (int i = 1; i < length - k; i++) {
			newTail = newTail.next;
		}
		
		// newTail的下一个节点就是新头节点
		newHead = newTail.next;
		// 老的尾节点指向老的头节点
		oldTail.next = head;
		// 新的尾节点指向null
		newTail.next = null;
		
		return newHead;
    }

	public static void main(String[] args) {
		int[] input;
		ListNode list;

		/*
		 * 输入: 1->2->3->4->5->NULL, k = 2
		 * 输出: 4->5->1->2->3->NULL
		 */
		input = new int[]{1, 2, 3, 4, 5};
		list = ListNode.buildList(input);
		ListNode newHeadListNode = rotateRight(list, 2);
		newHeadListNode.print();
		
		
		/*
		 * 输入: 0->1->2->NULL, k = 4
		 * 输出: 2->0->1->NULL
		 */
		input = new int[]{0, 1, 2};
		list = ListNode.buildList(input);
		newHeadListNode = rotateRight(list, 4);
		newHeadListNode.print();
		
		
		/*
		 * 输入: 1->2->NULL, k = 2
		 * 输出: 1->2->NULL
		 */
		input = new int[]{1, 2};
		list = ListNode.buildList(input);
		newHeadListNode = rotateRight(list, 2);
		newHeadListNode.print();
	}

}
