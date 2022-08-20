package edgar.leetcode;

public class Util {
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
		
		System.out.println(builder);
	}

	public static void printTwoDimArray(boolean[][] arrays) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("rows: %d, cols: %d\n", arrays.length, arrays[0].length));

		for(int row = 0; row < arrays.length; row++) {
			for(int col = 0; col < arrays[0].length; col++) {
				boolean b = arrays[row][col];
				builder.append(String.format("%b ", b));
			}

			builder.append("\n");
		}

		System.out.println(builder);
	}
	
	public static void printOneDimIntArray(int[] nums, int len) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < len; i++) {
			buf.append(nums[i]).append(" ");
		}
		
		System.out.println(buf);
	}
	
	public static void printTwoDimIntArray(int[][] nums) {
		StringBuilder buf = new StringBuilder();
		
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				buf.append(String.format("%04d", nums[i][j]) + " ");
			}
			
			buf.append("\n");
		}
		
		System.out.println(buf);
	}
}
