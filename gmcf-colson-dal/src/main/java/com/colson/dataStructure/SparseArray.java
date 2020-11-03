package com.colson.dataStructure;

import java.util.Arrays;

/**
 * 稀疏数组
 * <p>
 * 当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 * <p>
 * 稀疏数组的处理方法是：
 * 记录数组一共有几行几列，有多少个不同的值。
 * 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模。
 * <p>
 * <p>
 * 第一行记录行列数和数据的个数
 * 剩下的为有效的数据
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{0, 0, 0, 22, 0, 0, 15}, {0, 11, 0, 0, 0, 17, 0}, {0, 0, 0, -6, 0, 0, 0}, {0, 0, 0, 0, 0, 39, 0}, {91, 0, 0, 0, 0, 0, 0}, {0, 0, 28, 0, 0, 0, 0}};
        int[][] result = transferArr(arr);
        System.out.println("之前：");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("之后：");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("再次转化之后：");
        int[][] arrResult = transferResult(result);
        for (int i = 0; i < arrResult.length; i++) {
            for (int j = 0; j < arrResult[i].length; j++) {
                System.out.print(arrResult[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println(Arrays.toString(new int[5]));
    }


    // 数组转化为稀疏数组
    public static int[][] transferArr(int[][] arr) {

        if (arr == null) {
            return null;
        }
        int row = arr.length;
        int column = arr[0].length;
        int recordValue = 0;


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[i][j] != 0) {
                    recordValue++;
                }
            }
        }
        int[][] result = new int[recordValue + 1][3];
        // 记录第一行
        result[0][0] = row;
        result[0][1] = column;
        result[0][2] = recordValue;

        // 记录有效数据
        int resultRow = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[i][j] != 0) {
                    result[resultRow][0] = i;
                    result[resultRow][1] = j;
                    result[resultRow][2] = arr[i][j];
                    resultRow++;
                }
            }
        }
        return result;
    }

    // 从稀疏数组再转回成正常数组
    public static int[][] transferResult(int[][] arr) {
        int row = arr[0][0];
        int column = arr[0][1];
        int recordNum = arr[0][2];
        int[][] result = new int[row][column];

        for (int i = 1; i <= recordNum; i++) {
            result[arr[i][0]][arr[i][1]] = arr[i][2];
        }
        return result;
    }
}
