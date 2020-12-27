package edgar.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 
 * @author Administrator
 *
 */
public class Solution0020_ValidParentheses {
//	public static Map<Character, Character> parenthesesMap = Map.of(
//			Character.valueOf(')'), Character.valueOf('('), 
//			Character.valueOf(']'), Character.valueOf('['), 
//			Character.valueOf('}'), Character.valueOf('{') 
//	);
	public static Map<Character, Character> parenthesesMap = new HashMap<Character, Character>() {{
		put(Character.valueOf(')'), Character.valueOf('('));
		put(Character.valueOf(']'), Character.valueOf('['));
		put(Character.valueOf('}'), Character.valueOf('{') );
	}};
	
	public static boolean isValid(String s) {
		if (s == null || s.isEmpty()) {
			return true;
		}
		
		// 先增加一个字符串长度的奇偶判断，奇数直接返回false
		int len = s.length();
		if(len % 2 ==1) {
			return false;
		}
		
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == ' ') {
				continue;
			}
			
			//Character ch = Character.valueOf(c);
			if (parenthesesMap.containsKey(c)) {
				
				// Get the top element of the stack. If the stack is empty, set a dummy value of '#'
				char topElement = stack.empty() ? '#' : stack.pop();
				
				// If the mapping for this bracket doesn't match the stack's top element, return false.
				if (topElement != parenthesesMap.get(c)) {
					// 不等于期待的左括号
					return false;
				}
				
			} else {
				// 这是一个左括号
				stack.push(c);
				
				// 如果栈的深度大于字符串长度的1/2，就返回false。因为当出现这种情况的时候，即使后面的全部匹配，栈也不会为空。
				if (stack.size() > len/2) {
					return false;
				}
			}
		}
		
		return stack.isEmpty();
    }

	public static void main(String[] args) {
		String input = null;
		
		/*
		 * 输入: "()"
		 * 输出: true
		 */
		input = "()";
		assert  isValid(input);
		
		/*
		 * 输入: "()[]{}"
		 * 输出: true
		 */
		input = "()[]{}";
		assert  isValid(input);
		
		/*
		 * 输入: "(]"
		 * 输出: false
		 */
		input = "(]";
		assert  ! isValid(input);
		
		/*
		 * 输入: "([)]"
		 * 输出: false
		 */
		input = "([)]";
		assert  ! isValid(input);
		
		/*
		 * 输入: "{[]}"
		 * 输出: true
		 */
		input = "{[]}";
		assert  isValid(input);
		
		/*
		 * 输入: "["
		 * 输出: false
		 */
		input = "[";
		assert  ! isValid(input);
	}

}
