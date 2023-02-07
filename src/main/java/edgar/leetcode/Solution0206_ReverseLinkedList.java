package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/reverse-linked-list/">206. 反转链表</a>
 * Created by Edgar.Liu on 2022/7/31
 */
public class Solution0206_ReverseLinkedList {

    static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = new ListNode(0);

        // 将第一个节点后面的链表反转
        ListNode restList = reverseList1(head.next);
        // 把后面链表放在前面
        newHead.next = restList;
        // 找到restList的尾部
        ListNode tail = restList;
        while (tail.next != null) {
            tail = tail.next;
        }
        // 将head放在尾部
        tail.next = head;
        head.next = null;

        return newHead.next;
    }

    static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 新头节点，最后返回头节点的next
        ListNode newHead = new ListNode(0);
        ListNode p;

        while (head != null) {
            // p指向当前head链表的第一个
            p = head;
            // head向后移动
            head = head.next;

            // 将p插入到newHead链表的头部
            p.next = newHead.next;
            newHead.next = p;
        }

        return newHead.next;
    }

    public static void main(String[] args) {

        int[] input;
        ListNode list;

        /*
         * 输入：head = [1,2,3,4,5]
         * 输出：[5,4,3,2,1]
         */
        input = new int[]{1, 2, 3, 4, 5};
        list = ListNode.buildList(input);
        reverseList1(list).print();
        list = ListNode.buildList(input);
        reverseList2(list).print();

        /*
         * 输入：head = [1,2]
         * 输出：[2,1]
         */
        input = new int[]{1, 2};
        list = ListNode.buildList(input);
        reverseList1(list).print();
        list = ListNode.buildList(input);
        reverseList2(list).print();

        /*
         * 输入：head = []
         * 输出：[]
         */
        input = new int[]{};
        list = ListNode.buildList(input);
        assert list == null;
        list = ListNode.buildList(input);
        assert list == null;
    }
}
