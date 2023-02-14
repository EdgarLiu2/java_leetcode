package edgar.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/decode-string/">394. 字符串解码</a>
 * Created by Edgar.Liu on 2023/2/14
 */
public class Solution0394_DecodeString {

    // 当前处理到s的第idx位置
    private int idx;

    public String decodeString(String s) {
        idx = 0;
        // 保存已处理元组
        Deque<String> stack = new ArrayDeque<>();

        while (idx < s.length()) {
            char c = s.charAt(idx);

            if (Character.isDigit(c)) {
                // 当前字符是数字，则从当前位置获取全部数字
                String num = getNumber(s);
                // 操作数入栈
                stack.push(num);
            } else if (c == '[') {
                // 左括号，继续入栈
                stack.push(String.valueOf(c));
                idx++;
            } else if (Character.isLetter(c)) {
                // 当前字符是字母，则从当前位置获取全部字母
                String letters = getLetters(s);
                // 操作数入栈
                stack.push(letters);
            } else if (c == ']') {
                // 先弹出栈顶字符串，只要不是'['，就持续弹出
                String str = "";
                while (!"[".equals(stack.peek())) {
                    str = stack.pop() + str;
                }
                // 弹出栈顶'['，丢弃
                String tmp = stack.pop();
                assert "[".equals(tmp);
                // 弹出栈顶数字
                String numString = stack.pop();
                int num = Integer.parseInt(numString);
                // 将str重复num次，再放回栈
                String newStr = str.repeat(num);
                stack.push(newStr);

                idx++;
            }
        }

        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }

        return result;
    }

    private String getLetters(String s) {
        StringBuilder builder = new StringBuilder();

        for (; idx < s.length(); idx++) {
            char c = s.charAt(idx);

            if (Character.isLetter(c)) {
                builder.append(c);
            } else {
                break;
            }
        }

        return builder.toString();
    }

    private String getNumber(String s) {
        StringBuilder builder = new StringBuilder();

        for (; idx < s.length(); idx++) {
            char c = s.charAt(idx);

            if (Character.isDigit(c)) {
                builder.append(c);
            } else {
                break;
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String input;
        Solution0394_DecodeString solution = new Solution0394_DecodeString();

        /*
         * 输入：s = "3[a]2[bc]"
         * 输出："aaabcbc"
         */
        input = "3[a]2[bc]";
        assert "aaabcbc".equals(solution.decodeString(input));

        /*
         * 输入：s = "3[a2[c]]"
         * 输出："accaccacc"
         */
        input = "3[a2[c]]";
        assert "accaccacc".equals(solution.decodeString(input));

        /*
         * 输入：s = "2[abc]3[cd]ef"
         * 输出："abcabccdcdcdef"
         */
        input = "2[abc]3[cd]ef";
        assert "abcabccdcdcdef".equals(solution.decodeString(input));

        /*
         * 输入：s = "abc3[cd]xyz"
         * 输出："abccdcdcdxyz"
         */
        input = "abc3[cd]xyz";
        assert "abccdcdcdxyz".equals(solution.decodeString(input));
    }
}
