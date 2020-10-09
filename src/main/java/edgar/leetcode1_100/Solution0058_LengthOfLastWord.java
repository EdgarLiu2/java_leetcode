package edgar.leetcode1_100;

/**
 * 58. 最后一个单词的长度
 * https://leetcode-cn.com/problems/length-of-last-word/
 * 
 * @author liuzhao
 *
 */
public class Solution0058_LengthOfLastWord {
	
	public static int lengthOfLastWord(String s) {
		if (s == null || s.isBlank()) {
			return 0;
		}
		
		int lastWordEnd = s.length() - 1;
		while (lastWordEnd >= 0) {
			if (s.charAt(lastWordEnd) == ' ') {
				lastWordEnd--;
			} else {
				break;
			}
		}
		
		if (lastWordEnd == -1) {
			return 0;
		}
		
		int lastWordStart = lastWordEnd;
		while (lastWordStart >= 0) {
			if (s.charAt(lastWordStart) != ' ') {
				lastWordStart--;
			} else {
				break;
			}
		}

		
		return lastWordEnd - lastWordStart;
	}
	
	public static int lengthOfLastWord2(String s) {
		if (s == null || s.isBlank()) {
			return 0;
		}
		
		int lastLen = 0;
		boolean prePosSpace = false;
		
		for (char ch : s.toCharArray()) {
			if (ch != ' ' && prePosSpace) {
				lastLen = 1;
				prePosSpace = false;
			} else if (ch != ' ' && !prePosSpace){
				lastLen++;
			} else {
				prePosSpace = true;
			}
		}
		
		return lastLen;
	}
	
	public static int lengthOfLastWord1(String s) {
		if (s == null || s.isBlank()) {
			return 0;
		}
		
		String[] words = s.split("\\s+");
		
		return words[words.length - 1].length();
	}

	public static void main(String[] args) {
		
		/*
		 * 输入: "Hello World"
		 * 输出: 5
		 */
		assert 5 == lengthOfLastWord("Hello World");
		
		/*
		 * 输入: "a "
		 * 输出: 1
		 */
		assert 1 == lengthOfLastWord("a ");


	}

}
