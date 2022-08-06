package edgar.interview;

import edgar.leetcode.DoubleListNode;
import edgar.leetcode.ListNode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by liuzhao on 2022/7/23
 */
public class AmazonTest {

    /**
     * 1.一个数组，里面得数出现的次数是偶数次，只有一个数出现的次数是奇数次，找出那个出现奇数次的数
     */
    static int test1(int[] input) {

        if (input == null || input.length == 0) {
            return 0;
        }

        if (input.length == 1) {
            return input[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : input) {
            if (map.containsKey(i)) {
                // 数字第二次出现
                map.remove(i);
            } else {
                map.put(i, 1);
            }
        }

        // 最后map中应该只有一个数
        assert 1 == map.size();
        for (Integer i : map.keySet()) {
            return i;
        }

        return 0;
    }

    /**
     * 2.给一个大字符串，在里面找小字符串。然后用这个小字符串把大字符串分割，然后组成链表。
     */
    static Node2 test2(String longStr, String shortStr) {
        return dfs(longStr, 0, shortStr);
    }

    static Node2 dfs(String longStr, int start, String shortStr) {

        // 从longStr找到shortStr第一次出现的起始位置
        int idx = find(longStr, start, shortStr);
        Node2 node = new Node2();

        if (idx > 0) {
            // 从start到idx构建一个节点
            node.value = longStr.substring(start, idx);
            node.next = dfs(longStr, idx + shortStr.length(), shortStr);
        } else {
            // 没有找到，剩下的组成一个节点
            node.value = longStr.substring(start);
        }

        return node;
    }

    static int find(String longStr, int start, String shortStr) {

        if (start + shortStr.length() > longStr.length()) {
            // 剩余长度不足
            return -1;
        }

        for (int i = start; i <= longStr.length() - shortStr.length(); ) {
            if (longStr.charAt(i) != shortStr.charAt(0)) {
                i++;
                continue;
            }

            // 当前位置i与shortStr的第一个字符相同，检查剩余字符是否相同
            boolean found = true;
            for (int j = 1; j < shortStr.length(); j++) {
                if (longStr.charAt(i + j) != shortStr.charAt(j)) {
                    // 有不相同的字符
                    found = false;
                    i += j;
                }
            }

            if (found) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 4.单项链表，奇数在前偶数在后，不需要排序
     */
    static ListNode test4(ListNode nodes) {

        ListNode oddHead = new ListNode(0);
        ListNode oddTail = oddHead;
        ListNode evenHead = new ListNode(0);
        ListNode evenTail = evenHead;

        ListNode pNode = nodes;
        while (pNode != null) {
            if (pNode.val % 2 == 1) {
                // 奇数节点
                oddTail.next = pNode;
                pNode = pNode.next;
                oddTail = oddTail.next;
                oddTail.next = null;
            } else {
                // 偶数节点
                evenTail.next = pNode;
                pNode = pNode.next;
                evenTail = evenTail.next;
                evenTail.next = null;
            }
        }

        if (oddHead.next == null) {
            // 没有奇数元素
            return evenHead.next;
        } else {
            oddTail.next = evenHead.next;
        }

        return oddHead.next;
    }

    /**
     * 5.多条单项链表合并，不能消耗内存
     *
     * @return ListNode
     */
    static ListNode test5(ListNode... lists) {
        ListNode result = null;

        for (ListNode list : lists) {
            result = reduceMerge(result, list);
        }

        return result;
    }

    static ListNode reduceMerge(ListNode a, ListNode b) {
        ListNode head = new ListNode(0);
        ListNode p = head;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                p.next = a;
                a = a.next;
                p = p.next;
                p.next = null;
            } else {
                p.next = b;
                b = b.next;
                p = p.next;
                p.next = null;
            }
        }

        if (a != null) {
            p.next = a;
        } else {
            p.next = b;
        }

        return head.next;
    }

    /**
     * 7.字符串翻转 hello world返回 world hello
     *
     * @param str
     * @return
     */
    static String test7(String str) {
        String[] parts = str.split("\s");
        LinkedList<String> list = new LinkedList<>();

        for (String part : parts) {
            list.addFirst(part);
        }

        return list.stream().collect(Collectors.joining(" "));
    }

    static String test7v2(String str) {

        LinkedList<String> list = new LinkedList<>();
        int start = 0;
        int end = 0;

        while (end < str.length()) {

            if (str.charAt(end) != ' ') {
                // 没有遇到空格时，end指针向后移动
                end++;
                continue;
            }

            // 将start到end的部分子串加入list
            list.addFirst(str.substring(start, end));

            // start和end指针向后移动
            start = end + 1;
            end = start;
        }

        // 将start到结尾的部分子串加入list
        if (start < str.length()) {
            list.addFirst(str.substring(start));
        }

        return list.stream().collect(Collectors.joining(" "));
    }

    /**
     * There's a number array, please split it into digits and create a double-linked list based on these digits.
     * (e.g. 123 will be splited into 3 nodes like 1 & 2 & 3, 9467 will be splited into 4 nodes like 9 & 4 & 6 & 7).
     * <p>
     * From the forward direction, the next ptr of each digit (node) will point to the digit behind, for the last digit in the number, the next ptr will be pointed to the first digit of the next number.
     * (e.g. {123,9467}, focus on the next ptr, it will output 1-->2-->3-->9-->4-->6-->7-->null),
     * <p>
     * From the reverse direction, the prev ptr of each digit (node) will point to the digit in front, for the first digit in the number, the prev ptr will be pointed to the last digit of the current number.
     * (e.g. {123,9467}, focus on the prev ptr, it will output 1<--2<--3   9<--4<--6<--7)
     *
     * @param inputs
     * @return
     */
    static DoubleListNode test12(int[] inputs) {

        DoubleListNode<String> head = new DoubleListNode<>("");
        DoubleListNode p = head;

        for (int i : inputs) {
            // 基于i构建双向链表
            DoubleListNode subListHead = buildDoubleList(i);

            // 建立p到subListHead的双向链接
            p.next = subListHead;
            subListHead.previous = p;

            // p移动到双向链表的尾部
            while (p.next != null) {
                p = p.next;
            }
        }

        return head.next;
    }

    static DoubleListNode<String> buildDoubleList(int num) {
        DoubleListNode<String> head = new DoubleListNode<>("");
        DoubleListNode<String> p = head;
        String digits = String.valueOf(num);

        for (int i = 0; i < digits.length(); i++) {
            DoubleListNode<String> node = new DoubleListNode<String>(String.valueOf(digits.charAt(i)));
            node.previous = p;
            p.next = node;
            p = p.next;
        }

        head.next.previous = null;
        return head.next;
    }

    /**
     * 删除一个字符数组里重复的字符 只遍历一遍字符数组 用最少的时间和内存
     *
     * @param input
     * @return
     */
    static String test26(String input) {
        char[] charArray = input.toCharArray();
        Set<Character> visited = new HashSet<>();
        StringBuilder builder = new StringBuilder();

        // 核心
        for (char c : charArray) {
            if (visited.contains(c)) {
                continue;
            }

            builder.append(c);
            visited.add(c);
        }

        return builder.toString();
    }

    static void find1(String path, String pattern) {
        try {
            Files.lines(Path.of(path)).forEach(line -> {
                if (line.contains(pattern)) {
                    System.out.println(line);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void find2(String path, String pattern) {
        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(reader);
            String line;

            while ((line = buffer.readLine()) != null) {
                if (line.contains(pattern)) {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 34.找出一个数组的pivot 下标，pivot是指这个元素前面所有的元素的和，和这个元素后面所有元素的和相等。
     *
     * @param inputs
     * @return
     */
    static int test34(int[] inputs) {
        int sum = 0;
        for (int i : inputs) {
            sum += i;
        }

        // pivot不可能在0或最后一个位置
        int sumLeft = 0;
        int sumRight = sum - inputs[0];
        for (int i = 1; i < inputs.length - 2; i++) {
            sumLeft += inputs[i - 1];
            sumRight -= inputs[i];

            if (sumLeft == sumRight) {
                return i;
            }
        }

        return 0;
    }

    static ListNode test36(ListNode list, int num) {
        ListNode head = new ListNode(0, list);
        // prev执行value=num的前一个节点
        ListNode prev = head;

        while (prev.next != null) {
            if (prev.next.val == num) {
                break;
            } else {
                prev = prev.next;
            }
        }

        if (prev != null) {
            // 找到节点，value = num
            ListNode newHead = new ListNode(0, prev.next);
            // p 将执行链表尾部
            ListNode p = newHead;
            while (p.next != null) {
                p = p.next;
            }

            p.next = head.next;
            prev.next = null;
            head.next = newHead.next;
        }

        return head.next;
    }

    public static void main(String[] args) {

        var input1 = new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1, 5, 5, 6, 6, 7, 7, 7, 7};
        assert 5 == test1(input1);

        var input2 = "11aaa2222aaa3aaa4444aaa55aaa666666aaa777777777aaa88";
        var head = test2(input2, "aaa");
        System.out.println(head.toString());

        var input4 = new int[]{1, 2, 3, 4, 5};
        ListNode nodes4 = ListNode.buildList(input4);
        test4(nodes4).print();

        var input5_1 = new int[]{2, 5, 8, 10, 14};
        var input5_2 = new int[]{1, 4, 7, 12, 15};
        var input5_3 = new int[]{3, 6, 9, 11, 13};
        var nodes5_1 = ListNode.buildList(input5_1);
        var nodes5_2 = ListNode.buildList(input5_2);
        var nodes5_3 = ListNode.buildList(input5_3);
        test5(nodes5_1, nodes5_2, nodes5_3).print();

        var input7 = "hello world";
        assert "world hello".equals(test7(input7));
        assert "world hello".equals(test7v2(input7));

        var input12 = new int[]{123, 9467, 901};
        test12(input12).print();

        var input26_1 = "bcabc";
        assert "bca".equals(test26(input26_1));
        var input26_2 = "cbacdcbc";
        assert "cbad".equals(test26(input26_2));

        var filePath = "/Users/liuzhao/Desktop/Bytedance/workspace/GitHub/java_leetcode/pom.xml";
        var pattern = "spring";
        find1(filePath, pattern);
        find2(filePath, pattern);

        var input34 = new int[]{3, 1, 3, 5, 8, 0, 2, 4, 6};
        assert 4 == test34(input34);

        var input36 = input4;
        ListNode nodes36 = ListNode.buildList(input36);
        test36(nodes36, 3).print();

    }
}

class Node2 {
    public String value;
    public Node2 next;

    @Override
    public String toString() {
        String s = "Node2: " + value;

        Node2 nextNode = this.next;
        while (nextNode != null) {
            s += " -> " + nextNode.value;
            nextNode = nextNode.next;
        }

        return s;
    }
}