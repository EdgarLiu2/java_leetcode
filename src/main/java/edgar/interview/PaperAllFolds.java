package edgar.interview;

/**
 * Description:
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。 给定一个输入参数 N，代表纸条都从下边向上方连续对折 N 次。
 * 请从上到下打印所有折痕的方向。
 * 例如:
 * N=1时，打印down;
 * N=2时，打印:down down up
 *
 * @author Edgar.Liu
 * @since 2023/11/13 - 09:18
 */
public class PaperAllFolds {
    public static void printAllFolds(int n) {
        System.out.println("printAllFolds-折" + n + "次");
        // 第一次总是凹折痕
        doPrintAllFolds(n, 1, true);
    }

    private static void doPrintAllFolds(int n, int i, Boolean isConcave) {

        // 递归终止条件
        if (i > n) {
            return;
        }

        // 本次折痕上的新折痕应为凹折痕
        doPrintAllFolds(n, i + 1, true);
        // 打印本次折痕
        System.out.println(isConcave ? "凹折痕" : "凸折痕");
        // 本次折痕上的新折痕应为凸折痕
        doPrintAllFolds(n, i + 1, false);
    }

    public static void main(String[] args) {
        printAllFolds(1);
        printAllFolds(2);
    }
}
