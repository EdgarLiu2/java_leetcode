package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/hamming-distance/">461. 汉明距离</a>
 * Created by Edgar.Liu on 2023/3/20
 */
public class Solution0461_HammingDistance {
    int hammingDistance(int x, int y) {
        // 先对x和y进行异或
        int xor = x ^ y;

        // 数xor中有几个1
        int distance = 0;

        while (xor != 0) {
            // 使用公式 X & (X - 1)，每次循环消除xor的一个1
            xor = xor & (xor - 1);
            distance++;
        }

        return distance;
    }
}
