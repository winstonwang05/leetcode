package com.zk;

/**
 * 解决方案一
 *
 * @author Winston
 * @date 2024/07/15
 */
public class SolutionOne {

    /**
     * 在一个m x n的矩阵中搜索一个目标值，该矩阵具有以下特性：
     * - 每行中的整数从左到右排序。
     * - 每行的第一个整数大于前一行的最后一个整数。
     * <p>
     * 解题思路：
     * 此方法采用两步二分查找。
     * 1. 首先，对矩阵的行进行二分查找，以确定目标值可能在哪一行。
     *    通过检查每一行的第一个和最后一个元素，我们可以确定目标值是否在该行的范围内。
     * 2. 找到目标行后，在该行上执行第二次二分查找，以确定目标值是否存在。
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
        int topRow = 0;
        int bottomRow = rows - 1;
        int targetRow = -1;

        // 第一步：二分查找确定目标行
        while (topRow <= bottomRow) {
            // 计算中间行索引，避免潜在的整数溢出
            int midRow = topRow + (bottomRow - topRow) / 2;
            int firstElement = matrix[midRow][0];
            int lastElement = matrix[midRow][cols - 1];

            if (target >= firstElement && target <= lastElement) {
                // 目标值在该行的范围内，找到了目标行
                targetRow = midRow;
                break;
            } else if (target < firstElement) {
                // 目标值在当前行的上方
                bottomRow = midRow - 1;
            } else {
                // 目标值在当前行的下方
                topRow = midRow + 1;
            }
        }

        // 如果没有找到可能包含目标值的行，则目标值不存在
        if (targetRow == -1) {
            return false;
        }

        // 第二步：在目标行上进行二分查找
        int leftCol = 0;
        int rightCol = cols - 1;
        while (leftCol <= rightCol) {
            // 计算中间列索引，避免潜在的整数溢出
            int midCol = leftCol + (rightCol - leftCol) / 2;
            int midValue = matrix[targetRow][midCol];

            if (midValue == target) {
                // 找到目标值
                return true;
            } else if (target < midValue) {
                // 在左半部分继续搜索
                rightCol = midCol - 1;
            } else {
                // 在右半部分继续搜索
                leftCol = midCol + 1;
            }
        }

        // 在目标行中未找到目标值
        return false;
    }
}
