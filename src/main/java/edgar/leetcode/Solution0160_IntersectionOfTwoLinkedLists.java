package edgar.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/">160. 相交链表</a>
 * Created by Edgar.Liu on 2023/2/6
 */
public class Solution0160_IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        // 处理特殊情况
        if (headA == null || headB == null) {
            return null;
        }

        // 先遍历链表A，将所有节点加入Set
        Set<ListNode> aSet = new HashSet<>();
        ListNode p = headA;
        while (p != null) {
            aSet.add(p);
            p = p.next;
        }

        // 遍历链表B，判断节点是否已经包含在aSet中
        p = headB;
        while (p != null) {
            // 链表A的节点集合，包含链表B中p指向的节点，返回p
            if (aSet.contains(p)) {
                return p;
            }

            p = p.next;
        }

        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // 处理特殊情况
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA;
        ListNode pB = headB;

        // pA和pB都遍历一次链表A+链表B，如果没有交集，最后两个应该同时指向列表尾部的null
        while (pA != pB) {
            // pA向后走一步，直到链表尾部。如果pA走到链表A的结尾，则从链表B再走一遍。
            pA = (pA == null) ? headB : pA.next;
            // pB向后走一步，直到链表尾部。如果pB走到链表B的结尾，则从链表A再走一遍。
            pB = (pB == null) ? headA : pB.next;
        }

        return pA;
    }

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        // 处理特殊情况
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA;
        ListNode pB;

        // 遍历链表A，计算链表A的长度
        pA = headA;
        int aLength = 0;
        while (pA != null) {
            aLength++;
            pA = pA.next;
        }

        // 遍历链表B，计算链表A的长度
        pB = headB;
        int bLength = 0;
        while (pB != null) {
            bLength++;
            pB = pB.next;
        }

        // 如果链表A长，则pA从headA移动aLength - bLength步
        // 反之，则pB从headB移动bLength - aLength步
        pA = headA;
        pB = headB;
        if (aLength - bLength > 0) {
            for (int i = 0; i < aLength - bLength; i++) {
                pA = pA.next;
            }
        } else {
            for (int i = 0; i < bLength - aLength; i++) {
                pB = pB.next;
            }
        }

        // 此刻开始，pA和pB同步向后移动
        while (pA != null && pB != null) {
            // pA和pB指向同一个节点，返回
            if (pA == pB) {
                return pA;
            }

            pA = pA.next;
            pB = pB.next;
        }

        return null;
    }
}
