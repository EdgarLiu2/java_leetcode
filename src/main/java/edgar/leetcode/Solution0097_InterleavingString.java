package edgar.leetcode;

/**
 * 97. 交错字符串
 * https://leetcode-cn.com/problems/interleaving-string/
 * 
 * @author liuzhao
 *
 */
public class Solution0097_InterleavingString {
	
	public static boolean isInterleave(String s1, String s2, String s3) {
		int l1 = s1.length();
		int l2 = s2.length();
		int l3 = s3.length();
		
		if (l1 + l2 != l3) {
			return false;
		}
		
		boolean[][] visited = new boolean[l1 + 1][l2 + 1];
		// 起点
		visited[0][0] = true;
		
		// 不使用s2，只使用s1，可以覆盖多少s3
		for (int i = 1; i <= l1 && s1.charAt(i - 1) == s3.charAt(i - 1); i++) {
			visited[i][0] = visited[i - 1][0];
		}
		
		// 不使用s1，只使用s2，可以覆盖多少s3
		for (int j = 1; j <= l2 && s2.charAt(j - 1) == s3.charAt(j - 1); j++) {
			visited[0][j] = visited[0][j - 1];
		}
		
		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2; j++) {
				if (visited[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
					// s2的第j个字符，和s3的第i+j个字符一样，且目前s1[0,i]和s2[0, j-1]，与s3前缀部分相同
					visited[i][j] = true;
					continue;
				}
				
				if (visited[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
					// s1的第i个字符，和s3的第i+j个字符一样，且目前s1[0,i-1]和s2[0, j]，与s3前缀部分相同
					visited[i][j] = true;
					continue;
				}
			}
		}
		

		return visited[l1][l2];
    }

	public static void main(String[] args) {
		String s1;
		String s2;
		String s3;
		
		/*
		 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
		 * 输出：true
		 */
		s1 = "aabcc";
		s2 = "dbbca";
		s3 = "aadbbcbcac";
		assert isInterleave(s1, s2, s3);

		/*
		 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
		 * 输出：false
		 */
		s1 = "aabcc";
		s2 = "dbbca";
		s3 = "aadbbbaccc";
		assert !isInterleave(s1, s2, s3);
		
		/*
		 * 输入：s1 = "", s2 = "", s3 = ""
		 * 输出：true
		 */
		s1 = "";
		s2 = "";
		s3 = "";
		assert isInterleave(s1, s2, s3);
		
		/*
		 * 输入：s1 = "aa", s2 = "ab", s3 = "abaa"
		 * 输出：true
		 */
		s1 = "aa";
		s2 = "ab";
		s3 = "abaa";
		assert isInterleave(s1, s2, s3);
	}

}
