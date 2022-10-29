package edgar.leetcode;

import java.util.*;

/**
 * Created by Edgar.Liu on 2022/10/29
 */
public class Node2 {

    public int val;
    public Node2 next;
    public Node2 random;

    public Node2() {

    }

    public Node2(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public Node2(int val, Node2 next, Node2 random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }

    @Override
    public String toString() {
        List<String> list = new LinkedList<>();
        Node2 p = this;
        while (p != null) {
            int nextVal = (p.next != null) ? p.next.val : -1;
            int randomVal = (p.random != null) ? p.random.val : -1;
            String str = String.format("Node2{val=%d, nextVal=%d, randomVal=%d}", p.val, nextVal, randomVal);
            list.add(str);

            p = p.next;
        }

        return String.join(" -> ", list);
    }

    public static Node2 buildFromArray(int[][] inputs) {

        Node2 head = new Node2();
        if (Objects.isNull(inputs) || inputs.length == 0) {
            return head.next;
        }

        // 第一次遍历数组，创建所有节点
        Node2 p = head;
        // 记录第i节点的引用
        Map<Integer, Node2> map = new HashMap<>();
        for (int i = 0; i < inputs.length; i++) {
            int[] elem = inputs[i];
            Node2 node = new Node2(elem[0]);
            map.put(i, node);

            p.next = node;
            p = p.next;
        }

        // 第二次遍历数组，给random赋值
        p = head.next;
        int idx = 0;
        while (p != null) {
            Node2 node = p;
            int[] elem = inputs[idx];

            if (elem[1] != -1) {
                node.random = map.get(elem[1]);
            }

            p = p.next;
            idx++;
        }

        return head.next;
    }
}
