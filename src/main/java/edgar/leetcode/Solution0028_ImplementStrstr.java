package edgar.leetcode;

/**
 * 28. 实现 strStr()
 * https://leetcode-cn.com/problems/implement-strstr/
 * 
 * @author Administrator
 *
 */
public class Solution0028_ImplementStrstr {
	
	public static int strStr(String haystack, String needle) {
		int h_len = haystack.length();
		int n_len = needle.length();
		
		if (n_len == 0) {
			return 0;
		}
		
		if (h_len < n_len) {
			return -1;
		}
		
		for (int i = 0; i < h_len - n_len +1; i++) {
			int tmp = 0;
			
			for (int j = 0; j < n_len; j++) {
				if (haystack.charAt(i+j) == needle.charAt(j)) {
					// 第j个字符相等，继续
					tmp++;
				} else {
					tmp = -1;
					break;
				}
			}
			
			if (tmp == n_len) {
				return i;
			}
		}
		
		return -1;
    }

	public static void main(String[] args) {
		/*
		 * 输入：haystack = "hello", needle = "ll"
		 * 返回：2
		 */
		assert 2 == strStr("hello", "ll");
		
		/*
		 * 输入：haystack = "aaaaa", needle = "bba"
		 * 返回：-1
		 */
		assert -1 == strStr("aaaaa", "bba");
	}

}
