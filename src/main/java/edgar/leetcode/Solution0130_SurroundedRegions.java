package edgar.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/surrounded-regions/">130. 被围绕的区域</a>
 * Created by liuzhao on 2022/8/17
 */
public class Solution0130_SurroundedRegions {
    public static void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int row = board.length;
        int col = board[0].length;

        for(int i = 0; i < row; i++) {
            // 依次遍历，0 到 row 行
            // 第0列，和最后一列，寻找0
            if (board[i][0] == 'O') {
                // 从这个位置寻找连续0
                dfs(board, i, 0);
            }
            if (board[i][col - 1] == 'O') {
                // 从这个位置寻找连续0
                dfs(board, i, col - 1);
            }
        }

        for(int j = 0; j < col; j++) {
            // 依次遍历，0 到 col 列
            // 第0行，和最后一行，寻找0
            if (board[0][j] == 'O') {
                // 从这个位置寻找连续0
                dfs(board, 0, j);
            }
            if (board[row - 1][j] == 'O') {
                // 从这个位置寻找连续0
                dfs(board, row - 1, j);
            }
        }

        // 最后扫描一次，将剩余的'O'变成'X'，将'A'变成'O'
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                switch (board[i][j]) {
                    case 'O' -> board[i][j] = 'X';
                    case 'A' -> board[i][j] = 'O';
                }
            }
        }
    }

    static void dfs(char[][] board, int x, int y) {
        // 快速退出，当前位置不是0就立刻结束
        if (board[x][y] != 'O') {
            return;
        }

        board[x][y] = 'A';
        int row = board.length;
        int col = board[0].length;

        // 向右
        if (x + 1 < row) {
            dfs(board, x + 1 ,y);
        }
        // 向左
        if (x - 1 >= 0) {
            dfs(board, x - 1 ,y);
        }
        // 向上
        if (y - 1 >= 0) {
            dfs(board, x, y - 1);
        }
        // 向下
        if (y + 1 < col) {
            dfs(board, x, y + 1);
        }
    }

    public static void main(String[] args) {
        char[][] input;
        char[][] expected;

        /*
         * 输入：board = [["X","O"],["O","X"]]
         * 输出：[["X","O"],["O","X"]]
         */
        input = new char[][] {
                {'X', 'O'},
                {'O', 'X'}
        };
        solve(input);
        expected = new char[][] {
                {'X', 'O'},
                {'O', 'X'}
        };
        for (int i = 0; i < input.length; i++) {
            assert Arrays.equals(expected[i], input[i]);
        }

        /*
         * 输入：board = [["X"]]
         * 输出：[["X"]]
         */
        input = new char[][] {
                {'X'}
        };
        solve(input);
        assert Arrays.equals(new char[]{'X'}, input[0]);

        /*
         * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
         * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
         * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
         */
        input = new char[][] {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };
        expected = new char[][] {
                {'X','X','X','X'},
                {'X','X','X','X'},
                {'X','X','X','X'},
                {'X','O','X','X'}
        };
        solve(input);
        for (int i = 0; i < input.length; i++) {
            assert Arrays.equals(expected[i], input[i]);
        }

    }
}
