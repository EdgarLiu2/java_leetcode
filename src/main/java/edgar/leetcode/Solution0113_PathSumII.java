package edgar.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/path-sum-ii/">113. 路径总和 II</a>
 * Created by liuzhao on 2022/8/8
 */
public class Solution0113_PathSumII {

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        LinkedList<Integer> currentPath = new LinkedList<>();
        currentPath.addLast(root.val);
        dfs(root, targetSum, result, currentPath);

        return result;
    }

    static void dfs(TreeNode root, int targetSum, List<List<Integer>> result, LinkedList<Integer> currentPath) {

        if (root.left == null && root.right == null && root.val == targetSum) {
            // 叶子节点，且节点value==剩余的targetSum，找到一条路
            result.add(new ArrayList<>(currentPath));
        }

        targetSum -= root.val;

        if (root.left != null) {
            // 左子树不为空向下递归
            currentPath.addLast(root.left.val);
            dfs(root.left, targetSum, result, currentPath);
            currentPath.removeLast();
        }

        if (root.right != null) {
            // 右子树不为空向下递归
            currentPath.addLast(root.right.val);
            dfs(root.right, targetSum, result, currentPath);
            currentPath.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result;
        TreeNode root;

        /*
         * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
         * 输出：[[5,4,11,2],[5,8,4,5]]
         */
        root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11,
                                new TreeNode(7),
                                new TreeNode(2)),
                        null),
                new TreeNode(8,
                        new TreeNode(13),
                        new TreeNode(4,
                                new TreeNode(5),
                                new TreeNode(1)))
        );
        result = pathSum(root, 22);
        System.out.println(result);

        /*
         * 输入：root = [1,2,3], targetSum = 5
         * 输出：[]
         */
        root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3)
                );
        result = pathSum(root, 5);
        System.out.println(result);

        /*
         * 输入：root = [1,2], targetSum = 0
         * 输出：[]
         */
        root = new TreeNode(1,
                new TreeNode(2),
                null
        );
        result = pathSum(root, 0);
        assert result.isEmpty();
    }
}
