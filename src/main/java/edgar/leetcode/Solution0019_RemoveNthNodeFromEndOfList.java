package edgar.leetcode;

/**
 * 19. 删除链表的倒数第N个节点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * 
 * @author Administrator
 *
 */
public class Solution0019_RemoveNthNodeFromEndOfList {
	
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		// 既然是需要倒数第n个，那么我们造两个指针，让快指针先行n步。
		ListNode fastNode = head;
		for (int i = 0; i < n; i++) {
			fastNode = fastNode.next;
		}
		// 直接到末尾了，说明是要删除第一个节点，直接返回next节点即可
		if (fastNode == null) {
			return head.next;
		}
		
		// 慢指针和快指针一起往前走，直到快指针到末尾
		ListNode slowNode = head;
		while (fastNode != null) {
			if (fastNode.next == null) {
				// 快指针到最后一个元素
				slowNode.next = slowNode.next.next;
				break;
			}
			
			fastNode = fastNode.next;
			slowNode = slowNode.next;
		}
		
		return head;
    }

	public static void main(String[] args) {
		int[] input;
		
		/*
		 * 输入：1->2->3->4->5, 和 n = 2.
		 * 输出：1->2->3->5
		 */
		input = new int[]{1, 2, 3, 4, 5};
		ListNode head = ListNode.buildList(input);
		ListNode.print(head);
		head = removeNthFromEnd(head, 2);
		ListNode.print(head);
	}

}

