package edgar.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 71. 简化路径
 * https://leetcode-cn.com/problems/simplify-path/
 * 
 * @author liuzhao
 *
 */
public class Solution0071_SimplifyPath {

	public static String simplifyPath(String path) {
		// 先处理特殊空值
		if (path.isBlank() || "/".equals(path)) {
			return "/";
		}
		
		Deque<String> processedStack = new ArrayDeque<>();
		String[] symbols = path.trim().split("/");
		
		for (String symbol : symbols) {
			// 跳过空和.
			if(symbol.isBlank() || ".".equals(symbol)) {
				continue;
			}
			
			// 出栈，返回到上一级目录
			
			if ("..".equals(symbol)) {
				if (!processedStack.isEmpty()) {
					processedStack.pollLast();
				}
				
				continue;
			}
			
			// 新目录入栈
			processedStack.addLast(symbol);
		}
		
		// 栈为空
		if (processedStack.isEmpty()) {
			return "/";
		}
		
		StringBuilder builder = new StringBuilder();
		for (String p : processedStack) {
			builder.append("/").append(p);
		}

		return builder.toString();
    }
	
	public static void main(String[] args) {
		/*
		 * 输入：path = "/home/"
		 * 输出："/home"
		 */
		assert "/home".equals(simplifyPath("/home/"));

		/*
		 * 输入：path = "/../"
		 * 输出："/"
		 */
		assert "/".equals(simplifyPath("/../"));
		
		/*
		 * 输入：path = "/home//foo/"
		 * 输出："/home/foo"
		 */
		assert "/home/foo".equals(simplifyPath("/home//foo/"));
		
		/*
		 * 输入：path = "/a/./b/../../c/"
		 * 输出："/c"
		 */
		assert "/c".equals(simplifyPath("/a/./b/../../c/"));
	}

}
