package edgar.leetcode;

/**
 * <a href="https://leetcode-cn.com/problems/merge-two-sorted-lists/">21. 合并两个有序链表</a>
 * Created by Edgar.Liu on 2023/2/5
 */
public class Solution0021_MergeTwoSortedLists {
	 public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		 if (l1 == null) {
			 return l2;
		 }
		 if (l2 == null) {
			 return l1;
		 }
		 
		 ListNode newHead = new ListNode(0);
		 ListNode newTail = newHead;
		 
		 while (l1 != null && l2 != null) {
			 if (l1.val >= l2.val) {
				 newTail.next = l2;
				 l2 = l2.next;
			 } else {
				 newTail.next = l1;
				 l1 = l1.next;
			 }
			 
			 newTail = newTail.next;
		 }
		 
		 newTail.next = (l1 == null ? l2 : l1);
		 
		 return newHead.next;
	 }

	public static void main(String[] args) {
		int[] input1;
		int[] input2;
		
		/*
		 * 输入：1->2->4, 1->3->4
		 * 输出：1->1->2->3->4->4
		 */
		input1 = new int[]{1, 2, 4};
		ListNode l1 = ListNode.buildList(input1);
		input2 = new int[]{1, 3, 4};
		ListNode l2 = ListNode.buildList(input2);
		ListNode mergedList = mergeTwoLists(l1, l2);
		mergedList.print();

	}

}
