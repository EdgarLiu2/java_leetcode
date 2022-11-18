package edgar.leetcode;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.cn/problems/reorder-list/submissions/">143. 重排链表</a>
 * Created by Edgar.Liu on 2022/11/18
 */
public class Solution0143_ReorderList {

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // 将链表转成数组
        ArrayList<ListNode> array = new ArrayList<>();
        ListNode p = head;
        while(p != null) {
            array.add(p);
            p = p.next;
        }

        // 使用双指针，从前、后构造新链表
        p = head;
        int left = 0;
        int right = array.size() - 1;

        while (left <= right) {
            // 左边取一个
            p.next = array.get(left);
            p = p.next;

            if (left == right) {
                // 如果左右指针重叠时，退出
                break;
            }

            // 右边取一个
            p.next = array.get(right);
            p = p.next;

            // 左右指针移动
            left++;
            right--;
        }

        p.next = null;
    }

    public static void main(String[] args) {
        int[] input;
        ListNode list;

        /*
         * 输入：head = [1,2,3,4]
         * 输出：[1,4,2,3]
         */
        input = new int[]{1, 2, 3, 4};
        list = ListNode.buildList(input);
        reorderList(list);
        list.print();

        /*
         * 输入：head = [1,2,3,4,5]
         * 输出：[1,5,2,4,3]
         */
        input = new int[]{1, 2, 3, 4, 5};
        list = ListNode.buildList(input);
        reorderList(list);
        list.print();
    }
}
