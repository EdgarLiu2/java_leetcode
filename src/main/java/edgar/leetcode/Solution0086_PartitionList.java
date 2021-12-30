package edgar.leetcode;

/**
 * 86. 分隔链表
 * https://leetcode-cn.com/problems/partition-list/
 * 
 * @author liuzhao
 *
 */
public class Solution0086_PartitionList {
	
	public static ListNode partition(ListNode head, int x) {
		
		if (head == null) {
			return null;
		}
		
		ListNode smallHead = new ListNode(0);
		ListNode small = smallHead;
		ListNode largeHead = new ListNode(0);
		ListNode large = largeHead;
		
		while (head != null) {
			
			if (head.val < x) {
				// 当前节点放到小链表
				small.next = head;
				small = small.next;
			} else {
				// 当前节点放到大链表
				large.next = head;
				large = large.next;
			}
			
			head = head.next;
		}
		
		// 两个链表串起来
		small.next = largeHead.next;
		large.next = null;
		
		return smallHead.next;
    }

	public static void main(String[] args) {
		int[] input;
		ListNode head;

		/*
		 * 输入：head = [1,4,3,2,5,2], x = 3
		 * 输出：[1,2,2,4,3,5]
		 */
		input = new int[]{1, 4, 3, 2 ,5, 2};
		head = ListNode.buildList(input);
		ListNode.print(head);
		head = partition(head, 3);
		ListNode.print(head);
		
		/*
		 * 输入：head = [2,1], x = 2
		 * 输出：[1,2]
		 */
		input = new int[]{1, 2};
		head = ListNode.buildList(input);
		ListNode.print(head);
		head = partition(head, 2);
		ListNode.print(head);
	}

}
