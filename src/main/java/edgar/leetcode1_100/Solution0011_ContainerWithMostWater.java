package edgar.leetcode1_100;

/**
 * 11. 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 * 
 * @author Administrator
 *
 */
public class Solution0011_ContainerWithMostWater {
	
	public static int maxArea1(int[] height) {
		int maxArea = 0;
		
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i + 1; j < height.length; j++) {
				int newArea = Math.min(height[i], height[j]) * (j - i);
				maxArea = Math.max(maxArea, newArea);
			}
		}
		
		return maxArea;
	}
	
	public static int maxArea2(int[] height) {
		int maxArea = 0;
		int left = 0, right = height.length - 1;
		
		while (left < right) {
			int newArea = Math.min(height[left], height[right]) * (right - left);
			maxArea = Math.max(maxArea, newArea);
			
			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}
		
		return maxArea;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 输入: [1,8,6,2,5,4,8,3,7]，输出: 49
		int[] heights = new int[]{1,8,6,2,5,4,8,3,7};
		assert maxArea1(heights) == 49;
		assert maxArea2(heights) == 49;
	}

}
