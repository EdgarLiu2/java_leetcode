package edgar.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/">116. 填充每个节点的下一个右侧节点指针</a>
 * Created by liuzhao on 2022/8/9
 */
public class Solution0116_PopulatingNextRightPointersInEachNode {

    public static Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // 当前层最左节点
        Node leftMost = root;

        while (leftMost.left != null) {
            // 从当前层最左开始
            Node head = leftMost;

            while (head != null) {
                // 连接head的左右节点
                head.left.next = head.right;

                // 连接head的右子节点，和head兄弟节点的左子节点
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                head = head.next;
            }

            // 向下移动一行
            leftMost = leftMost.left;
        }


        return root;
    }

    public static Node connect2(Node root) {

        if (root == null) {
            return root;
        }

        // 借助队列，按层遍历所有节点
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 当前层有size个元素
            int size = queue.size();
            Node prev = null;

            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                if (n.left != null) {
                    queue.offer(n.left);
                }
                if (n.right != null) {
                    queue.offer(n.right);
                }

                if (prev != null) {
                    prev.next = n;
                }

                prev = n;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Node root;

        /*
         * 输入：root = [1,2,3,4,5,6,7]
         * 输出：[1,#,2,3,#,4,5,6,7,#]
         * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
         */
        root = new Node(1,
                new Node(2,
                        new Node(4),
                        new Node(5),
                        null),
                new Node(3,
                        new Node(6),
                        new Node(7),
                        null),
                null);
        connect(root);
    }
}
