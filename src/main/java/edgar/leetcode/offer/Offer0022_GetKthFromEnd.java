package edgar.leetcode.offer;

import edgar.leetcode.ListNode;

/**
 * <a href="https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/">剑指 Offer 22. 链表中倒数第k个节点</a>
 * Created by Edgar.Liu on 2023/2/11
 */
public class Offer0022_GetKthFromEnd {
    static ListNode getKthFromEnd(ListNode head, int k) {
        // 处理异常情况
        if (head == null || k <= 0) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        // fast 指针，先走 k-1 步。如果已经到链表尾部，则直接返回null
        for (int i = 0; i < k - 1; i++) {
            if (fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }

        if (fast == null) {
            return null;
        }

        // fast 和 slow，同时向后走，当fast到结尾时，slow指向所要节点
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] input;
        ListNode head;

        /*
         * 给定一个链表: 1->2->3->4->5, 和 k = 2.
         * 返回链表 4->5.
         */
        input = new int[]{1, 2, 3, 4, 5};
        head = ListNode.buildList(input);
        assert 4 == getKthFromEnd(head, 2).val;

        /*
         * 给定一个链表: 1->2->3->4->5, 和 k = 6.
         * 返回 null.
         */
        input = new int[]{1, 2, 3, 4, 5};
        head = ListNode.buildList(input);
        assert null == getKthFromEnd(head, 6);
    }
}
