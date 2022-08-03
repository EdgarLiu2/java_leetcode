package edgar.leetcode.offer;

import edgar.leetcode.ListNode;

/**
 * 面试题 02.05. 链表求和
 * https://leetcode.cn/problems/sum-lists-lcci/
 *
 * Created by liuzhao on 2022/8/3
 */
public class Interview0205_SumLists {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 保存结果链表
        ListNode result = new ListNode(0);
        ListNode p = result;
        // 进位
        int carry = 0;

        // 两个指针同步向后移动，当都有值时求和保存到result。进位部分保存到carry
        while (l1 != null || l2 != null) {
            int l1Val = (l1 != null) ? l1.val : 0;
            int l2Val = (l2 != null) ? l2.val : 0;
            int add = l1Val + l2Val + carry;
            carry = add / 10;

            ListNode newNode = new ListNode(add % 10);
            p.next = newNode;
            p = p.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            p.next = newNode;
        }

        return result.next;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 保存结果链表
        ListNode result = new ListNode(0);
        ListNode p = result;
        // 进位
        int carry = 0;

        // 两个指针同步向后移动，当都有值时求和保存到result。进位部分保存到carry
        while (l1 != null && l2 != null) {
            int add = l1.val + l2.val + carry;
            carry = add / 10;

            ListNode newNode = new ListNode(add % 10);
            p.next = newNode;
            p = p.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        // l1 或 l2可能还有没出来完的节点
        ListNode l3 = l1;
        if (l2 != null) {
            l3 = l2;
        }

        // l1 和 l2 有一个不为空，变量l3剩余节点
        while (l3 != null) {
            int add = l3.val + carry;
            carry = add / 10;

            ListNode newNode = new ListNode(add % 10);
            p.next = newNode;
            p = p.next;

            l3 = l3.next;
        }

        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            p.next = newNode;
        }

        return result.next;
    }

    public static void main(String[] args) {
        int[] input1, input2;
        ListNode list1, list2;

        /**
         * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
         * 输出：2 -> 1 -> 9，即912
         */
        input1 = new int[]{7, 1, 6};
        input2 = new int[]{5, 9, 2};
        list1 = ListNode.buildList(input1);
        list2 = ListNode.buildList(input2);
        addTwoNumbers(list1, list2).print();

        /**
         * 输入：(1) + (9 -> 9)，即1 + 99
         * 输出：0 -> 0 -> 1，即100
         */
        input1 = new int[]{1};
        input2 = new int[]{9, 9};
        list1 = ListNode.buildList(input1);
        list2 = ListNode.buildList(input2);
        addTwoNumbers(list1, list2).print();
    }
}
