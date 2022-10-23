package edgar.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/single-number-ii/">137. 只出现一次的数字 II</a>
 * Created by Edgar.Liu on 2022/10/21
 */
public class Solution0137_SingleNumberII {

    public static int singleNumber(int[] nums) {

        int[] cnts = new int[32];

        for (int num : nums) {
            // 依次遍历每个数
            for (int i = 0; i < cnts.length; i++) {
                if ((num >> i & 1) == 1) {
                    cnts[i]++;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < cnts.length; i++) {
            if (cnts[i] % 3 == 1) {
                answer += 1 << i;
            }
        }

        return answer;
    }

    public static int singleNumber2(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 遍历找到count == 1的数字
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] nums;

        /*
         * 输入：nums = [2,2,3,2]
         * 输出：3
         */
        nums = new int[]{2,2,3,2};
        assert 3 == singleNumber(nums);

        /*
         * 输入：nums = [0,1,0,1,0,1,99]
         * 输出：99
         */
        nums = new int[]{0,1,0,1,0,1,99};
        assert 99 == singleNumber(nums);

        /*
         * 输入：nums = [-2,-2,1,1,4,1,4,4,-4,-2]
         * 输出：-4
         */
        nums = new int[]{-2,-2,1,1,4,1,4,4,-4,-2};
        assert -4 == singleNumber(nums);
    }
}
