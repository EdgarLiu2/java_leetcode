package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/subarrays-with-k-different-integers/">992. K 个不同整数的子数组</a>
 * Created by Edgar.Liu on 2023/1/15
 */
public class Solution0992_SubarraysWithKDifferentIntegers {

    static int subarraysWithKDistinct(int[] nums, int k) {
        // 含有不多于k不同数字的子数组数 - 含有不多于k-1不同数字的子数组数，等于刚好含有k个不同数字的子数组数
        return atMostWithKDistinct(nums, k) - atMostWithKDistinct(nums, k - 1);
    }

    /**
     * nums数组中，最多包含k不同数字的子数组数
     * @param nums 输入数组
     * @param k k个不同的数字
     * @return int，返回最多包含k不同数字的子数组数
     */
    static int atMostWithKDistinct(int[] nums, int k) {

        // 准备变量
        int L = 0;
        int R = 0;
        int[] debtMap = new int[nums.length + 1];
        int distinctKinds = 0;
        int answer = 0;

        while (R != nums.length) {
            // R当前指向的数字
            int currentR = nums[R];

            if (debtMap[currentR] == 0) {
                // 找到新的不同数字
                distinctKinds++;
            }
            debtMap[currentR]++;

            // 当找到k+1个不同数字时，开始结算
            while (distinctKinds > k) {
                // L向右移动一个，看是否依然达标
                int currentL = nums[L];
                L++;
                // L所指数字在债务表减一
                debtMap[currentL]--;
                if (debtMap[currentL] == 0) {
                    // 债务表中currentL减没了
                    distinctKinds--;
                }
            }

            // 从L到R-1，共R-L个子数组达标
            answer += R - L;

            // R向右
            R++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] input;

        /*
         * 输入：nums = [1,2,1,2,3], k = 2
         * 输出：7
         * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
         */
        input = new int[]{1, 2, 1, 2, 3};
        assert 7 == subarraysWithKDistinct(input, 2);

        /*
         * 输入：nums = [1,2,1,3,4], k = 3
         * 输出：3
         * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
         */
        input = new int[]{1, 2, 1, 3, 4};
        assert 3 == subarraysWithKDistinct(input, 3);

        /*
         * 输入：nums = [1,2], k = 1
         * 输出：2
         */
        input = new int[]{1, 2};
        assert 2 == subarraysWithKDistinct(input, 1);
    }
}
