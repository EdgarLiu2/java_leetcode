package edgar.leetcode;

/**
 * 23. 合并K个排序链表
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * 
 * @author Administrator
 *
 */
public class Solution0023_MergeKSortedLists {
	/*
	 * 分治，两两合并
	 */
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		
		return mergeKLists(lists, 0, lists.length - 1);
	}
	
	public static ListNode mergeKLists(ListNode[] lists, int start, int end) {
		if (start == end) {
			return lists[start];
		}
		
		int mid = start + (end - start) / 2;
		ListNode l1 =  mergeKLists(lists, start, mid);
		ListNode l2 =  mergeKLists(lists, mid + 1, end);
		
		return mergeTwoLists(l1, l2);
	}
	
	/**
	 * 先将0,1, 2,3, 4,5, 6,7合并，然后再合并0,2, 4,6, ... 
	 * @param lists
	 * @return
	 */
	public static ListNode mergeKLists2(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		} else if (lists.length == 1) {
			return lists[0];
		}
		
		int interval = 1;
		int length = lists.length;
		
		while (interval < length) {
			for (int i = 0; i < lists.length - interval; i += interval*2) {
				lists[i] = mergeTwoLists(lists[i], lists[i+interval]);
			}
			
			interval *= 2;
		}

		return lists[0];
    }
	
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		 if (l1 == null) {
			 return l2;
		 } else if (l2 == null) {
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


		/*
		 * 输入: [ 1->4->5, 1->3->4, 2->6 ] 
		 * 输出: 1->1->2->3->4->4->5->6
		 */
		int[] input1 = new int[]{1, 4, 5};
		int[] input2 = new int[]{1, 3, 4};
		int[] input3 = new int[]{2, 6};
		
		ListNode[] lists = new ListNode[] {
				ListNode.buildList(input1),
				ListNode.buildList(input2),
				ListNode.buildList(input3)
		};
		
		ListNode mergedList = mergeKLists(lists);
		mergedList.print();
		
		/*
		 * 输入: [[],[-1,5,11],[],[6,10]]
		 * 输出: 1->1->2->3->4->4->5->6
		 */
		input1 = new int[]{};
		input2 = new int[]{-1,5,11};
		input3 = new int[]{6,10};
		
		lists = new ListNode[] {
				ListNode.buildList(input1),
				ListNode.buildList(input2),
				ListNode.buildList(input1),
				ListNode.buildList(input3)
		};
		
		mergedList = mergeKLists(lists);
		mergedList.print();
	}

}
