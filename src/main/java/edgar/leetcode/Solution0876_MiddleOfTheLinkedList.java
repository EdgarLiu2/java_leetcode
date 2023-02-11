package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/middle-of-the-linked-list/">876. 链表的中间结点</a>
 * Created by Edgar.Liu on 2023/2/11
 */
public class Solution0876_MiddleOfTheLinkedList {
    static ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] input;
        ListNode head;

        /*
         * 输入：[1,2,3,4,5]
         * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
         * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
         * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
         * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
         */
        input = new int[]{1, 2, 3, 4, 5};
        head = ListNode.buildList(input);
        assert 3 == middleNode(head).val;

        /*
         * 输入：[1,2,3,4,5,6]
         * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
         * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
         */
        input = new int[]{1, 2, 3, 4, 5, 6};
        head = ListNode.buildList(input);
        assert 4 == middleNode(head).val;
    }
}
