package edgar.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/">138. 复制带随机指针的链表</a>
 *
 * @author Edgar.Liu
 * @since 2022/10/29 - 23:29
 */
public class Solution0138_CopyListWithRandomPointer {

    static Node2 copyRandomList(Node2 head) {

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

    static Node2 copyRandomList2(Node2 head) {
        // 保存新旧节点的对应关系
        Map<Node2, Node2> map = new HashMap<>();

        Node2 current = head;
        while (current != null) {
            Node2 newNode = new Node2(current.val);
            // 保存节点关系
            map.put(current, newNode);

            // 旧列表向后移动
            current = current.next;
        }

        // 第二遍遍历head，调整newHead节点的next和random属性
        current = head;
        while (current != null) {
            map.get(current).next = map.get(current.next);
            map.get(current).random = map.get(current.random);

            current = current.next;
        }

        return map.get(head);
    }

    static Node2 copyRandomList3(Node2 head) {
        Node2 pCurrent;

        // 从head开始，沿着next指针遍历原始数组，为每个节点创建一个clone节点，并放在原来节点的后面
        // 1 -> 2
        // 1 -> 1' -> 2
        pCurrent = head;
        while (pCurrent != null) {
            Node2 pNext = pCurrent.next;

            // 创建1'节点，并加入当前链表中
            Node2 pCloneCurrent = new Node2(pCurrent.val);
            pCloneCurrent.next = pNext;
            pCurrent.next = pCloneCurrent;

            // 移动到节点2
            pCurrent = pNext;
        }

        // 从head开始，沿着next指针遍历原始数组，参照原节点的random指针，设置新节点的random指针
        pCurrent = head;
        while (pCurrent != null) {
            Node2 pNext = pCurrent.next.next;

            // 如果1节点的random指向3节点，那么1'节点的random指向3节点的next，即3'节点
            Node2 pCloneCurrent = pCurrent.next;
            pCloneCurrent.random = (pCurrent.random == null) ? null : pCurrent.random.next;

            // 移动到节点2
            pCurrent = pNext;
        }

        Node2 newHead = new Node2(0);
        Node2 newCurrent = newHead;
        // 对链表进行拆分，将1'和2'等新节点挪到newHead下并返回
        // 1 -> 1' -> 2 -> 2'
        pCurrent = head;
        while (pCurrent != null) {
            Node2 pNext = pCurrent.next.next;

            // head -> 1'
            newCurrent.next = pCurrent.next;
            newCurrent = newCurrent.next;
            // 1 -> 2
            pCurrent.next = pNext;

            // 移动到节点2
            pCurrent = pNext;
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
        copiedHead = copyRandomList2(head);
        System.out.println(copiedHead);
        copiedHead = copyRandomList3(head);
        System.out.println(copiedHead);

        /*
         * 输入：head = [[1,1],[2,1]]
         * 输出：[[1,1],[2,1]]
         */
        inputs = new int[][]{{1,1},{2,1}};
        head = Node2.buildFromArray(inputs);
        copiedHead = copyRandomList(head);
        System.out.println(copiedHead);
        copiedHead = copyRandomList2(head);
        System.out.println(copiedHead);
        copiedHead = copyRandomList3(head);
        System.out.println(copiedHead);

        /*
         * 输入：head = [[3,null],[3,0],[3,null]]
         * 输出：{{3,null},{3,0},{3,null}}
         */
        inputs = new int[][]{{3,-1},{3,0},{3,-1}};
        head = Node2.buildFromArray(inputs);
        copiedHead = copyRandomList(head);
        System.out.println(copiedHead);
        copiedHead = copyRandomList2(head);
        System.out.println(copiedHead);
        copiedHead = copyRandomList3(head);
        System.out.println(copiedHead);
    }
}
