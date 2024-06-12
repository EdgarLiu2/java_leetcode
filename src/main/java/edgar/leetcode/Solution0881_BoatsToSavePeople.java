package edgar.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/boats-to-save-people/description/">881. 救生艇</a>
 *
 * @author Edgar.Liu
 * @since 2024/6/12 - 09:26
 */
public class Solution0881_BoatsToSavePeople {
    static int numRescueBoats(int[] people, int limit) {
        // 先对输入进行排序
        Arrays.sort(people);

        // 确定阈值为limit的一半
        double threshold = limit / 2.0;

        // 遍历people，找到最后一个<=threshold的位置
        int leftIdx = -1;
        for (int i = 0; i < people.length; i++) {
            if (people[i] <= threshold) {
                leftIdx = i;
            } else {
                // i位置的值已经大于threshold，退出循环
                break;
            }
        }
        int rightIdx = leftIdx + 1;

        // 最后结果由三部分组成
        int leftRightMatchNum = 0;
        int leftNotMatchNum = 0;
        int rightNotMatchNum = 0;

        while (leftIdx >= 0 && rightIdx < people.length) {
            if (people[leftIdx] + people[rightIdx] > limit) {
                // leftIdx和rightIdx的和超过limit时，无法拼船，leftIdx向左移动
                leftNotMatchNum++;
                leftIdx--;
            } else {
                // leftIdx和rightIdx的和未超过limit，可以凑一船
                leftRightMatchNum++;
                leftIdx--;
                rightIdx++;
            }
        }

        int ans = leftRightMatchNum;
        leftIdx++;

        if (leftIdx + leftNotMatchNum > 0) {
            // 左侧人未安排完，两个人拼一艘
            ans += Math.ceil((leftIdx + leftNotMatchNum) / 2.0);
        }

        if (rightIdx != people.length) {
            // 右侧人未安排完，一个人一艘船
            ans += people.length - rightIdx;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] input;

        /*
         * 输入：people = [1,2], limit = 3
         * 输出：1
         * 解释：1 艘船载 (1, 2)
         */
        input = new int[]{1, 2};
        assert 1 == numRescueBoats(input, 3);

        /*
         * 输入：people = [3,2,2,1], limit = 3
         * 输出：3
         * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
         */
        input = new int[]{3, 2, 2, 1};
        assert 3 == numRescueBoats(input, 3);

        /*
         * 输入：people = [3,5,3,4], limit = 5
         * 输出：4
         * 解释：4 艘船分别载 (3), (3), (4), (5)
         */
        input = new int[]{3, 5, 3, 4};
        assert 4 == numRescueBoats(input, 5);

        /*
         * 输入：people = [2,4], limit = 5
         * 输出：2
         */
        input = new int[]{2, 4};
        assert 2 == numRescueBoats(input, 5);

        /*
         * 输入：people = [3,2,3,2,2], limit = 6
         * 输出：3
         */
        input = new int[]{3, 2, 3, 2, 2};
        assert 3 == numRescueBoats(input, 6);

        /*
         * 输入：people = [3,1,7], limit = 7
         * 输出：2
         */
        input = new int[]{3, 1, 7};
        assert 2 == numRescueBoats(input, 7);
    }
}
