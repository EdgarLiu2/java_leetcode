package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle/">141. 环形链表</a>
 * Created by Edgar.Liu on 2023/2/6
 */
public class Solution0141_LinkedListCycle {

	public static boolean hasCycle(ListNode head) {
		
		// 快指针每次走两步
		ListNode fast = head;
		// 慢指针每次走一步
		ListNode slow = head;
		
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if (slow == fast) {
				// 快指针和慢指针重合
				return true;
			}
		}
        
		return false;
    }

	public static void main(String[] args) {
		int[] inputs;
		ListNode head;

		/*
		 * 输入：head = [3,2,0,-4], pos = 1
		 * 输出：true
		 */
		inputs = new int[]{3, 2, 0, -4};
		head = ListNode.buildCycleList(inputs, 1);
		assert(hasCycle(head));
		
		/*
		 * 输入：head = [1,2], pos = 0
		 * 输出：true
		 */
		inputs = new int[]{1, 2};
		head = ListNode.buildCycleList(inputs, 0);
		assert(hasCycle(head));
		
		/*
		 * 输入：head = [1], pos = -1
		 * 输出：true
		 */
		inputs = new int[]{1};
		head = ListNode.buildCycleList(inputs, -1);
		assert(!hasCycle(head));
	}

}
