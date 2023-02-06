package edgar.leetcode;

/**
 * <a href="https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/">83. 删除排序链表中的重复元素</a>
 * Created by Edgar.Liu on 2023/2/6
 */
public class Solution0083_RemoveDuplicatesFromSortedList {

	public static ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pNode = head;

		while (pNode.next != null) {
			if (pNode.val == pNode.next.val) {
				// pNode和pNode.next相同，删除pNode.next
				pNode.next = pNode.next.next;
			} else {
				// pNode向后移动
				pNode = pNode.next;
			}
		}

		return head;
	}

	public static ListNode deleteDuplicates2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode firstNode = head;
		ListNode secondNode = head.next;
		
		while (firstNode != null && secondNode != null) {
			if (firstNode.val == secondNode.val) {
				// firstNode与secondNode值相同，删除secondNode
				firstNode.next = secondNode.next;
				// firstNode保持不动，secondNode向后移动
				secondNode = firstNode.next;
			} else {
				// firstNode与secondNode同时向后移动
				firstNode = firstNode.next;
				if (firstNode != null) {
					secondNode = firstNode.next;
				} else {
					break;
				}
			}
			
		}
		
		return head;
    }
	
	public static void main(String[] args) {
		int[] inputs;
		ListNode head;
		
		/*
		 * 输入：head = [1,1,2]
		 * 输出：[1,2]
		 */
		inputs = new int[]{1, 1, 2};
		head = ListNode.buildList(inputs);
		head = deleteDuplicates(head);
		head.print();

		/*
		 * 输入：head = [1,1,2,3,3]
		 * 输出：[1,2,3]
		 */
		inputs = new int[]{1, 1, 2, 3, 3};
		head = ListNode.buildList(inputs);
		head = deleteDuplicates(head);
		head.print();
		
		/*
		 * 输入：head = [1,1,1]
		 * 输出：[1,1]
		 */
		inputs = new int[]{1, 1, 1};
		head = ListNode.buildList(inputs);
		head = deleteDuplicates(head);
		head.print();
	}

}
