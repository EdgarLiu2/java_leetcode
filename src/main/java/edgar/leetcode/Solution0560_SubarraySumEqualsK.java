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
        // 前缀和字典，记录目前为止，某个前缀和出现的次数
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        // 为了开头的答案
        prefixSumMap.put(0, 1);

        // 考虑每个元素i作为子数组的结尾，统计以i结尾的累加和为sum的子数组n个
        for (int num : nums) {
            // 计算含i元素num，累加和
            preSum += num;

            // 找到满足条件的前缀和，计入答案
            if (prefixSumMap.containsKey(preSum - k)) {
                ans += prefixSumMap.get(preSum - k);
            }

            // 将当前前缀和放入前缀和字典，如果之前已经存在，计数+1
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
