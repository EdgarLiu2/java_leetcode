package edgar.leetcode.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/">剑指 Offer 10- I. 斐波那契数列</a>
 *
 * @author Edgar.Liu
 * @since 2023/1/30 - 23:29
 */
public class Offer0010_FeiBoNaQiShuLieLcof {

    private final Map<Integer, Integer> localCache = new HashMap<>();

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        // f(n-1)
        int f1;
        // f(n-2)
        int f2;

        if (localCache.containsKey(n - 1)) {
            f1 = localCache.get(n - 1);
        } else {
            f1 = fib(n - 1);
            localCache.put(n - 1, f1);
        }

        if (localCache.containsKey(n - 2)) {
            f2 = localCache.get(n - 2);
        } else {
            f2 = fib(n - 2);
            localCache.put(n - 2, f2);
        }

        return (f1 + f2) % 1000000007;
    }
}
