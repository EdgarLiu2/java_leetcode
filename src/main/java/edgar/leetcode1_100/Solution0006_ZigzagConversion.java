/**
 * 
 */
package edgar.leetcode1_100;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.joptsimple.internal.Strings;

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
	
	public static String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		
		int length = s.length();
		List<String>[] arrays = new ArrayList[numRows];
		for(int i = 0; i< numRows; i++) {
			arrays[i] = new ArrayList<String>();
		}
		
		int nextRow = 0;
		boolean isDown = true;
		for(int i = 0; i< length; i++) {
			String t = String.valueOf(s.charAt(i));
			arrays[nextRow].add(t);
			
			if (isDown) {
				if (nextRow == numRows - 1) {
					// 已经向下走到头，向上
					nextRow--;
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
				}
			}
		}
		
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			result.append(String.join("", arrays[i]));
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
		 * "PAHNAPLSIIGYIR", 3, "PAHNAPLSIIGYIR"
		 * "PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"
		 * "A", 1
		 * "AB", 1
		 * 
		 */

		System.out.printf("input: %s, numRows: %d, result: %s\n", "LEETCODEISHIRING", 3, convert("LEETCODEISHIRING", 3));
		System.out.printf("input: %s, numRows: %d, result: %s\n", "LEETCODEISHIRING", 4, convert("LEETCODEISHIRING", 4));
		
		System.out.printf("input: %s, numRows: %d, result: %s\n", "PAHNAPLSIIGYIR", 3, convert("PAHNAPLSIIGYIR", 3));
		System.out.printf("input: %s, numRows: %d, result: %s\n", "PAYPALISHIRING", 3, convert("PAYPALISHIRING", 3));
		
		System.out.printf("input: %s, numRows: %d, result: %s\n", "A", 1, convert("A", 1));
		System.out.printf("input: %s, numRows: %d, result: %s\n", "AB", 1, convert("AB", 1));
	}

}
