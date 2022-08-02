package edgar.leetcode;

import java.util.*;

/**
 * 316. 去除重复字母
 * https://leetcode.cn/problems/remove-duplicate-letters/
 *
 * Created by liuzhao on 2022/8/2
 */
public class Solution0316_RemoveDuplicateLetters {
    public static String removeDuplicateLetters(String s) {

        int length = s.length();
        char[] charArray = s.toCharArray();
        // 保存每个字符最后出现的位置
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            lastIndexMap.put(charArray[i], i);
        }
        // 保存已经在栈中的字符
        Set<Character> inStackSet = new HashSet<>();
        // 结果栈
        Deque<Character> stack = new ArrayDeque<>();

        // 核心算法
        for (int i = 0; i < length; i++) {
            char c = charArray[i];

            // 已经包含当前c
            if (inStackSet.contains(c)) {
                continue;
            }

            // 检查栈顶元素，如果栈顶元素比c大，且后续还会出现，则需要将栈顶元素出栈
            while (!stack.isEmpty() && stack.peekLast() > c && lastIndexMap.get(stack.peekLast()) > i) {
                Character popedChar = stack.removeLast();
                inStackSet.remove(popedChar);
            }

            // 放入c
            stack.addLast(c);
            inStackSet.add(c);
        }

        // 组织结果
        StringBuilder builder = new StringBuilder();
        for (Character c : stack) {
            builder.append(c);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String input;

        /**
         * 输入：s = "bcabc"
         * 输出："abc"
         */
        input = "bcabc";
        assert "abc".equals(removeDuplicateLetters(input));

        /**
         * 输入：s = "cbacdcbc"
         * 输出："acdb"
         */
        input = "cbacdcbc";
        assert "acdb".equals(removeDuplicateLetters(input));
    }
}
