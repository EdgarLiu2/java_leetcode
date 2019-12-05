/**
 * 
 */
package edgar.leetcode;

import java.util.HashMap;
import java.util.Map;

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
		for(String str : inputs) {
			System.out.printf("%s: %d\n", str, lengthOfLongestSubstring(str));
		}
	}

}
