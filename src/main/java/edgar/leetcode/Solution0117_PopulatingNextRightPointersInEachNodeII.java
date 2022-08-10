package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/">117. 填充每个节点的下一个右侧节点指针 II</a>
 * Created by liuzhao on 2022/8/10
 */
public class Solution0117_PopulatingNextRightPointersInEachNodeII {

    public static Node connect(Node root) {

        if (root == null) {
            return root;
        }

        // leftMost指向第i层的第一个元素
        Node leftMost = root;

        while (leftMost != null) {
            // 第i+1层的第一个元素
            Node nextLevelHead = new Node(0);
            // 第i+1层的之前遍历的元素
            Node nextLevelPre = nextLevelHead;

            // 在当前层一直想右遍历
            while (leftMost != null) {
                if (leftMost.left != null) {
                    // 左节点不为空
                    nextLevelPre.next = leftMost.left;
                    nextLevelPre = nextLevelPre.next;
                }

                if (leftMost.right != null) {
                    // 左节点不为空
                    nextLevelPre.next = leftMost.right;
                    nextLevelPre = nextLevelPre.next;
                }

                leftMost = leftMost.next;
            }

            // 向下移动一层
            leftMost = nextLevelHead.next;
        }

        return root;
    }

    public static void main(String[] args) {
        Node root;

        /**
         * 输入：root = [1,2,3,4,5,null,7]
         * 输出：[1,#,2,3,#,4,5,7,#]
         * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
         */
        root = new Node(1,
                new Node(2,
                        new Node(4),
                        new Node(5),
                        null),
                new Node(3,
                        null,
                        new Node(7),
                        null),
                null);
        connect(root);
    }
}
