package edgar.leetcode1_100;

/**
 * 14. 最长公共前缀
 * https://leetcode-cn.com/problems/longest-common-prefix/
 * 
 * @author Administrator
 *
 */
public class Solution0014_LongestCommonPrefix {

	/**
	 * 水平扫描法
	 * 
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix1(String[] strs) {
		if(strs == null || strs.length ==0) {
			return "";
		} else if (strs.length == 1) {
			return strs[0];
		}
		
		String prefix = strs[0];
		for (int i = 1; i< strs.length; i++) {
			while(strs[i].indexOf(prefix) !=0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) {
					return "";
				}
			}
		}
		
		return prefix;
    }
	
	/**
	 * 分治
	 * 
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length ==0) {
			return "";
		} else if (strs.length == 1) {
			return strs[0];
		}
		
		return longestCommonPrefix(strs, 0, strs.length - 1);
	}
	
	public static String longestCommonPrefix(String[] strs, int start, int end) {
		if (start == end) {
			return strs[start];
		} else {
			int mid = (start + end) / 2;
			String lcpLeft = longestCommonPrefix(strs, start, mid);
			String lcpRight = longestCommonPrefix(strs, mid + 1, end);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}
	
	public static String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for (int i = 0; i < min; i++) {
			if (left.charAt(i) != right.charAt(i)) {
				return left.substring(0, i);
			}
		}
		
		return left.substring(0, min);
	}
	
	public static void main(String[] args) {
		// 输入: "["flower","flow","flight"]"，输出: "fl"
		String[] input1 = new String[]{"flower","flow","flight"};
		assert "fl".equals(longestCommonPrefix(input1));

		// 输入: ["dog","racecar","car"]，输出: ""
		String[] input2 = new String[]{"dog","racecar","car"};
		assert "".equals(longestCommonPrefix(input2));
	}

}
