package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/">448. 找到所有数组中消失的数字</a>
 * Created by Edgar.Liu on 2023/2/3
 */
public class Solution0448_FindAllNumbersDisappearedInAnArray {
    static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        // 处理特殊情况
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 遍历到num，就将数组中num-1位置的数置为负数，以表示num出现过
        for (int num : nums) {
            int current =  num > 0 ? num : -num;
            if (nums[current - 1] > 0) {
                nums[current - 1] = -nums[current - 1];
            }
        }

        // 如果 i 位置的数还是正数，表示源数组缺少 i+1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] inputs;
        List<Integer> result;

        /*
         * 输入：nums = [4,3,2,7,8,2,3,1]
         * 输出：[5,6]
         */
        inputs = new int[]{4, 3 ,2, 7, 8, 2, 3, 1};
        result = findDisappearedNumbers(inputs);
        assert List.of(5, 6).equals(result);

        /*
         * 输入：nums = [1,1]
         * 输出：[2]
         */
        inputs = new int[]{1, 1};
        result = findDisappearedNumbers(inputs);
        assert List.of(2).equals(result);
    }
}
