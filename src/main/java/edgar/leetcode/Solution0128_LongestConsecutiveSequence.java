package edgar.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/">128. 最长连续序列</a>
 * Created by liuzhao on 2022/8/15
 */
public class Solution0128_LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {

        if (nums.length < 2) {
            return nums.length;
        }

        // 第一次遍历，全加入Set
        Set<Integer> sets = new HashSet<>();
        for (int num : nums) {
            sets.add(num);
        }

        // 第二次遍历，找最长序列
        int maxLongSequence = 0;

        for (int num : nums) {
            if (sets.contains(num - 1)) {
                // 当前数字前序在集合中，算在前序数字里面
                continue;
            }

            int current = num;
            int longSequence = 0;
            while (sets.contains(current)) {
                longSequence++;
                current++;
            }

            maxLongSequence = Math.max(maxLongSequence, longSequence);
        }

        return maxLongSequence;
    }

    public static void main(String[] args) {

        int[] input;

        /*
         * 输入：nums = [100,4,200,1,3,2]
         * 输出：4
         * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
         */
        input = new int[]{100,4,200,1,3,2};
        assert 4 == longestConsecutive(input);

        /*
         * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
         * 输出：9
         */
        input = new int[]{0,3,7,2,5,8,4,6,0,1};
        assert 9 == longestConsecutive(input);
    }
}
