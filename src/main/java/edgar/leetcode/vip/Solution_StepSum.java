package edgar.leetcode.vip;

/**
 * Created by Edgar.Liu on 2023/1/8
 */
public class Solution_StepSum {

    /**
     * 如果输入数字为某个数的[Step Sum]，返回true，否则返回false
     * @param number 输入数字
     * @return 如果是某个数的[Step Sum]，返回true
     */
    static boolean isStepSumOfNum(int number) {

//        int l = 1;
        int l = (int)(number * 0.9);
        int r = number;

        while(l <= r) {
            // 中间的数，防止int越界
            int mid = l + (r - l) / 2;
            int stepSum = computeStepSum(mid);

            if (stepSum == number) {
                System.out.printf("%d is StepSum of %d\n", number, mid);
                return true;
            }

            if (stepSum < number) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return false;
    }

    /**
     * 计算一个数的[Step Sum]
     * @param n 输入数
     * @return 返回n对应的[Step Sum]
     */
    static int computeStepSum(int n) {
        int stepSum = 0;

        while(n != 0) {
            stepSum += n;
            n /= 10;
        }

        return stepSum;
    }

    public static void main(String[] args) {
        assert isStepSumOfNum(754);
        assert isStepSumOfNum(14);
        assert isStepSumOfNum(111);
    }
}
