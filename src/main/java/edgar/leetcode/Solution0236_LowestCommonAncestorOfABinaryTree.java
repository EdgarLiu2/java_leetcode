package edgar.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/">236. 二叉树的最近公共祖先</a>
 * <a href="https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/">LCR 194. 二叉树的最近公共祖先</a>
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

    static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return doLowestCommonAncestor2(root, p, q).answer;
    }

    static LowestCommonAncestorInfo doLowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            // 空树一定没有找到，直接返回信息
            return new LowestCommonAncestorInfo(null, false, false);
        }

        // 从左右子树获取信息
        LowestCommonAncestorInfo leftInfo = doLowestCommonAncestor2(root.left, p, q);
        LowestCommonAncestorInfo rightInfo = doLowestCommonAncestor2(root.right, p, q);

        // 开始汇总当前节点的信息

        // 当前root节点就是p，或者左右子树已经找到p，表示当前子树已经找到p
        boolean isFindP = root == p || leftInfo.isFindP || rightInfo.isFindP;
        // 当前root节点就是q，或者左右子树已经找到q，表示当前子树已经找到q
        boolean isFindQ = root == q || leftInfo.isFindQ || rightInfo.isFindQ;

        // 当前子树的最后答案
        TreeNode answer = null;
        if (leftInfo.answer != null) {
            // 左子树已经找到答案，那就是当前子树的最后答案
            answer = leftInfo.answer;
        }
        if (rightInfo.answer != null) {
            // 右子树已经找到答案，那就是当前子树的最后答案
            answer = rightInfo.answer;
        }

        if (answer == null && isFindP && isFindQ) {
            // 如果当前子树已经找到p和q，那么相交节点就是当前的root节点
            answer = root;
        }

        return new LowestCommonAncestorInfo(answer, isFindP, isFindQ);
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
        ans = lowestCommonAncestor2(root, p, q);
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
        ans = lowestCommonAncestor2(root, p, q);
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
        ans = lowestCommonAncestor2(root, p, q);
        assert ans != null && ans.val == 1;
    }
}

class LowestCommonAncestorInfo {
    public TreeNode answer;
    public boolean isFindP;
    public boolean isFindQ;

    public LowestCommonAncestorInfo(TreeNode answer, boolean isFindP, boolean isFindQ) {
        this.answer = answer;
        this.isFindP = isFindP;
        this.isFindQ = isFindQ;
    }
}