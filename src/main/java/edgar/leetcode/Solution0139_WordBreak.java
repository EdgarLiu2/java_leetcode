package edgar.leetcode;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/word-break/">139. 单词拆分</a>
 * Created by Edgar.Liu on 2022/11/11
 */
public class Solution0139_WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {

        // List转Set，便于查询是否包含
        Set<String> words = new HashSet<>(wordDict);

        // 定义 dp[i]表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词
        boolean[] dp = new boolean[s.length() + 1];
        // 初始化 dp[0]=true，空字符可以被表示
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for(int j = i - 1; j >= 0; j--) {
                String subStr = s.substring(j, i);

                // 看 s[0..j−1] 组成的字符串和 s[j..i−1] 组成的字符串 是否都合法。
                if (dp[j] && words.contains(subStr)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {

        String input;
        List<String> wordDict;

        /*
         * 输入: s = "leetcode", wordDict = ["leet", "code"]
         * 输出: true
         * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
         */
        input = "leetcode";
        wordDict = List.of("leet", "code");
        assert wordBreak(input, wordDict);

        /*
         * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
         * 输出: true
         * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
         *      注意，你可以重复使用字典中的单词。
         */
        input = "applepenapple";
        wordDict = List.of("apple", "pen");
        assert wordBreak(input, wordDict);

        /*
         * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
         * 输出: false
         */
        input = "catsandog";
        wordDict = List.of("cats", "dog", "sand", "and", "cat");
        assert !wordBreak(input, wordDict);
    }
}
