package edgar.leetcode1_100;

/**
 * 12. 整数转罗马数字
 * https://leetcode-cn.com/problems/integer-to-roman/
 * 
 * @author Administrator
 *
 */
public class Solution0012_IntegerToRoman {
	
	public static String intToRoman(int num) {
		// 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
        // 并且按照阿拉伯数字的大小降序排列，这是贪心选择思想
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
        	while (num >= nums[i]) {
        		result.append(romans[i]);
        		num -= nums[i];
        	}
        	
        	if (num == 0) {
        		break;
        	}
        }

		return result.toString();
    }

	public static void main(String[] args) {
		// 输入: 3，输出: "III"
		assert "III".equals(intToRoman(3));
		
		// 输入: 4，输出: "IV"
		assert "IV".equals(intToRoman(4));
		
		// 输入: 9，输出: "IX"
		assert "IX".equals(intToRoman(9));
		
		// 输入: 58，输出: "LVIII"
		// 解释: L = 50, V = 5, III = 3.
		assert "LVIII".equals(intToRoman(58));
	}

}
