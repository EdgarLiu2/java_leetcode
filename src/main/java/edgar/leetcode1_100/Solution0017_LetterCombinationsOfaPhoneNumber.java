package edgar.leetcode1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * 
 * @author Administrator
 *
 */
public class Solution0017_LetterCombinationsOfaPhoneNumber {
	
	public static List<String> letterCombinations(String digits) {
		if(digits == null || digits.isEmpty()) {
			return Collections.emptyList();
		}
		
		Map<String, String> numberCharacter = Map.of(
				"2", "abc",
				"3", "def",
				"4", "ghi",
				"5", "jkl",
				"6", "mon",
				"7", "pqrs",
				"8", "tuv",
				"9", "wxyz"
		);
		/*
		Map<String, String> numberCharacter = Map.ofEntries(
				Map.entry("2", "abc"),
				Map.entry("3", "def"),
				Map.entry("4", "ghi"),
				Map.entry("5", "jkl"),
				Map.entry("6", "mon"),
				Map.entry("7", "pqrs"),
				Map.entry("8", "tuv"),
				Map.entry("9", "wxyz")
		);
		*/
		List<String> result = new ArrayList<String>();
		for (char ch : digits.toCharArray()) {
			if (result.size() == 0) {
				for (char c : numberCharacter.get(String.valueOf(ch)).toCharArray() ) {
					result.add(String.valueOf(c));
				}
			} else {
				List<String> list = new ArrayList<String>();
				for (String elem : result) {
					for (char c : numberCharacter.get(String.valueOf(ch)).toCharArray() ) {
						list.add(elem + c);
					}
				}
				
				result = list;
			}
		}
		
		return result;
    }

	public static void main(String[] args) {
		String input = "";
		List<String> result;
		
		/*
		 * 输入："23"
		 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
		 */
		input = "23";
		List<String> expectedResult = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
		result = letterCombinations(input);
		System.out.println(result);
		
		assert expectedResult.size() == result.size();
		assert result.containsAll(expectedResult);
		
	}

}
