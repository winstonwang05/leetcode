package com.leetcode;

/**
 * @author Winston
 */
public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length; // 行
        int cols = grid[0].length; // 列
        int isLandsCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    isLandsCount++;
                    dfs(grid, i, j);
                }

            }
        }
        return isLandsCount;
    }

    /**
     *  DFS 深度搜索
     * @param grid 二维数组
     * @param i 行
     * @param j 列
     */
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        // 淹没
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
