package edgar.leetcode;

/**
 * 82. 删除排序链表中的重复元素 II
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 * 
 * @author liuzhao
 *
 */
public class Solution0082_RemoveDuplicatesFromSortedListII {
	
	public static ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode newHead = new ListNode(0, head);
		
		// 指向当前值前一个节点
		ListNode left = newHead;
		// 指向当前校验的节点
		ListNode right = newHead.next;
		int current = right.val;
		
		while (left.next != null) {
			
			if (right.next == null) {
				// right后面没有节点了，结束
				break;
			}
			
			// 当前节点的下一个节点值不同，都往后移动
			if (current != right.next.val) {
				current = right.next.val;
				left = right;
				right = right.next;
			} else {
				// right一直往后移动，直到right节点的值和current不同
				while (current == right.val) {
					right = right.next;
					
					// 后面已经没有节点了
					if (right == null) {
						break;
					}
				}
				
				// 删除left和right之间的原始
				left.next = right;
				
				// 后面已经没有节点了
				if (right == null) {
					break;
				} else {
					current = right.val;
				}
			}
		}

		return newHead.next;
    }

	public static void main(String[] args) {
		int[] input;
		ListNode head;

		/*
		 * 输入：head = [1,2,3,3,4,4,5]
		 * 输出：[1,2,5]
		 */
		input = new int[]{1, 2, 3, 3, 4, 4, 5};
		head = ListNode.buildList(input);
		ListNode.print(head);
		head = deleteDuplicates(head);
		ListNode.print(head);
		
		/*
		 * 输入：head = [1,1,1,2,3]
		 * 输出：[2,3]
		 */
		input = new int[]{1, 1, 1, 2, 3};
		head = ListNode.buildList(input);
		ListNode.print(head);
		head = deleteDuplicates(head);
		ListNode.print(head);
	}

}
