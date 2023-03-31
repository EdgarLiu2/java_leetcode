package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/fibonacci-number/">509. 斐波那契数</a>
 * Created by Edgar.Liu on 2023/3/31
 */
public class Solution0509_FibonacciNumber {
    int fib(int n) {
        if (n <=1) {
            return n;
        }

        int[] result = new int[n + 1];
        result[1] = 1;

        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }

        return result[n];
    }
}
