package edgar.leetcode;

/**
 * 148. 排序链表
 * https://leetcode.cn/problems/sort-list/
 *
 * Created by liuzhao on 2022/7/30
 */
public class Solution0148_SortList {
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 计算列表长度
        int length = 0;
        ListNode p = head;
        while (p != null) {
            length++;
            p = p.next;
        }

        /**
         * 从subLength=1开始，从头开始，依次将两个subLength相同的小list通过归并合并到一起。
         * 然后subLength*2，将两个subLength=2的列表通过归并合并到一起，依次类推
         */
        ListNode newHead = new ListNode(0, head);

        for (int subLength = 1; subLength < length; subLength *= 2) {
            // p从头遍历到尾部，实现一次完成的，以subLength长的归并排序
            ListNode current = newHead;

            while (current != null && current.next != null) {

                /* 找到长度为subLength的第一个子串 */
                // 第一个子串的头节点
                ListNode l1Head = current.next;
                // 第一个子串的尾节点
                ListNode l1Tail = l1Head;
                for (int i = 1; i < subLength; i++) {
                    if (l1Tail.next != null) {
                        l1Tail = l1Tail.next;
                    }
                }

                if (l1Tail.next == null) {
                    // 第一个子串后没有了
                    break;
                }

                // 找到长度为subLength的第二个子串
                // 第二个子串的头节点
                ListNode l2Head = l1Tail.next;
                // 第二个子串的尾节点
                ListNode l2Tail = l2Head;
                for (int i = 1; i < subLength; i++) {
                    if (l2Tail.next != null) {
                        l2Tail = l2Tail.next;
                    }
                }

                // 合并两个子串
                ListNode nodeAfterL2Tail = l2Tail.next;
                l1Tail.next = null;
                l2Tail.next = null;
                current.next = doMerge(l1Head, l2Head);

                // 找到合并后子串的尾节点
                ListNode tailOfTwoList = current;
                while (tailOfTwoList.next != null) {
                    tailOfTwoList = tailOfTwoList.next;
                }
                tailOfTwoList.next = nodeAfterL2Tail;
                current = tailOfTwoList;
            }
        }

        return newHead.next;
    }

    /**
     * 采用递归思路，将原list从中间拆分成两个小list，分别排好序后，再用归并算法合并成一个有效的大list
     * @param head
     * @return
     */
    public static ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return doSortList(head, null);
    }

    static ListNode doSortList(ListNode start, ListNode end) {

        // 链表长度为1，不需要排序
        if (start == end || start == null || start.next == null) {
            return start;
        }

        ListNode fast = start;
        ListNode slow = start;

        // 快指针每次走2步，慢指针每次走1步
        while (fast != end && fast.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 此时slow应执行列表中部节点
        // 先排后一半
        ListNode half2 = doSortList(slow.next, end);
        slow.next = null;
        // 再排前一半
        ListNode half1 = doSortList(start, slow);

        // 前后两部分已经排序，归并到一起
        return doMerge(half1, half2);
    }

    static ListNode doMerge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode p = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }

            p = p.next;
            p.next = null;
        }

        if (list1 != null) {
            p.next = list1;
        } else {
            p.next = list2;
        }

        return head.next;
    }

    public static void main(String[] args) {
        int[] inputs;
        ListNode head;

        /**
         * 输入：head = [4,2,1,3]
         * 输出：[1,2,3,4]
         */
        inputs = new int[]{4, 2, 1, 3};
        head = ListNode.buildList(inputs);
        sortList(head).print();

        /**
         * 输入：head = [-1,5,3,4,0]
         * 输出：[-1,0,3,4,5]
         */
        inputs = new int[]{-1, 5, 3, 4, 0};
        head = ListNode.buildList(inputs);
        sortList(head).print();

        /**
         * 输入：head = []
         * 输出：[]
         */
        inputs = new int[]{};
        head = ListNode.buildList(inputs);
        assert head == null;
    }
}
