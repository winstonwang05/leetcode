package com.zk;

/**
 * 解决方案二
 *
 * @author Winston
 * @date 2024/07/15
 */
public class SolutionTwo {

    /**
     * 在一个m x n的矩阵中搜索一个目标值，该矩阵具有以下特性：
     * - 每行中的整数从左到右排序。
     * - 每行的第一个整数大于前一行的最后一个整数。
     * <p>
     * 解题思路：
     * 我们可以将这个二维矩阵视为一个有序的一维数组，然后使用二分查找来寻找目标值。
     * 数组的索引范围是从 0 到 rows * cols - 1。
     * 对于一维数组中的任何索引 mid，它在二维矩阵中对应的行是 mid / cols，列是 mid % cols。
     *
     * @param matrix 输入的二维矩阵
     * @param target 要搜索的目标值
     * @return 如果找到目标值则返回 true，否则返回 false
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 检查矩阵是否为空或无效
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        // 将矩阵视为一个有序的一维数组
        int left = 0;
        int right = rows * cols - 1;

        // 在矩阵的一维表示上执行二分查找
        while (left <= right) {
            // 计算中间索引，使用 left + (right - left) / 2 避免潜在的整数溢出
            int mid = left + (right - left) / 2;
            // 将一维的 mid 索引转换回二维矩阵的坐标
            int midValue = matrix[mid / cols][mid % cols];

            if (midValue == target) {
                // 找到目标值
                return true;
            } else if (midValue < target) {
                // 在右半部分继续搜索
                left = mid + 1;
            } else {
                // 在左半部分继续搜索
                right = mid - 1;
            }
        }

        // 未找到目标值
        return false;
    }
}
