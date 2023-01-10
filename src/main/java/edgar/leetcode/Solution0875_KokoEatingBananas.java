package edgar.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/koko-eating-bananas/">875. 爱吃香蕉的珂珂</a>
 * Created by Edgar.Liu on 2023/1/8
 */
public class Solution0875_KokoEatingBananas {
    static int minEatingSpeed(int[] piles, int h) {
        if (piles == null || piles.length > h) {
            return 0;
        }

        // 找到最多香蕉的数目
        int max = Arrays.stream(piles).max().orElse(0);
        int min = 1;
        int answer = 0;

        while (min <= max) {
            // 二分查找，从中位数开始
            int mid = min + (max - min) / 2;

            // 计算以mid的速度吃香蕉，需要吃多久
            if (hours(piles, mid) <= h) {
                // 以mid速度，可以吃完，找到一个备选答案
                answer = mid;

                // 尝试更小速度
                max = mid - 1;
            } else {
                // 以mid速度吃不完，加快
                min = mid + 1;
            }
        }

        return answer;
    }

    static long hours(int[] piles, int speed) {
        long hours = 0;
        // Arrays.stream(piles).map(i -> ceil(i, speed)).sum()
        for (int pile : piles) {
            hours += ceil(pile, speed);
        }
        return hours;
    }


    static int ceil(int m, int n) {
        // m 除以 n 向上取整
        return (m + n - 1) / n;
    }

    public static void main(String[] args) {
        int[] input;

        /*
         * 输入：piles = [3,6,7,11], h = 8
         * 输出：4
         */
        input = new int[]{3, 6, 7, 11};
        assert 4 == minEatingSpeed(input, 8);

        /*
         * 输入：piles = [30,11,23,4,20], h = 5
         * 输出：30
         */
        input = new int[]{30, 11, 23, 4, 20};
        assert 30 == minEatingSpeed(input, 5);

        /*
         * 输入：piles = [30,11,23,4,20], h = 6
         * 输出：23
         */
        input = new int[]{30, 11, 23, 4, 20};
        assert 23 == minEatingSpeed(input, 6);

        /*
         * 输入：piles = [805306368,805306368,805306368], h = 1000000000
         * 输出：3
         */
        input = new int[]{805306368, 805306368, 805306368};
        assert 3 == minEatingSpeed(input, 1000000000);
    }
}
