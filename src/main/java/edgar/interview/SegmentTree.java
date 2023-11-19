package edgar.interview;

import java.util.Arrays;

/**
 * Description: 线段树解法，时间复杂度O(logN)
 *
 * @author Edgar.Liu
 * @since 2023/11/11 - 15:20
 */
public class SegmentTree {

    // 保存原始输入数据，values数组的第一个元素不用，下标从1开始
    private final int[] values;
    private final int MaxN;

    // 满二叉树结构，储存某一个区间的累加和信息
    private final int[] sumArray;
    // 满二叉树结构，储存某一个区间没有往下下发的累加信息
    private final int[] lazyArray;
    // 满二叉树结构，储存某一个区间没有往下下发的更新信息
    private final int[] changeArray;
    // 满二叉树结构，储存某一个区间是否有没有往下下发的更新信息
    private final boolean[] isUpdatedArray;

    public SegmentTree(int[] inputs) {
        // 数组最后一个元素下标
        MaxN = inputs.length + 1;
        values = new int[MaxN];

        System.arraycopy(inputs, 0, values, 1, inputs.length);

        // 需准备4N空间保存所有中间数据结构
        // 当输入数组元素个数为2的N次方时，准备2倍inputs.length就够了。但是当输入数组元素个数为2的N次方+1时，就需要准备4倍inputs.length
        // 均为满二叉树结构第一个元素放在idx=1，它的左孩子idx=2*i，右孩子idx=2*i + 1
        sumArray = new int[MaxN * 4];
        lazyArray = new int[MaxN * 4];
        changeArray = new int[MaxN * 4];
        isUpdatedArray = new boolean[MaxN * 4];

        build(1, inputs.length, 1);
    }

    /**
     * 初始化sum数组
     * 从left到right范围的sum，填入sumArray数组的rt位置
     */
    private void build(int left, int right, int rt) {
        if (left == right) {
            sumArray[rt] = values[left];
            return;
        }

        // left到right范围的中间节点下标
        int mid = (left + right) / 2;
        // 计算left到mid范围的累加和，存入rt的左孩子2 * rt
        build(left, mid, 2 * rt);
        // 计算mid+1到right范围的累加和，存入rt的右孩子2 * rt + 1
        build(mid + 1, right, 2 * rt + 1);
        // 基于rt的左孩子和右孩子的新sum，重新计算下标rt的sum
        reSum(rt);
    }

    @Override
    public String toString() {
        return "SegmentTree sumArray=" + Arrays.toString(sumArray) +
                " lazyArray=" + Arrays.toString(lazyArray) +
                " changeArray=" + Arrays.toString(changeArray);
    }

    /**
     * 基于rt的左孩子和右孩子的sum，重新计算下标rt的sum，更新回sumArray
     */
    private void reSum(int rt) {
        sumArray[rt] = sumArray[2 * rt] + sumArray[2 * rt + 1];
    }

    /**
     * 从taskFrom到taskTo范围，所有节点都增加value
     */
    public void add(int taskFrom, int taskTo, int value) {
        doAdd(taskFrom, taskTo, value, 1, MaxN - 1, 1);
    }

    private void doAdd(int taskFrom, int taskTo, int value, int left, int right, int rt) {

        if (taskFrom <= left && right <= taskTo) {
            // 当前范围(left, right)完全在任务范围内，说明可以通过lazy数组完全覆盖，不需要继续下发
            sumArray[rt] += (right - left + 1) * value;
            lazyArray[rt] += value;
            return;
        }

        // 任务需要往下下发，即任务范围(taskFrom, taskTo)并没有完成包住(left, right)
        int mid = (left + right) / 2;

        // 下发之前未下发的懒更新和懒增加任务
        doPushDown(mid - left + 1, right - mid, rt);

        // 判断是否需要将任务下发到左孩子。如果任务左边界在mid左侧，说明左孩子需要知道本次任务。
        if (taskFrom <= mid) {
            doAdd(taskFrom, taskTo, value, left, mid, rt * 2);
        }

        // 判断是否需要将任务下发到右孩子。如果任务右边界在mid右侧，说明右孩子需要知道本次任务。
        if (mid < taskTo) {
            doAdd(taskFrom, taskTo, value, mid + 1, right, rt * 2 + 1);
        }

        // 左孩子和右孩子都针对任务更新sum，重新计算当前下标rt的sum
        reSum(rt);
    }

    /**
     * 下发之前未下发的lazy和change懒任务，父范围发给左右两个子范围
     * @param leftChildNum 左侧孩子数
     * @param rightChildNum 右侧孩子数
     * @param rt 当前下发lazy任务的在lazyArray中的位置
     */
    private void doPushDown(int leftChildNum, int rightChildNum, int rt) {
        int leftChildRT = rt * 2;
        int rightChildRT = rt * 2 + 1;

        // 懒更新任务
        if (isUpdatedArray[rt]) {
            // 懒更新任务需要下发到左孩子
            isUpdatedArray[leftChildRT] = true;
            changeArray[leftChildRT] = changeArray[rt];
            // 懒更新任务需要下发到右孩子
            isUpdatedArray[rightChildRT] = true;
            changeArray[rightChildRT] = changeArray[rt];

            // 左孩子和右孩子的懒增加都清空
            lazyArray[leftChildRT] = 0;
            lazyArray[rightChildRT] = 0;

            // 左孩子和右孩子的累加和重新计算
            sumArray[leftChildRT] = changeArray[rt] * leftChildNum;
            sumArray[rightChildRT] = changeArray[rt] * rightChildNum;

            isUpdatedArray[rt] = false;
        }

        // 懒增加任务
        if (lazyArray[rt] != 0) {
            // 左孩子下发lazy任务
            lazyArray[leftChildRT] += lazyArray[rt];
            sumArray[leftChildRT] += lazyArray[rt] * leftChildNum;

            // 右孩子下发lazy任务
            lazyArray[rightChildRT] += lazyArray[rt];
            sumArray[rightChildRT] += lazyArray[rt] * rightChildNum;

            // 下发完成，清空当前rt节点上的lazy任务
            lazyArray[rt] = 0;
        }
    }

