package edgar.leetcode.offer;


import edgar.leetcode.ListNode;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.cn/problems/aMhZSa/">剑指 Offer II 027. 回文链表</a>
 * Created by liuzhao on 2022/8/5
 */
public class Offer0027_IsPalindrome {
    public static boolean isPalindrome(ListNode head) {

        ArrayList<Integer> array = new ArrayList<>();
        // 遍历head，将所有数放入ArrayList
        while (head != null) {
            array.add(head.val);
            head = head.next;
        }

        // 使用双指针判断是否为回文
        int left = 0;
        int right = array.size() - 1;

        while (left < right) {
            if (!array.get(left).equals(array.get(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] input;
        ListNode head;

        /*
         * 输入: head = [1,2,3,3,2,1]
         * 输出: true
         */
        input = new int[]{1,2,3,3,2,1};
        head = ListNode.buildList(input);
        assert isPalindrome(head);

        /*
         * 输入: head = [1,2]
         * 输出: true
         */
        input = new int[]{1,2};
        head = ListNode.buildList(input);
        assert !isPalindrome(head);
    }
}
