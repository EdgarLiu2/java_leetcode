package edgar.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/palindrome-partitioning/">131. 分割回文串</a>
 * Created by liuzhao on 2022/8/19
 */
public class Solution0131_PalindromePartitioning {
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        char[] charArray = s.toCharArray();

        /* 先用动态规划，计算从i到j是否为回文 */
        boolean[][] isPalindrome = new boolean[charArray.length][charArray.length];
        // 对角线都是回文
        for (int i = 0; i < charArray.length; i++) {
            isPalindrome[i][i] = true;
        }
        for (int right = 0; right < charArray.length; right++) {
            for (int left = 0; left < right; left++) {
                // 对角线都是回文，否则就看left和right位置是否一样
                if (right - left < 2) {
                    isPalindrome[left][right] = (charArray[left] == charArray[right]);
                } else {
                    isPalindrome[left][right] = isPalindrome[left + 1][right - 1] && (charArray[left] == charArray[right]);
                }
            }
        }
        Util.printTwoDimArray(isPalindrome);

        Deque<String> path = new ArrayDeque<>();
        dfs(s, 0, isPalindrome, path, result);

        return result;
    }

    static void dfs(String s, int start, boolean[][] isPalindrome, Deque<String> path, List<List<String>> result) {
        if (start == s.length()) {
            // 遍历到数组尾部
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (isPalindrome[start][i]) {
                // 从start到i为回文，找到一个有效分支点
                path.addLast(s.substring(start, i+1));
                dfs(s, i + 1, isPalindrome, path, result);
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> result;

        /*
         * 输入：s = "aab"
         * 输出：[["a","a","b"],["aa","b"]]
         */
        result = partition("aab");
        for (List<String> list : result) {
            System.out.println(String.join(", ", list));
        }

        /*
         * 输入：s = "a"
         * 输出：[["a"]]
         */
        result = partition("a");
        for (List<String> list : result) {
            System.out.println(String.join(", ", list));
        }
    }
}