    /**
     * 从taskFrom到taskTo范围，所有节点都设置成value
     */
    public void update(int taskFrom, int taskTo, int value) {
        doUpdate(taskFrom, taskTo, value, 1, MaxN - 1, 1);
    }

    private void doUpdate(int taskFrom, int taskTo, int value, int left, int right, int rt) {
        if (taskFrom <= left && right <= taskTo) {
            // 当前范围(left, right)完全在任务范围内，说明可以通过change数组完全覆盖，不需要继续下发
            // 只有在isUpdatedArray=true，changeArray才有用
            isUpdatedArray[rt] = true;
            changeArray[rt] = value;
            // 更新节点值后，sum重新计算
            sumArray[rt] = (right - left + 1) * value;
            // 之前的lazy都作废
            lazyArray[rt] = 0;
            return;
        }

        // 任务需要往下下发，即任务范围(taskFrom, taskTo)并没有完成包住(left, right)
        int mid = (left + right) / 2;

        // 下发之前未下发的懒更新和懒增加任务
        doPushDown(mid - left + 1, right - mid, rt);

        // 判断是否需要将任务下发到左孩子。如果任务左边界在mid左侧，说明左孩子需要知道本次任务。
        if (taskFrom <= mid) {
            doUpdate(taskFrom, taskTo, value, left, mid, rt * 2);
        }

        // 判断是否需要将任务下发到右孩子。如果任务右边界在mid右侧，说明右孩子需要知道本次任务。
        if (mid < taskTo) {
            doUpdate(taskFrom, taskTo, value, mid + 1, right, rt * 2 + 1);
        }

        // 左孩子和右孩子都针对任务更新sum，重新计算当前下标rt的sum
        reSum(rt);
    }

    /**
     * 从taskFrom到taskTo范围，所有节点value的累加和
     */
    public int query(int taskFrom, int taskTo) {
        return doQuery(taskFrom, taskTo, 1, MaxN - 1, 1);
    }

    private int doQuery(int taskFrom, int taskTo, int left, int right, int rt) {
        if (taskFrom <= left && right <= taskTo) {
            // 当前范围(left, right)完全在任务范围内，说明可以通过sumArray数组完全覆盖，不需要继续下发
            return sumArray[rt];
        }

        // 任务需要往下下发，即任务范围(taskFrom, taskTo)并没有完成包住(left, right)
        int mid = (left + right) / 2;

        // 下发之前未下发的懒更新和懒增加任务
        doPushDown(mid - left + 1, right - mid, rt);

        int result = 0;

        // 判断是否需要将任务下发到左孩子。如果任务左边界在mid左侧，说明左孩子需要知道本次任务。
        if (taskFrom <= mid) {
            result += doQuery(taskFrom, taskTo, left, mid, rt * 2);
        }

        // 判断是否需要将任务下发到右孩子。如果任务右边界在mid右侧，说明右孩子需要知道本次任务。
        if (mid < taskTo) {
            result += doQuery(taskFrom, taskTo, mid + 1, right, rt * 2 + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] inputs;
        SegmentTree tree;

        // Test 1
        inputs = new int[]{4, 3, 5, 2};
        tree = new SegmentTree(inputs);
        System.out.println(tree);

        // Test 2
        inputs = new int[]{0, 0, 0, 0};
        tree = new SegmentTree(inputs);
        tree.add(1, 2, 5);
        tree.add(3, 4, 7);
        tree.add(1, 4, 2);
        tree.add(1, 3, 6);
        System.out.println(tree);

        // Test 3
        inputs = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        tree = new SegmentTree(inputs);
        tree.add(1, 4, 2);
        tree.update(5, 8, 1);
        tree.update(1, 8, 2);
        tree.add(1, 8, 3);
        tree.add(1, 8, 5);
        System.out.println(tree);
    }
}

/**
 * 暴力求解算法，时间复杂度O(N)
 */
class SimpleSolution {
    // values数组的第一个元素不用，下标从1开始
    private final int[] values;

    public SimpleSolution(int[] inputs) {

        values = new int[inputs.length + 1];

        System.arraycopy(inputs, 0, values, 1, inputs.length);
    }

    /**
     * 从fromIndex到toIndex范围，所有节点都增加value
     */
    public void add(int fromIndex, int toIndex, int newValue) {
        for (int i = fromIndex; i <= toIndex; i++) {
            values[i] += newValue;
        }
    }

    /**
     * 从fromIndex到toIndex范围，所有节点都设置成value
     */
    public void update(int fromIndex, int toIndex, int newValue) {
        for (int i = fromIndex; i <= toIndex; i++) {
            values[i] = newValue;
        }
    }

    /**
     * 从fromIndex到toIndex范围，所有节点value的累加和
     */
    public int getSum(int fromIndex, int toIndex) {
        int sum = 0;

        for (int i = fromIndex; i <= toIndex; i++) {
            sum += values[i];
        }

        return sum;
    }
}