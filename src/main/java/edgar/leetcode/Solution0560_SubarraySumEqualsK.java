package edgar.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/description/">560. 和为 K 的子数组</a>
 *
 * @author Edgar.Liu
 * @since 2024/6/12 - 18:36
 */
public class Solution0560_SubarraySumEqualsK {
    static int subarraySum1(int[] nums, int k) {
        int ans = 0;

        // 两层循环，暴力破解
        for (int i = 0; i < nums.length; i++) {
            // 初始化变量
            int preSumToJ = 0;

            for(int j = i; j >= 0; j--) {
                preSumToJ += nums[j];

                if (preSumToJ == k) {
                    ans += 1;
                }

            }

        }

        return ans;
    }

    static int subarraySum2(int[] nums, int k) {
        int ans = 0;
        // 初始化变量，前缀和
        int preSum = 0;
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);

        // 考虑每个元素作为子数组的结尾
        for (int num : nums) {
            // 计算含i元素，当前pre数组所有元素的和
            preSum += num;

            if (prefixSumMap.containsKey(preSum - k)) {
                ans += prefixSumMap.get(preSum - k);
            }

            // 将当前前缀计入前缀数组
            prefixSumMap.put(preSum, prefixSumMap.getOrDefault(preSum, 0) + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] input;

        /*
         * 输入：nums = [1,1,1], k = 2
         * 输出：2
         */
        input = new int[]{1, 1, 1};
        assert 2 == subarraySum1(input, 2);
        assert 2 == subarraySum2(input, 2);

        /*
         * 输入：nums = [1,2,3], k = 3
         * 输出：2
         */
        input = new int[]{1, 2, 3};
        assert 2 == subarraySum1(input, 3);
        assert 2 == subarraySum2(input, 3);

        /*
         * 输入：nums = [100,1,2,100,2,100,1,1,1,100,2], k = 3
         * 输出：2
         */
        input = new int[]{100, 1, 2, 100, 2, 100, 1, 1, 1, 100, 2};
        assert 2 == subarraySum1(input, 3);
        assert 2 == subarraySum2(input, 3);

        /*
         * 输入：nums = [1,-1,0], k = 0
         * 输出：3
         */
        input = new int[]{1, -1, 0};
        assert 3 == subarraySum1(input, 0);
        assert 3 == subarraySum2(input, 0);
    }
}
