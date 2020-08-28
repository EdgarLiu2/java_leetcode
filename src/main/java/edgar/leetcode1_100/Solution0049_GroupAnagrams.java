package edgar.leetcode1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 * 
 * @author liuzhao
 *
 */
public class Solution0049_GroupAnagrams {
	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		
		if (strs.length == 0) {
			return Collections.emptyList();
		} else if (strs.length == 1) {
			result.add(Arrays.asList(strs));
			return result;
		}
		
		Map<String, List<String>> groupAnagrms = new HashMap<>();
		int[] count = new int[26];

		for (String word : strs) {
			Arrays.fill(count, 0);
			for (char c : word.toCharArray()) {
				count[c - 'a'] += 1;
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 26; i++) {
				sb.append('#');
				sb.append(count[i]);
			}
			String key = sb.toString();

			if (!groupAnagrms.containsKey(key)) {
				groupAnagrms.put(key, new ArrayList<String>());
			}
			groupAnagrms.get(key).add(word);
			
		}
		
		result.addAll(groupAnagrms.values());
		
		return result;
	}
	
	public static List<List<String>> groupAnagrams2(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		
		if (strs.length == 0) {
			return Collections.emptyList();
		} else if (strs.length == 1) {
			result.add(Arrays.asList(strs));
			return result;
		}
		
		Map<String, List<String>> groupAnagrms = new HashMap<>();

		for (String word : strs) {
			char[] ca = word.toCharArray();
			Arrays.sort(ca);
			String key = String.valueOf(ca);
			
			if (groupAnagrms.containsKey(key)) {
				groupAnagrms.get(key).add(word);
			} else {
				groupAnagrms.put(key, new ArrayList<String>() {{
					add(word);
				}});
			}
		}
		
		result.addAll(groupAnagrms.values());
		
		return result;
	}
	
	public static void main(String[] args) {
		String[] words;
		List<List<String>> result;

		/*
		 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
		 * 输出:
		 * [
		 *   ["ate","eat","tea"],
		 *   ["nat","tan"],
		 *   ["bat"]
		 * ]
		 */
		words = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
		result = groupAnagrams(words);
		for (List<String> list : result) {
			System.out.println(list);
		}
		
		/*
		 * 输入：[""]
		 * 输出：[[""]]
		 */
		words = new String[] {""};
		result = groupAnagrams(words);
		for (List<String> list : result) {
			System.out.println(list);
		}
	}
}
