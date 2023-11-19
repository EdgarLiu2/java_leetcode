package edgar.interview;

import edgar.leetcode.ListNode;

/**
 * Description: 将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 * @author Edgar.Liu
 * @since 2023/11/18 - 17:36
 */
public class ListPartition {

    static ListNode partition(ListNode head, int pivot) {
        // 小于区的头尾指针
        ListNode smallHead = new ListNode(-1);
        ListNode smallTail = smallHead;

        // 等于区的头尾指针
        ListNode equalHead = new ListNode(-1);
        ListNode equalTail = equalHead;

        // 大于区的头尾指针
        ListNode bigHead = new ListNode(-1);
        ListNode bigTail = bigHead;

        while (head != null) {
            // 准备移动第一个节点
            ListNode current = head;
            head = head.next;
            current.next = null;

            if (current.val < pivot) {
                // 放在小于区
                smallTail.next = current;
                smallTail = smallTail.next;
            } else if (current.val == pivot) {
                // 放在等于区
                equalTail.next = current;
                equalTail = equalTail.next;
            } else {
                // 放在大于区
                bigTail.next = current;
                bigTail = bigTail.next;
            }
        }

        // 小于区的尾节点，指向等于区的头节点后的第一个元素
        smallTail.next = equalHead.next;

        // 等于区的尾节点，指向大于区的头节点后的第一个元素
        if (equalHead != equalTail) {
            equalTail.next = bigHead.next;
        } else {
            // 等于区为空，小于区的尾节点指向大于区的头节点后的第一个元素
            smallTail.next = bigHead.next;
        }

        return smallHead.next;
    }

    public static void main(String[] args) {
        int[] input;
        ListNode head;

        /*
         * 三个区都有
         * 输入：head = [4,2,3,5,6,1,3,0], x = 3
         */
        input = new int[]{4, 2, 3, 5, 6, 1, 3, 0};
        head = ListNode.buildList(input);
        ListNode.print(head);
        head = partition(head, 3);
        ListNode.print(head);

        /*
         * 没有小于区
         * 输入：head = [4,3,5,6,3], x = 3
         */
        input = new int[]{4, 3, 5, 6, 3};
        head = ListNode.buildList(input);
        ListNode.print(head);
        head = partition(head, 3);
        ListNode.print(head);

        /*
         * 没有等于区
         * 输入：head = [4,2,5,6,1,0], x = 3
         */
        input = new int[]{4, 2, 5, 6, 1, 0};
        head = ListNode.buildList(input);
        ListNode.print(head);
        head = partition(head, 3);
        ListNode.print(head);

        /*
         * 没有大于区
         * 输入：head = [2,3,1,3,0], x = 3
         */
        input = new int[]{2, 3, 1, 3, 0};
        head = ListNode.buildList(input);
        ListNode.print(head);
        head = partition(head, 3);
        ListNode.print(head);

        /*
         * 没有小于和等于区
         * 输入：head = [4,5,6], x = 3
         */
        input = new int[]{4, 5, 6};
        head = ListNode.buildList(input);
        ListNode.print(head);
        head = partition(head, 3);
        ListNode.print(head);
    }
}
