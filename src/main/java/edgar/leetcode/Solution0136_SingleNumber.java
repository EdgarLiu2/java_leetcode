package edgar.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/single-number/">136. 只出现一次的数字</a>
 * Created by liuzhao on 2022/8/26
 */
public class Solution0136_SingleNumber {

    public static int singleNumber(int[] nums) {
        int result = 0;

        // 对所有数XOR，最后结果就是只出现一次的
        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    public static int singleNumber2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Set<Integer> result = new HashSet<>();

        for (int num : nums) {
            if (result.contains(num)) {
                result.remove(num);
            } else {
                result.add(num);
            }
        }

        assert result.size() == 1;
        Object o = result.toArray()[0];

        return (Integer)o;
    }

    public static void main(String[] args) {
        int[] input;

        /*
         * 输入: [2,2,1]
         * 输出: 1
         */
        input = new int[] {2,2,1};
        assert 1 == singleNumber(input);

        /*
         * 输入: [4,1,2,1,2]
         * 输出: 4
         */
        input = new int[] {4,1,2,1,2};
        assert 4 == singleNumber(input);
    }
}
