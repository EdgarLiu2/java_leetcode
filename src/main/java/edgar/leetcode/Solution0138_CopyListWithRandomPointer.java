package edgar.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/">138. 复制带随机指针的链表</a>
 * Created by Edgar.Liu on 2022/10/29
 */
public class Solution0138_CopyListWithRandomPointer {

    public static Node2 copyRandomList(Node2 head) {

        if (Objects.isNull(head)) {
            return null;
        }

        Node2 newHead = new Node2(0);

        // 第一遍遍历head，创建newHead对应节点
        Node2 p1 = head;
        Node2 p2 = newHead;
        // 保存新旧节点的对应关系
        Map<Node2, Node2> map = new HashMap<>();

        while (p1 != null) {
            Node2 newNode = new Node2(p1.val);
            // 保存节点关系
            map.put(p1, newNode);
            p2.next = newNode;

            // 新旧列表向后移动
            p2 = p2.next;
            p1 = p1.next;
        }

        // 第二遍遍历head，调整newHead节点的random属性
        Node2 oldNode = head;
        while (oldNode != null) {
            Node2 newNode = map.get(oldNode);
            newNode.random = map.get(oldNode.random);

            oldNode = oldNode.next;
        }

        return newHead.next;
    }

    public static void main(String[] args) {

        int[][] inputs;
        Node2 head;
        Node2 copiedHead;

        /*
         * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
         * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
         */
        inputs = new int[][]{{7,-1},{13,0},{11,4},{10,2},{1,0}};
        head = Node2.buildFromArray(inputs);
        copiedHead = copyRandomList(head);
        System.out.println(copiedHead);

        /*
         * 输入：head = [[1,1],[2,1]]
         * 输出：[[1,1],[2,1]]
         */
        inputs = new int[][]{{1,1},{2,1}};
        head = Node2.buildFromArray(inputs);
        copiedHead = copyRandomList(head);
        System.out.println(copiedHead);

        /*
         * 输入：head = [[3,null],[3,0],[3,null]]
         * 输出：{{3,null},{3,0},{3,null}}
         */
        inputs = new int[][]{{3,-1},{3,0},{3,-1}};
        head = Node2.buildFromArray(inputs);
        copiedHead = copyRandomList(head);
        System.out.println(copiedHead);
    }
}