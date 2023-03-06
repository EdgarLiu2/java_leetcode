package edgar.leetcode;

/**
 * 堆排序
 * Created by Edgar.Liu on 2023/3/6
 */
public class HeapSort {

    public void sort(int[] inputs) {
        // 构建大堆
        buildMaxHeap(inputs);

        // 开始排序
        int len = inputs.length - 1;
        while (len >= 0) {
            // 将0号位置元素移动到数组末尾
            swap(inputs, 0, len);
            // 数组长度减一
            len--;

            // 从0位置开始调整堆
            adjustHeap(inputs, 0, len);
        }
    }

    /**
     * 构建大堆
     * @param inputs 输入数组
     */
    private void buildMaxHeap(int[] inputs) {
        int n = inputs.length / 2 - 1;

        // 从n/2开始循环
        for (int i = n; i >= 0; i--) {
            // 左孩子
            int left = i * 2 + 1;
            // 右孩子
            int right = i * 2 + 2;

            // 左右孩子中较大的index
            int maxChild;
            if (right >= inputs.length) {
                maxChild = left;
            } else {
                maxChild =  (inputs[left] >= inputs[right]) ? left : right;
            }

            // 如果子节点maxChild的值，比i的要大
            if (inputs[maxChild] > inputs[i]) {
                // 将maxChild与i互换，故三个节点最大的值交互到i的位置
                swap(inputs, i, maxChild);

                // 对maxChild位置以下再进行调整
                adjustHeap(inputs, maxChild, inputs.length - 1);
            }
        }
    }

    private void adjustHeap(int[] inputs, int parent, int len) {
        // 左孩子
        int left = parent * 2 + 1;
        // 右孩子
        int right = parent * 2 + 2;

        // 1. 左右孩子都不存在
        if (left > len) {
            return;
        }

        // 2. 只有左孩子，且左孩子的值比idx要大
        if (right > len && inputs[left] > inputs[parent]) {
            // 需要将left与parent进行交换
            swap(inputs, left, parent);
            // 对left继续进行调整
            adjustHeap(inputs, left, len);
        }

        // 3. 左右孩子都有
        // 左右孩子中较大的index
        int maxChild;
        if (right >= len) {
            maxChild = left;
        } else {
            maxChild =  (inputs[left] >= inputs[right]) ? left : right;
        }

        // 如果子节点maxChild的值，比parent的要大
        if (inputs[maxChild] > inputs[parent]) {
            // 将maxChild与parent互换，故三个节点最大的值交互到parent的位置
            swap(inputs, parent, maxChild);

            // 对maxChild位置以下再进行调整
            adjustHeap(inputs, maxChild, len);
        }
    }

    /**
     * 交换数组中的i和j位置的元素
     * @param inputs 输入数组
     * @param i 第一个位置
     * @param j 第二个位置
     */
    private void swap(int[] inputs, int i, int j) {
        int tmp = inputs[i];
        inputs[i] = inputs[j];
        inputs[j] = tmp;
    }

    public static void main(String[] args) {
        int[] inputs;
        HeapSort heapSort = new HeapSort();

        inputs = new int[] {5, 2, 1, 8, 9};
        heapSort.sort(inputs);
        Util.printOneDimIntArray(inputs, inputs.length);

        inputs = new int[] {4, 7, 3, 2, 6, 7, 8, 32, 67, 23, 867, 23};
        heapSort.sort(inputs);
        Util.printOneDimIntArray(inputs, inputs.length);

        inputs = new int[] {5, 2, 1, 8, 9, 3, 7, 0, 4, 6};
        heapSort.sort(inputs);
        Util.printOneDimIntArray(inputs, inputs.length);
    }


}
