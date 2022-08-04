package edgar.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 1930. 长度为 3 的不同回文子序列
 * https://leetcode.cn/problems/unique-length-3-palindromic-subsequences/
 *
 * Created by liuzhao on 2022/8/4
 */
public class Solution1930_UniqueLength3PalindromicSubsequences {

    /**
     * 从a到z，枚举中间的字符，然后向两边找相同的字符
     * @param s
     * @return int
     */
    public static int countPalindromicSubsequence(String s) {
        if (s.length() < 3) {
            return 0;
        }

        char[] charArray = s.toCharArray();
        Set<String> result = new HashSet<>();
        Set<Character> leftCharSet = new HashSet<>();
        Set<Character> rightCharSet = new HashSet<>();

        // 中间从 1 遍历到 length - 2
        for (int i = 1; i < charArray.length - 1; i++) {
            // 清空 rightCharSet
            rightCharSet.clear();
            // i 左边的加入 leftCharSet
            leftCharSet.add(charArray[i - 1]);
            // 先将 j=i+1 到 j=length-1 的所有字符加入rightCharSet
            for (int j = i + 1; j < charArray.length; j++) {
                rightCharSet.add(charArray[j]);
            }

            // 求 leftCharSet 和 rightCharSet 的交集
            Set<Character> inter = new HashSet<>(leftCharSet);
            inter.retainAll(rightCharSet);

            // inter中的字符，和charArray[i]组成的字符串就是结果
            for (char c : inter) {
                result.add("" + c + charArray[i] + c);
            }
        }

        return result.size();
    }

    /**
     * 从a到z，枚举两侧的字符，然后统计中间不重复的字符数
     *
     * @param s
     * @return int
     */
    public static int countPalindromicSubsequence2(String s) {
        if (s.length() < 3) {
            return 0;
        }

        char[] charArray = s.toCharArray();
        int result = 0;

        // 遍历 a - z
        for (char c = 'a'; c <= 'z'; c++) {
            int leftC = 0;
            int rightC = charArray.length - 1;

            // 从左侧找到 c 的位置
            while (leftC < rightC) {
                if (charArray[leftC] != c) {
                    leftC++;
                } else {
                    break;
                }
            }

            // 从右侧找到 c 的位置
            while (leftC < rightC) {
                if (charArray[rightC] != c) {
                    rightC--;
                } else {
                    break;
                }
            }

            if (rightC - leftC < 2) {
                // 无法凑成长度为3的字符串
                continue;
            }

            // 统计从leftC到rightC中间的字符种类
            Set<Character> chars = new HashSet<>();
            for (int i = leftC + 1; i < rightC; i++) {
                chars.add(charArray[i]);
            }

            result += chars.size();
        }

        return result;
    }

    public static void main(String[] args) {
        String input;

        /*
         * 输入：s = "aabca"
         * 输出：3
         * 解释：长度为 3 的 3 个回文子序列分别是：
         * - "aba" ("aabca" 的子序列)
         * - "aaa" ("aabca" 的子序列)
         * - "aca" ("aabca" 的子序列)
         */
        input = "aabca";
        assert 3 == countPalindromicSubsequence(input);

        /*
         * 输入：s = "adc"
         * 输出：0
         * 解释："adc" 不存在长度为 3 的回文子序列。
         */
        input = "adc";
        assert 0 == countPalindromicSubsequence(input);

        /*
         * 输入：s = "bbcbaba"
         * 输出：4
         * 解释：长度为 3 的 4 个回文子序列分别是：
         * - "bbb" ("bbcbaba" 的子序列)
         * - "bcb" ("bbcbaba" 的子序列)
         * - "bab" ("bbcbaba" 的子序列)
         * - "aba" ("bbcbaba" 的子序列)
         */
        input = "bbcbaba";
        assert 4 == countPalindromicSubsequence(input);
    }
}
