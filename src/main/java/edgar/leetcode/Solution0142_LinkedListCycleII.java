package edgar.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/">142. 环形链表 II</a>
 * Created by Edgar.Liu on 2022/7/31
 */
public class Solution0142_LinkedListCycleII {
    /**
     * 使用快慢指针算法
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // fast和slow持续向后走，直到列表尾，或者fast与slow重合
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                // fast追上slow，有环
                break;
            }
        }

        if (fast == null || fast.next == null) {
            // 没有环，退出
            return null;
        }

        // fast跟slow重合，肯定有环
        // fast回到head，并和slow以相同速度移动，再次重合时候就是环的入口
        fast = head;
        while (fast != slow) {
            // slow和ptr同步向前走，当重合时，ptr指向出口
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    /**
     * 使用Set，将遍历过的节点保存下来，遇到之前访问过的节点，就说明有环
     */
    public static ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        Set<ListNode> set = new HashSet<>();
        ListNode p = head;

        while (p != null) {
            if (set.contains(p)) {
                // 找到重复节点，返回
                return p;
            }

            set.add(p);
            p = p.next;
        }

        return null;
    }

    public static void main(String[] args) {

        int[] inputs;
        ListNode head;

        /*
         * 输入：head = [3,2,0,-4], pos = 1
         * 输出：返回索引为 1 的链表节点
         * 解释：链表中有一个环，其尾部连接到第二个节点。
         */
        inputs = new int[]{3, 2, 0, -4};
        head = ListNode.buildCycleList(inputs, 1);
        assert 2 == detectCycle(head).val;


        /*
         * 输入：head = [1,2], pos = 0
         * 输出：返回索引为 0 的链表节点
         * 解释：链表中有一个环，其尾部连接到第一个节点。
         */
        inputs = new int[]{1, 2};
        head = ListNode.buildCycleList(inputs, 0);
        assert 1 == detectCycle(head).val;

        /*
         * 输入：head = [1,2], pos = -1
         * 输出：返回索引为 0 的链表节点
         * 解释：链表中有一个环，其尾部连接到第一个节点。
         */
        inputs = new int[]{1, 2};
        head = ListNode.buildCycleList(inputs, -1);
        assert detectCycle(head) == null;


        /*
         * 输入：head = [1], pos = -1
         * 输出：返回 null
         * 解释：链表中没有环。
         */
        inputs = new int[]{1};
        head = ListNode.buildCycleList(inputs, -1);
        assert detectCycle(head) == null;
    }
}
