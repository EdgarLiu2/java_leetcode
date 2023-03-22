package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/add-strings/">415. 字符串相加</a>
 * Created by Edgar.Liu on 2023/3/22
 */
public class Solution0415_AddStrings {
    static String addStrings(String num1, String num2) {

        // 先异常处理
        if (num1 == null || num1.isBlank()) {
            return num2;
        }
        if (num2 == null || num2.isBlank()) {
            return num1;
        }

        // 将num1和num2的长度补齐
        int len1_2 = num1.length() - num2.length();
        if (len1_2 > 0) {
            // num1比num2长，给num2的前面补0
            num2 = "0".repeat(len1_2) + num2;
        } else {
            // num2比num1长，给num1的前面补0
            num1 = "0".repeat(-len1_2) + num1;
        }

        // 结果保存
        int carry = 0;
        StringBuilder result = new StringBuilder();

        for (int i = num1.length() - 1; i >= 0 || carry > 0; i--) {
            int a = i < 0 ? 0 : num1.charAt(i) - '0';
            int b = i < 0 ? 0 : num2.charAt(i) - '0';
            int sum = a + b + carry;

            result.append(sum % 10);
            carry = sum / 10;
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String num1;
        String num2;

        /*
         * 输入：num1 = "11", num2 = "123"
         * 输出："134"
         */
        num1 = "11";
        num2 = "123";
        assert "134".equals(addStrings(num1, num2));

        /*
         * 输入：num1 = "456", num2 = "77"
         * 输出："533"
         */
        num1 = "456";
        num2 = "77";
        assert "533".equals(addStrings(num1, num2));

        /*
         * 输入：num1 = "0", num2 = "0"
         * 输出："0"
         */
        num1 = "0";
        num2 = "0";
        assert "0".equals(addStrings(num1, num2));

        /*
         * 输入：num1 = "1", num2 = "9"
         * 输出："10"
         */
        num1 = "1";
        num2 = "9";
        assert "10".equals(addStrings(num1, num2));
    }
}
