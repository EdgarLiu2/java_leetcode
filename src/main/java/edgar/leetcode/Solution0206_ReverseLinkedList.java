package edgar.leetcode;

/**
 * Created by liuzhao on 2022/7/31
 */
public class Solution0206_ReverseLinkedList {
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = new ListNode(0);

        // 当前的第一个节点
        ListNode firstNode = head;
        // 将第一个节点后面的链表反转
        ListNode restList = reverseList(firstNode.next);
        // 把后面链表放在前面
        newHead.next = restList;
        // 找到restList的尾部
        ListNode tail = restList;
        while (tail.next != null) {
            tail = tail.next;
        }
        // 将firstNode放在尾部
        tail.next = firstNode;
        firstNode.next = null;

        return newHead.next;
    }

    public static void main(String[] args) {

        int[] input;
        ListNode list;

        /**
         * 输入：head = [1,2,3,4,5]
         * 输出：[5,4,3,2,1]
         */
        input = new int[]{1, 2, 3, 4, 5};
        list = ListNode.buildList(input);
        reverseList(list).print();

        /**
         * 输入：head = [1,2]
         * 输出：[2,1]
         */
        input = new int[]{1, 2};
        list = ListNode.buildList(input);
        reverseList(list).print();

        /**
         * 输入：head = []
         * 输出：[]
         */
        input = new int[]{};
        list = ListNode.buildList(input);
        assert list == null;
    }
}
