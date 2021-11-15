package edgar.leetcode;

/**
 * 
 * 79. 单词搜索
 * https://leetcode-cn.com/problems/word-search/
 * 
 * @author liuzhao
 *
 */
public class Solution0079_WordSearch {

	public static boolean exist(char[][] board, String word) {
		
		if (word == null || word.isBlank()) {
			return true;
		}
		
		int rows = board.length;
		int cols = board[0].length;
		boolean[][] visited = new boolean[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				visited[i][j] = true;
				boolean flag = check(board, i, j, word, 0, visited);
				visited[i][j] = false;
				
				if (flag) {
					return true;
				}
			}
		}

		return false;
    }
	
	/**
	 * 
	 * @param board
	 * @param x 本次搜索的起始横坐标
	 * @param y 本次搜索的起始纵坐标
	 * @param word
	 * @param index word下一个要找的字符
	 * @param visited
	 * @return
	 */
	public static boolean check(char[][] board, int x, int y, String word, int index, boolean[][] visited) {
		
		if (board[x][y] != word.charAt(index)) {
			// 当前位置没有找到word[index]
			return false;
		} else if (index == word.length() - 1) {
			// 找到word最后一个字符
			return true;
		}
		
		int[][] directions = {
				{1, 0},
				{0, 1},
				{-1, 0},
				{0, -1},
		};
		
		for (int[] dir : directions) {
			int newX = x + dir[0];
			int newY = y + dir[1];
			
			// 确认newX和newY是个有效位置
			if (newX >= 0 && newX < board.length
					&& newY >= 0 && newY < board[0].length) {
				if (visited[newX][newY]) {
					// 这个位置已经访问过了
					continue;
				}
				
				visited[newX][newY] = true;
				boolean flag = check(board, newX, newY, word, index + 1, visited);
				visited[newX][newY] = false;
				
				if (flag) {
					// 找到了
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	public static void main(String[] args) {
		
		char[][] inputs;
		
		/*
		 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
		 * 输出：true
		 */
		inputs = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		assert exist(inputs, "ABCCED");
		

		/*
		 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
		 * 输出：true
		 */
		inputs = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		assert exist(inputs, "SEE");

		/*
		 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
		 * 输出：false
		 */
		inputs = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		assert !exist(inputs, "ABCB");
	}

}
