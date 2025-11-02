package com.leetcode;

/**
 * @author Winston
 * 使用深度优先搜索（DFS）和visited二维数组解决岛屿数量问题
 */
@SuppressWarnings({"unused"})
public class SolutionTwo {

    private static final char LAND = '1';
    private static final char WATER = '0';

    /**
     * 计算网格中岛屿的数量
     *
     * @param grid 包含 '1' (陆地) 和 '0' (水) 的二维字符网格
     * @return 岛屿的数量
     */
    public int numIslands(char[][] grid) {
        // 处理边界情况，如果网格为空或没有行，则没有岛屿
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numRows = grid.length;
        int numCols = grid[0].length;
        // 创建一个同样大小的visited数组来跟踪访问过的单元格
        boolean[][] visited = new boolean[numRows][numCols];
        int numIslands = 0;

        // 遍历网格中的每个单元格
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // 如果单元格是陆地（'1'）且尚未访问
                if (grid[i][j] == LAND && !visited[i][j]) {
                    // 发现一个新岛屿，增加计数器
                    numIslands++;
                    // 从这个单元格开始进行深度优先搜索以找到所有相连的陆地
                    dfs(grid, i, j, visited);
                }
            }
        }

        return numIslands;
    }

    /**
     * 深度优先搜索（DFS）辅助方法，用于遍历并标记单个岛屿的所有部分
     *
     * @param grid    网格
     * @param r       当前单元格的行索引
     * @param c       当前单元格的列索引
     * @param visited 记录单元格是否被访问过的二维布尔数组
     */
    private void dfs(char[][] grid, int r, int c, boolean[][] visited) {
        int nr = grid.length;
        int nc = grid[0].length;

        // DFS的终止条件：
        // 1. 行或列越界
        // 2. 当前单元格是水（'0'）
        // 3. 当前单元格已经被访问过
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == WATER || visited[r][c]) {
            return;
        }

        // 将当前单元格标记为已访问
        visited[r][c] = true;

        // 递归地对所有四个方向（上、下、左、右）的邻居进行DFS
        dfs(grid, r - 1, c, visited); // 上
        dfs(grid, r + 1, c, visited); // 下
        dfs(grid, r, c - 1, visited); // 左
        dfs(grid, r, c + 1, visited); // 右
    }
}
