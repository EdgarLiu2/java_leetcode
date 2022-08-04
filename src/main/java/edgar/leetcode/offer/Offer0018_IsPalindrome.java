package edgar.leetcode.offer;

/**
 * 剑指 Offer II 018. 有效的回文
 * https://leetcode.cn/problems/XltzEq/
 *
 * Created by liuzhao on 2022/8/4
 */
public class Offer0018_IsPalindrome {

    public static boolean isPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }

        // 去掉原始字符串中的特殊字符，并转成想小写
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                builder.append(Character.toLowerCase(c));
            }
        }

        char[] charArray = builder.toString().toCharArray();
        if (charArray.length < 2) {
            return true;
        }

        // 使用双指针检查是否为有效回文
        int left = 0;
        int right = charArray.length - 1;

        // 当left与right重合时，说明是有效回文
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static boolean isPalindrome2(String s) {

        if (s.length() < 2) {
            return true;
        }

        char[] charArray = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        // 当left与right重合时，说明是有效回文
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(charArray[left])) {
                // 当left指向不是非字符时，向右移动
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(charArray[right])) {
                // 当right指向不是非字符时，向左移动
                right--;
            }

            if (Character.isLetterOrDigit(charArray[left]) &&
                    Character.isLetterOrDigit(charArray[right]) &&
                    Character.toLowerCase(charArray[left]) != Character.toLowerCase(charArray[right])) {
                // 头尾字符不等，不是回文
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {

        String input;

        /**
         * 输入: s = "A man, a plan, a canal: Panama"
         * 输出: true
         * 解释："amanaplanacanalpanama" 是回文串
         */
        input = "A man, a plan, a canal: Panama";
        assert isPalindrome(input);

        /**
         * 输入: s = "race a car"
         * 输出: false
         * 解释："raceacar" 不是回文串
         */
        input = "race a car";
        assert !isPalindrome(input);

        /**
         * 输入: s = ".,"
         * 输出: true
         */
        input = ".,";
        assert isPalindrome(input);

        /**
         * 输入: s = "0P"
         * 输出: false
         */
        input = "0P";
        assert !isPalindrome(input);
    }
}
