package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 * 
 * @author liuzhao
 *
 */
public class Solution0093_RestoreIPAddresses {
	
	public static final int SEG_COUNT = 4;
	
	public static List<String> restoreIpAddresses(String s) {
		List<String> result = new ArrayList<>();
		int TOTAL_LENGTH = s.length();
		
		if (TOTAL_LENGTH < 4) {
			return result;
		}
		
		int[] segments = new int[SEG_COUNT];
		// 第1段，从第0个位置开始
		dfs(s, 0, 0, result, segments);
		
		return result;
	}
	
	private static void dfs(String s, int segId, int segStartIndex, List<String> result, int[] segments) {
		
		// 4段都已经找到，判断是否符合要求
		if (segId == SEG_COUNT) {
			if (segStartIndex == s.length()) {
				String ip = String.format("%d.%d.%d.%d", segments[0], segments[1], segments[2], segments[3]);
//				System.out.println(ip);
				result.add(ip);
			}
			
			return;
		}
		
		// 已经到了string结尾，无法凑齐4段
		if (segStartIndex == s.length()) {
			return;
		}
		
		// 当前位置是0，直接跳到下一个segment
		if (s.charAt(segStartIndex) == '0') {
			segments[segId] = 0;
			dfs(s, segId + 1, segStartIndex + 1, result, segments);
			return;
		}
		
		// 一般情况，从长度1遍历到长度3
		for (int i = 1; i < 4; i++) {
			// 已经到s结尾
			if (segStartIndex + i > s.length()) {
				break;
			}
			
			String partX = s.substring(segStartIndex, segStartIndex + i);
			int partInt = Integer.parseInt(partX);
			
			// 当前没有超过255就继续，否则需要回退
			if (partInt <= 255) {
				segments[segId] = partInt;
				// 向下一层
				dfs(s, segId + 1, segStartIndex + i, result, segments);
			}
			
		}
		
		// 重置
		segments[segId] = -1;
	}

	public static List<String> restoreIpAddresses2(String s) {
		List<String> result = new ArrayList<>();
		int TOTAL_LENGTH = s.length();
		
		if (TOTAL_LENGTH < 4) {
			return result;
		}
		
		for(int i = 1; i < 4; i++) {
			String part1 = s.substring(0, i);
			// 0后面不能有其它数字
			if (i > 1 && part1.startsWith("0")) {
				continue;
			}
			// 不能超过255
			if (Integer.parseInt(part1) > 255) {
				continue;
			}
			
			for(int j = 1; j < 4; j++) {
				// 前两段已经太长了
				if (i + j + 1 >= TOTAL_LENGTH) {
					continue;
				}
				
				String part2 = s.substring(i, i + j);
				// 0后面不能有其它数字
				if (j > 1 && part2.startsWith("0")) {
					continue;
				}
				// 不能超过255
				if (Integer.parseInt(part2) > 255) {
					continue;
				}
				
				for(int k = 1; k < 4; k++) {
					// 前三段已经太长了
					if (i + j + k >= TOTAL_LENGTH) {
						continue;
					}
					
					String part3 = s.substring(i + j, i + j + k);
					// 0后面不能有其它数字
					if (k > 1 && part3.startsWith("0")) {
						continue;
					}
					// 不能超过255
					if (Integer.parseInt(part3) > 255) {
						continue;
					}
					
					String part4 = s.substring(i + j + k, TOTAL_LENGTH);
					// 0后面不能有其它数字
					if (part4.length() > 1 && part4.startsWith("0")) {
						continue;
					}
					// part4长度不能超过4
					if (part4.length() > 3) {
						continue;
					}
					// 不能超过255
					if (Integer.parseInt(part4) > 255) {
						continue;
					}
					
					String ip = String.format("%s.%s.%s.%s", part1, part2, part3, part4);
					result.add(ip);
				}
			}
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		List<String> result;
		
		/*
		 * 输入：s = "0"
		 * 输出：[]
		 */
		result = restoreIpAddresses("0");
		System.out.println(result);
		
		/*
		 * 输入：s = "25525511135"
		 * 输出：["255.255.11.135","255.255.111.35"]
		 */
		result = restoreIpAddresses("25525511135");
		System.out.println(result);
		
		/*
		 * 输入：s = "0000"
		 * 输出：["0.0.0.0"]
		 */
		result = restoreIpAddresses("0000");
		System.out.println(result);
		
		/*
		 * 输入：s = "101023"
		 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
		 */
		result = restoreIpAddresses("101023");
		System.out.println(result);
	}

}
