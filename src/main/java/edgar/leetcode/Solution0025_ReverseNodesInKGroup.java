package edgar.leetcode;

/**
 * 25. K 个一组翻转链表
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * 
 * @author Administrator
 *
 */
public class Solution0025_ReverseNodesInKGroup {
	
	public static ListNode reverse(ListNode head) {
		ListNode newHead = null;
		ListNode tmpNode;
		
		while (head != null) {
			tmpNode = head;
			head = head.next;
			
			tmpNode.next = newHead;
			newHead = tmpNode;
		}
		
		return newHead;
	}
	
	public static ListNode reverseKGroup(ListNode head, int k) {
		
		if (k == 1 || head == null) {
			return head;
		}
		
		ListNode preHead = head;
		
		// 从head找第k个节点
		ListNode kthNode = head;
		for (int i = 0; i < k - 1; i++) {
			if (kthNode.next == null) {
				return head;
			} else {
				kthNode = kthNode.next;
			}
		}
		
		// 第k个节点后面的节点
		ListNode restNodes = kthNode.next;
		
		// 将head到kthNode进行翻转
		kthNode.next = null;
		head = reverse(head);
		
		// 将剩余节点翻转后挂在原来头节点后面
		preHead.next = reverseKGroup(restNodes, k);
		
		return head;
    }

	public static void main(String[] args) {
		int[] input;
		ListNode list;

		/*
		 * 输入：1->2->3->4->5
		 * 当 k = 2 时，应当返回: 2->1->4->3->5
		 * 当 k = 3 时，应当返回: 3->2->1->4->5
		 */
		input = new int[]{1, 2, 3, 4, 5};
		list = ListNode.buildList(input);
		ListNode reversedList =  reverseKGroup(list, 2);
		reversedList.print();
		
		list = ListNode.buildList(input);
		reversedList =  reverseKGroup(list, 3);
		reversedList.print();
	}

}
