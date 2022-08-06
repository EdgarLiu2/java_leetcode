package edgar.leetcode;

/**
 * 8. 字符串转换整数 (atoi)
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 *
 * @author Administrator
 */
public class Solution0008_StringToIntegerAtoi {

    public static int myAtoi1(String str) {
        int i = 0;
        long result = 0;
        int len = str.length();
        boolean isPositive = true;

        while (i < len) {
            char c = str.charAt(i);

            if (c == ' ') {
                // 跳过开头的空格
                i++;
            } else {
                // 判断正负号
                if (c == '+') {
                    isPositive = true;
                    i++;
                    break;
                } else if (c == '-') {
                    isPositive = false;
                    i++;
                    break;
                }

                // 非字母直接返回0
                if (c < '0' || c > '9') {
                    return 0;
                }

                break;
            }
        }

        // 都是空格
        if (i == len) {
            return 0;
        }

        while (i < len) {
            char c = str.charAt(i++);
            if (c >= '0' && c <= '9') {
                // 是数字
                result = result * 10 + (c - '0');

                if (result > Integer.MAX_VALUE) {
                    // 超过INT_MAX or INT_MIN
                    return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            } else {
                // 遇到非数字就停
                break;
            }
        }

        return isPositive ? (int) result : -(int) result;
    }

    public static int myAtoi2(String str) {
        int i = 0;
        long result = 0;
        int len = str.length();
        boolean isPositive = true;

        // 跳过开头的空格
        while (i < len && str.charAt(i) == ' ')
            i++;

        // 都是空格
        if (i == len)
            return 0;

        // 判断正负号
        if (str.charAt(i) == '+') {
            isPositive = true;
            i++;
        } else if (str.charAt(i) == '-') {
            isPositive = false;
            i++;
        }

        while (i < len && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i++) - '0');

            if (result > Integer.MAX_VALUE) {
                // 超过INT_MAX or INT_MIN
                return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

        }

        return isPositive ? (int) result : -(int) result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 输入: "42"，输出: 42
        assert myAtoi2("42") == 42;

        // 输入: "   -42"，输出: -42
        assert myAtoi2("   -42") == -42;

        // 输入: "4193 with words"，输出: 4193
        assert myAtoi2("4193 with words") == 4193;

        // 输入: "-91283472332"，输出: -2147483648
        assert myAtoi2("-91283472332") == -2147483648;

        // 输入: "+"，输出: 0
        assert myAtoi2("+") == 0;

        // 输入: "  0000000000012345678"，输出: 12345678
        assert myAtoi2("  0000000000012345678") == 12345678;

        // 输入: "2147483648"，输出: 2147483647
        assert myAtoi2("2147483648") == 2147483647;
    }

}
