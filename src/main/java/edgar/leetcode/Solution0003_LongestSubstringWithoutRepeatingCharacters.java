package edgar.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 3. 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 
 * @author Administrator
 *
 */
public class Solution0003_LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String s) {
		int maxLength = 0;
		char[] charArray = s.toCharArray();
		Set<Character> chars = new HashSet<>();
		// 向右扫描的指针
		int rk = 0;

		for (int i = 0; i < s.length(); i++) {
			// 先从集合移除i-1的元素
			if (i != 0) {
				chars.remove(charArray[i-1]);
			}

			while (rk < s.length() && !chars.contains(charArray[rk])) {
				chars.add(charArray[rk]);
				rk++;
			}

			maxLength = Math.max(maxLength, rk - i);
		}

		return maxLength;
	}

    public static int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        int j = 0;
        int strLength = s.length();
        Map<Character, Integer> chars = new HashMap<Character, Integer>();

        for(int i = 0; i < strLength; i++) {
        	char currentChar = s.charAt(i);
        	
        	if (chars.containsKey(currentChar)) {
        		j = Math.max(chars.get(currentChar), j);
        	}
        	
        	chars.put(currentChar, i+1);
        	maxLength = Math.max(maxLength, i + 1 - j);
        }

        return maxLength;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 
		 * "abcabcbb: 3"
		 * "bbbbb: 1"
		 * "pwwkew: 3"
		 * "abba": 2
		 * " ": 1
		 * 
		 */
		String[] inputs = new String[] {"abcabcbb", "bbbbb", "pwwkew", "abba", " "};
		int[] expected = new int[] {3, 1, 3, 2, 1};
		for (int i = 0; i < inputs.length; i++) {
			assert expected[i] == lengthOfLongestSubstring(inputs[i]);
		}
	}

}
