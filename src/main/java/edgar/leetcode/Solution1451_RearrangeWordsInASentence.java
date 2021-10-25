package edgar.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1451. 重新排列句子中的单词
 * https://leetcode-cn.com/problems/rearrange-words-in-a-sentence/
 * 
 * @author liuzhao
 *
 */
public class Solution1451_RearrangeWordsInASentence {
	
	public static String arrangeWords(String text) {
		// 处理特殊情况
		if (text == null || text.isBlank()) {
			return text;
		}
		if (text.trim().isBlank()) {
			return text;
		}
		
		
		// 按照空格进行分割
		String[] words = text.split(" ");
		// 第一个单词小写
		words[0] = words[0].toLowerCase();
		
		// 按照每个单词的长度对words进行排序，如果长度相同保持在原来句中的位置
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
			
		});
		
		// 第一个word首字母大写
		words[0] = words[0].substring(0, 1).toUpperCase() + words[0].substring(1);
		
		// 重新组合成一个String
//		StringBuilder buf = new StringBuilder(words[0]);
//		for (int i = 1; i < words.length; i++) {
//			buf.append(" ").append(words[i]);	
//		}
//		return buf.toString();
		return String.join(" ", words);
    }

	public static void main(String[] args) {

		String text;
		
		/*
		 * 输入：text = "Leetcode is cool"
		 * 输出："Is cool leetcode"
		 */
		text = "Leetcode is cool";
		assert "Is cool leetcode".equals(arrangeWords(text));

		/*
		 * 输入：text = "Keep calm and code on"
		 * 输出："On and keep calm code"
		 */
		text = "Keep calm and code on";
		assert "On and keep calm code".equals(arrangeWords(text));
		
		/*
		 * 输入：text = "To be or not to be"
		 * 输出："To be or to be not"
		 */
		text = "To be or not to be";
		assert "To be or to be not".equals(arrangeWords(text));
	}

}
