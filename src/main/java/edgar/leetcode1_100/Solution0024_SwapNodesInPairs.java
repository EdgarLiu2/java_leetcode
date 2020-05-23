package edgar.leetcode1_100;

/**
 * 24. 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * 
 * @author Administrator
 *
 */
public class Solution0024_SwapNodesInPairs {
	
	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode h1 = head;
		ListNode h2 = head.next;
		ListNode h3 = swapPairs(h2.next); 
		
		h2.next = h1;
		h1.next = h3;
		
		return h2;
    }

	public static void main(String[] args) {

		int[] input;
		
		/*
		 * 输入：1->2->3->4
		 * 输出：2->1->4->3
		 */
		input = new int[]{1, 2, 3, 4, 5, 6};
		ListNode list1 = ListNode.buildList(input);
		ListNode swapedList = swapPairs(list1);
		swapedList.print();
	}

}
