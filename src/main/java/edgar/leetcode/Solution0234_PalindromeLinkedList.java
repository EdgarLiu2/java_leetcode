package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/palindrome-linked-list/">234. 回文链表</a>
 * Created by Edgar.Liu on 2023/2/8
 */
public class Solution0234_PalindromeLinkedList {
    static boolean isPalindrome(ListNode head) {
        // 处理特殊情况
        if (head == null || head.next == null) {
            return true;
        }

        // 定义快慢指针
        ListNode slow = head;
        ListNode fast = head;

        // 快慢指针向后移动，直到快指针走到列表尾部
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 快指针没有执行null，说明链表有奇数个节点
        if (fast != null) {
            // 慢指针指向中间节点，再向后移动一个
            slow = slow.next;
        }

        // 将慢指针后的链表进行翻转
        slow = reverse(slow);

        // 快指针回到链表头
        fast = head;
        // 快慢指针向后移动，如果值不同就返回false，否则同时向后移动，直到慢指针走到结尾
        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }

            fast = fast.next;
            slow = slow.next;
        }

        return true;
    }

    static ListNode reverse(ListNode head) {
        ListNode newHead = new ListNode(0);

        while (head != null) {
            ListNode p = head;
            head = head.next;

            p.next = newHead.next;
            newHead.next = p;
        }

        return newHead.next;
    }

    public static void main(String[] args) {
        int[] input;
        ListNode head;

        /*
         * 输入：head = [1,2,2,1]
         * 输出：true
         */
        input = new int[]{1, 2, 2, 1};
        head = ListNode.buildList(input);
        assert isPalindrome(head);

        /*
         * 输入：head = [1,2]
         * 输出：false
         */
        input = new int[]{1, 2};
        head = ListNode.buildList(input);
        assert !isPalindrome(head);
    }
}
