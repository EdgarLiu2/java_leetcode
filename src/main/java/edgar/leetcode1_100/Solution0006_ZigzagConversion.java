package edgar.leetcode1_100;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 6. Z 字形变换
 * https://leetcode-cn.com/problems/zigzag-conversion/solution/z-zi-xing-bian-huan-by-leetcode/
 * 
 * @author Administrator
 *
 */
public class Solution0006_ZigzagConversion {
	
	public static void printTwoDimArray(char[][] arrays) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("rows: %d, cols: %d\n", arrays.length, arrays[0].length));
		
		for(int row = 0; row < arrays.length; row++) {
			for(int col = 0; col < arrays[0].length; col++) {
				char c = arrays[row][col];
				builder.append(String.format("%s ", c == '\0' ? "*" : c));
			}
			
			builder.append("\n");
		}
		
		System.out.println(builder.toString());
	}
	
	public static String convert1(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		
		int length = s.length();
		int numCols = (int)(Math.ceil(length*1.0 / (numRows + numRows - 2)) * (numRows - 1));
		char[][] tmpArrays = new char[numRows][numCols];
		
		int nextRow = 0;
		int nextCol = 0;
		boolean isDown = true;
		for(int i = 0; i< length; i++) {
			tmpArrays[nextRow][nextCol] = s.charAt(i);
			
			if (isDown) {
				if (nextRow == numRows - 1) {
					// 已经向下走到头，向上
					nextRow--;
					nextCol++;
					isDown = false;
				} else {
					nextRow++;
				}
			} else {
				if (nextRow == 0) {
					// 已经向上走到头，向下
					nextRow++;
					isDown = true;
				} else {
					nextRow--;
					nextCol++;
				}
			}
		}
		
		printTwoDimArray(tmpArrays);
		
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				if (tmpArrays[i][j] != '\0') {
					result.append(tmpArrays[i][j]);
				}
			}
		}
		
		return result.toString();
	}
	
	public static String convert2(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		
		int length = s.length();
		List<String>[] arrays = new ArrayList[numRows];
		for(int i = 0; i< numRows; i++) {
			arrays[i] = new ArrayList<String>();
		}
		
		int nextRow = 0;
		boolean isDown = false;
		for(int i = 0; i< length; i++) {
			String t = String.valueOf(s.charAt(i));
			arrays[nextRow].add(t);
			
			if (nextRow == 0 || nextRow == numRows - 1) {
				// 当前行nextRow为0或numRows-1时，箭头发生反向转折
				isDown = !isDown;
			}
			
			nextRow += isDown ? 1 : -1;
		}
		
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			result.append(String.join("", arrays[i]));
		}
		
		return result.toString();
	}
	
	/**
	 * 
	 * 每一行字母的所有下标其实是有规则的
	 * 我们先假定有 numRows=4 行来推导下，其中 2*numRows-2 = 6 , 我们可以假定为 step=2*numRows-2 ，我们先来推导下规则：
	 * 
	 * 第0行： 0 - 6 - 12 - 18
	 * ==> 下标间距 6 - 6 - 6 ==> 下标间距 step - step - step
	 * 第1行： 1 - 5 - 7 - 11 - 13
	 * ==> 下标间距 4 - 2 - 4 - 2 ==> 下标间距step-2*1(行) - 2*1(行) - step-2*1(行) - 2*1(行)
	 * 第2行： 2 - 4 - 8 - 10 - 14
	 * ==> 下标间距 2 - 4 - 2 - 4 ==> 下标间距step-2*2(行) - 2*2(行) - step-2*2(行) - 2*2(行)
	 * 第3行：3 - 9 - 15 - 21
	 * ==> 下标间距间距 6 - 6 - 6 ==>下标间距step - step - step
	 * 
	 * 可以得出以下结论：
	 * 1. 起始下标都是行号
	 * 2. 第0层和第numRows-1层的下标间距总是step 。
	 * 3. 中间层的下标间距总是step-2*行数，2*行数交替。
	 * 4. 下标不能超过len(s)-1
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public static String convert3(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		
		int step = numRows * 2 - 2;				// 间距
		int index = 0;									// 记录s的下标
		int len = s.length();
		int add = 0;										// 真实的间距
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < numRows; i++) {
			index = i;
			add = i * 2;
			while (index < len) {
				// 超出字符串长度计算下一层
				result.append(s.charAt(index));	// 当前行的第一个字母
				add = step - add;						// 第一次间距是step-2*i，第二次是2*i, 
				index += (i == 0 || i == numRows-1) ? step : add; // 0行和最后一行使用step间距，其余使用add间距
			}
		}
		
		return result.toString();
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 
		 * "LEETCODEISHIRING", 3, "LCIRETOESIIGEDHN"
		 * "LEETCODEISHIRING", 4, "LDREOEIIECIHNTSG"
		 * "PAHNAPLSIIGYIR", 3, "PAIIANPSIYRHLG"
		 * "PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"
		 * "A", 1
		 * "AB", 1
		 * 
		 */

		System.out.printf("input: %s, numRows: %d, result: %s\n", "LEETCODEISHIRING", 3, convert2("LEETCODEISHIRING", 3));
		System.out.printf("input: %s, numRows: %d, result: %s\n", "LEETCODEISHIRING", 4, convert2("LEETCODEISHIRING", 4));
		
		System.out.printf("input: %s, numRows: %d, result: %s\n", "PAHNAPLSIIGYIR", 3, convert2("PAHNAPLSIIGYIR", 3));
		System.out.printf("input: %s, numRows: %d, result: %s\n", "PAYPALISHIRING", 3, convert2("PAYPALISHIRING", 3));
		
		System.out.printf("input: %s, numRows: %d, result: %s\n", "A", 1, convert2("A", 1));
		System.out.printf("input: %s, numRows: %d, result: %s\n", "AB", 1, convert2("AB", 1));
	}

}
