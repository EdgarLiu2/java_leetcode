package edgar.leetcode1_100;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. 罗马数字转整数
 * https://leetcode-cn.com/problems/roman-to-integer/
 * 
 * @author Administrator
 *
 */
public class Solution0013_RomanToInteger {
	
	public static int romanToInt1(String s) {
		// 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在Map中
        Map<String, Integer> romans = new HashMap<String, Integer>() {
			private static final long serialVersionUID = 1L;

			{
        		put("M", 1000);
        		put("CM", 900);
        		put("D", 500);
        		put("CD", 400);
        		put("C", 100);
        		put("XC", 90);
        		put("L", 50);
        		put("XL", 40);
        		put("X", 10);
        		put("IX", 9);
        		put("V", 5);
        		put("IV", 4);
        		put("I", 1);
        	}
        };
        
        int result = 0;
        
        for (int i = 0; i < s.length(); i++) {
        	if (i + 2 <= s.length()) {
        		String char2 = s.substring(i, i +2);
            	
            	if (romans.containsKey(char2)) {
            		result += romans.get(char2);
            		i++;
            		continue;
            	}
        	}
        	
    		result += romans.get(String.valueOf(s.charAt(i)));
        }
        
		return result;
    }
	
	public static int romanToInt(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		
		// 保留当前位的值，当遍历到下一位的时，对比保留值与遍历位的大小关系，再确定保留值为加还是减。最后一位做加法即可。
		int sum = 0;
		int preNum = getValue(s.charAt(0));
		
		for (int i = 1; i< s.length(); i++) {
			int num = getValue(s.charAt(i));
			
			if (num > preNum) {
				sum -= preNum;
			} else {
				sum += preNum;
			}
			
			preNum = num;
		}
		
		sum += preNum;
		
		return sum;
	}
	
	private static int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

	public static void main(String[] args) {
		// 输入: "III"，输出: 3
		assert 3 == romanToInt("III");
		
		// 输入: "IV"，输出: 4
		assert 4 == romanToInt("IV");
		
		// 输入: "IX"，输出: 9
		assert 9 == romanToInt("IX");
		
		// 输入: "LVIII"，输出: 58
		assert 58 == romanToInt("LVIII");
		
		// 输入: "MCMXCIV"，输出: 1994
		// 解释: M = 1000, CM = 900, XC = 90, IV = 4.
		assert 1994 == romanToInt("MCMXCIV");

	}

}
