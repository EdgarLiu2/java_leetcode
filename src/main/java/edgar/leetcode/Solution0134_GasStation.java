package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/gas-station/">134. 加油站</a>
 * Created by liuzhao on 2022/8/24
 */
public class Solution0134_GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length != cost.length) {
            return -1;
        }

        int[] netGas = new int[gas.length];
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < gas.length; i++) {
            netGas[i] = gas[i] - cost[i];
            totalGas += gas[i];
            totalCost += cost[i];
        }
//        Util.printOneDimIntArray(netGas, netGas.length);

        if (totalGas < totalCost) {
            // 一定无解
            return -1;
        }

        int currentGas = 0;
        int start = 0;
        for (int i = 0; i < netGas.length; i++) {
            currentGas += netGas[i];

            if (currentGas < 0) {
                // 当到第i个站时，剩余gas为负，就从i的下一个节点重新探索
                currentGas = 0;
                start = i + 1;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        int[] gas, cost;

        /*
         * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
         * 输出: 3
         * 解释:
         * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
         * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
         * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
         * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
         * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
         * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
         * 因此，3 可为起始索引。
         */
        gas = new int[]{1,2,3,4,5};
        cost = new int[]{3,4,5,1,2};
        assert 3 == canCompleteCircuit(gas, cost);

        /*
         * 输入: gas = [2,3,4], cost = [3,4,3]
         * 输出: -1
         * 解释:
         * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
         * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
         * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
         * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
         * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
         * 因此，无论怎样，你都不可能绕环路行驶一周。
         */
        gas = new int[]{2,3,4};
        cost = new int[]{3,4,3};
        assert -1 == canCompleteCircuit(gas, cost);
    }
}
