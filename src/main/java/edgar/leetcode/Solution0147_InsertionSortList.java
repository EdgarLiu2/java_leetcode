package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/insertion-sort-list/">147. 对链表进行插入排序</a>
 *
 * @author Edgar.Liu
 * @since 2023/12/3 - 15:45
 */
public class Solution0147_InsertionSortList {
    static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = new ListNode(0);

        // 从head开始依次插入到新list中
        while (head != null) {
            // 本轮循环，将pCurrent节点插入到新list中
            ListNode pCurrent = head;
            head = head.next;
            pCurrent.next = null;

            ListNode pNewPrev = newHead;
            ListNode pNewCurrent = pNewPrev.next;

            while (pNewCurrent != null) {
                // 从pNewCurrent开始遍历，找到第一个>=pCurrent的节点，并将心节点插入到pNewPrev和pNewCurrent中间
                if (pNewCurrent.val <= pCurrent.val) {
                    // 继续向右移动
                    pNewPrev = pNewPrev.next;
                    pNewCurrent = pNewPrev.next;
                } else {
                    // pNewPrev <= pCurrent <= pNewCurrent
                    break;
                }
            }

            if (pNewCurrent == null) {
                // 已经走到新链表的尾部，新节点时当前的最大值，直接放到新链表
                pNewPrev.next = pCurrent;
            } else {
                pCurrent.next = pNewCurrent;
                pNewPrev.next = pCurrent;
            }

        }

        return newHead.next;
    }

    public static void main(String[] args) {
        int[] input;
        ListNode list;

        /*
         * 输入: head = [4,2,1,3]
         * 输出: [1,2,3,4]
         */
        input = new int[]{4, 2, 1, 3};
        list = ListNode.buildList(input);
        list = insertionSortList(list);
        list.print();

        /*
         * 输入: head = [-1,5,3,4,0]
         * 输出: [-1,0,3,4,5]
         */
        input = new int[]{-1, 5, 3, 4, 0};
        list = ListNode.buildList(input);
        list = insertionSortList(list);
        list.print();
    }
}
