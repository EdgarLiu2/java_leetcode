package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/counting-bits/">338. 比特位计数</a>
 * Created by Edgar.Liu on 2023/3/19
 */
public class Solution0338_CountingBits {
    int[] countBits(int n) {
        // 定义结果数组
        int[] result = new int[n + 1];
        // 0中1个个数肯定是0
        result[0] = 0;

        for(int i = 1; i <= n; i++) {
            if ((i & 1) == 1) {
                // 如果是奇数，i中1的个数，比i-1（偶数）中1的个数多1
                result[i] = result[i-1] + 1;
            } else {
                // 如果是偶数，i中1的个数与i/2中1的个数相同
                result[i] = result[i >> 1];
            }
        }

        return result;
    }
}
