package edgar.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 * 
 * @author Administrator
 *
 */
public class Solution0022_GenerateParentheses {
	
	public static List<String> generateParenthesis(int n) {
		if (n == 0) {
			return Collections.emptyList();
		} else if (n == 1) {
			return Arrays.asList("()");
		}
		
		List<String> result = new ArrayList<String>();
		dfs("", n, n, result);

		return result;
    }
	
	/**
     * @param path  从根结点到任意结点的路径，全程只使用一份
     * @param left  左括号还有几个可以使用
     * @param right 右括号还有几个可以使用
     * @param res
     */
	public static void dfs(String path, int left, int right, List<String> result) {
		if (left == 0 && right == 0) {
			// 因为每一次尝试，都使用新的字符串变量，所以无需回溯
			result.add(path);
			return;
		}
		
		// 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
		if (left > right) {
			return;
		}
		
		if (left > 0) {
			dfs(path + "(", left-1, right, result);
		}
		
		if (right > 0) {
			dfs(path + ")", left, right-1, result);
		}
	}
	
	public static List<String> generateParenthesis2(int n) {
		if (n == 0) {
			return Collections.emptyList();
		} else if (n == 1) {
			return Arrays.asList("()");
		}
		
		List<String> subResult = generateParenthesis(n-1);
		Set<String> result = new HashSet<String>();
		for(String elem : subResult ) {
			result.add("()" + elem);
			result.add("(" + elem + ")");
			result.add(elem + "()");
		}

		List<String> r = new ArrayList<String>(result);
		Collections.sort(r);
		return r;
    }

	public static void main(String[] args) {
		/*
		 * 输入：n = 3 
		 * 输出：[ "((()))", "(()())", "(())()", "()(())", "()()()" ]
		 */
		List<String> result = generateParenthesis(3);
		System.out.println(result);

		/*
		 * 输入：n = 4
		 * 输出：["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]
		 */
		result = generateParenthesis(4);
		System.out.println(result);
	}

}
