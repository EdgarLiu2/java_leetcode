package edgar.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/">236. 二叉树的最近公共祖先</a>
 *
 * @author Edgar.Liu
 * @since 2023/11/21 - 07:48
 */
public class Solution0236_LowestCommonAncestorOfABinaryTree {
    static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // 通过递归遍历，生成每个节点的父节点Map
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        // 根节点的父节点为空
        fillParentMap(root, null, parentMap);

        // 将p和p的父节点放入pParentSet
        Set<TreeNode> pParentSet = new HashSet<>();
        pParentSet.add(p);
        TreeNode current = parentMap.get(p);
        while (current != null) {
            pParentSet.add(current);
            current = parentMap.get(current);
        }

        // 从q开始往上遍历，寻找与集合pParentSet第一个交集的节点
        current = q;
        while (current != null) {
            if (pParentSet.contains(current)) {
                return current;
            }

            current = parentMap.get(current);
        }

        return null;
    }

    private static void fillParentMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (node == null) {
            return;
        }

        parentMap.put(node, parent);

        fillParentMap(node.left, node, parentMap);
        fillParentMap(node.right, node, parentMap);
    }

    public static void main(String[] args) {
        TreeNode root, p, q, ans;

        /*
         * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
         * 输出：3
         * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
         */
        p = new TreeNode(5,
                new TreeNode(6),
                new TreeNode(2,
                        new TreeNode(7),
                        new TreeNode(4)));
        q = new TreeNode(1,
                new TreeNode(0),
                new TreeNode(8));
        root = new TreeNode(3,
                p,
                q);
        ans = lowestCommonAncestor1(root, p, q);
        assert ans != null && ans.val == 3;

        /*
         * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
         * 输出：5
         * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
         */
        q = new TreeNode(4);
        p = new TreeNode(5,
                new TreeNode(6),
                new TreeNode(2,
                        new TreeNode(7),
                        q));
        root = new TreeNode(3,
                p,
                new TreeNode(1,
                        new TreeNode(0),
                        new TreeNode(8)));
        ans = lowestCommonAncestor1(root, p, q);
        assert ans != null && ans.val == 5;

        /*
         * 输入：root = [1,2], p = 1, q = 2
         * 输出：1
         */
        q = new TreeNode(2);
        p = new TreeNode(1,
                q,
                null);
        root = p;
        ans = lowestCommonAncestor1(root, p, q);
        assert ans != null && ans.val == 1;
    }
}
