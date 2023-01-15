package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/minimum-window-substring/">76. 最小覆盖子串</a>
 * Created by Edgar.Liu on 2023/1/11
 */
public class Solution0076_MinimumWindowSubstring {
    static String minWindow(String s, String t) {

        if (s.length() < t.length()) {
            // s的长度小于t，肯定没有解
            return "";
        }

        // 先处理t，统计各个字符的数量
        int totalDebt = t.length();
        // 词频表
        int[] charFrequencyMap = new int[256];

        for (char c : t.toCharArray()) {
            charFrequencyMap[c]++;
        }

        // 准备处理s
        int L = 0;
        int R = 0;
        char[] sChars = s.toCharArray();
        // 目前最佳子串长度
        int answerLen = Integer.MAX_VALUE;
        int answerL = 0;
        int answerR = 0;

        // 开始遍历s
        while (R != sChars.length) {
            char c = sChars[R];
            charFrequencyMap[c]--;
            if (charFrequencyMap[c] >= 0) {
                // 有效还款，总债务下降
                totalDebt--;
            }

            // 已经找到t中所有字符，L开始向右
            // L向右走，当 totalDebt=0 且 R-L<answerLen 时，就找到更优解
            while (totalDebt == 0) {
                if (R - L + 1 < answerLen) {
                    // 有更优解
                    answerLen = R - L + 1;
                    answerL = L;
                    answerR = R;
                }

                // L一直向右走，直到totalDebt != 0
                char cc = sChars[L++];
                charFrequencyMap[cc]++;
                if (charFrequencyMap[cc] > 0) {
                    totalDebt++;
                    break;
                }
            }

            // R一直向右走，直到totalDebt = 0
            R++;
        }

        // 结果返回
        return answerLen == Integer.MAX_VALUE ? "" : s.substring(answerL, answerR + 1);
    }

    public static void main(String[] args) {
        String s, t;

        /*
         * 输入：s = "ADOBECODEBANC", t = "ABC"
         * 输出："BANC"
         * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
         */
        s = "ADOBECODEBANC";
        t = "ABC";
        assert "BANC".equals(minWindow(s, t));

        /*
         * 输入：s = "a", t = "a"
         * 输出："a"
         * 解释：整个字符串 s 是最小覆盖子串。
         */
        s = "a";
        t = "a";
        assert "a".equals(minWindow(s, t));

        /*
         * 输入: s = "a", t = "aa"
         * 输出: ""
         * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
         */
        s = "a";
        t = "aa";
        assert "".equals(minWindow(s, t));
    }
}
