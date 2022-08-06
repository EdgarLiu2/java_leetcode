package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/">109. 有序链表转换二叉搜索树</a>
 * Created by liuzhao on 2022/8/5
 */
public class Solution0109_ConvertSortedListToBinarySearchTree {

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        return doBinarySearchTree(head, null);
    }

    static TreeNode doBinarySearchTree(ListNode start, ListNode end) {
        if (start == end) {
            return null;
        }

        // 使用快慢指针的方法，找到start和end的中间节点
        ListNode fast = start;
        ListNode slow = start;
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }

        // 此时slow指向中间节点
        TreeNode root = new TreeNode(slow.val);
        root.left = doBinarySearchTree(start, slow);
        root.right = doBinarySearchTree(slow.next, end);

        return root;
    }

    public static TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }

        // 将链表转成数组
        ArrayList<Integer> array = new ArrayList<>();
        while (head != null) {
            array.add(head.val);
            head = head.next;
        }

        // 递归构造二叉搜索树
        return doBinarySearchTree(array, 0, array.size() - 1);
    }

    static TreeNode doBinarySearchTree(ArrayList<Integer> array, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(array.get(mid));
        root.left = doBinarySearchTree(array, start, mid - 1);
        root.right = doBinarySearchTree(array, mid + 1, end);

        return root;
    }

    public static void main(String[] args) {

        int[] input;
        ListNode head;
        TreeNode result;
        List<Integer> integers;

        /*
         * 输入: head = [-10,-3,0,5,9]
         * 输出: [0,-3,9,-10,null,5]
         * 解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
         */
        input = new int[] {-10,-3,0,5,9};
        head = ListNode.buildList(input);
        result = sortedListToBST(head);
        integers = result.preorderTraversal();
        integers.forEach(t -> System.out.print(t + " -> "));

        /*
         * 输入: head = []
         * 输出: []
         */
        input = new int[] {};
        head = ListNode.buildList(input);
        result = sortedListToBST(head);
        assert result == null;
    }
}
