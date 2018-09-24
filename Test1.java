package com.zyx.test1;

import java.util.ArrayList;

/**
 * @Author:zhangyx
 * @Date:Created in 22:142018/9/24
 * @Modified By:
 */
public class Test1 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int length = input.length;
        int start = 0;
        int end = length - 1;
        if (input == null || k > length || k <= 0)
            return list;
        else {
            // 基于快速排序的分割方法
            int index = Partition(input, length, start, end);
            // 这样调整以后，位于数组中左边的k个数字就是最小的k个数字(这k个数字不一定排序)
            while (index != k - 1) {
                // 如果比k大，在左边调整
                if (index > k - 1) {
                    end = index - 1;
                    index = Partition(input, length, start, end);
                } else {
                    // 如果比k小，调整右边
                    start = index + 1;
                    index = Partition(input, length, start, end);
                }
            }
            for (int i = 0; i < k; i++) {
                list.add(input[i]);
            }
            return list;
        }
    }
    // 在数组中选择一个数，比选择的数字小的数字移到数组的左边，比选择数字大的移动到数组的右边
    static int Partition(int[] data, int length, int start, int end) {
        if (data.length == 0 || length <= 0 || start < 0 || end >= length) {

            return -1;
        }
        // 选择从最后一个数为基准元素开始一次排序
        int index = start - 1;
        for (int i = start; i < end; ++i) {
            if (data[i] < data[end]) // 以最后一个元素为基准点进行划分
            {
                ++index;
                if (index != i) {
                    // 交换
                    int temp = data[i];
                    data[i] = data[index];
                    data[index] = temp;
                }
            }
        }

        ++index;
        // 交换
        int temp = data[index];
        data[index] = data[end];
        data[end] = temp;
        // System.out.print("index=" + index + " ");
        return index; // 返回一次排序后开始选择的数此刻所在最终位置索引

    }


    /**
     * 主函数
     */
    public static void main(String[] args) {
        int[] input = { 4, 5, 1, 6, 2, 7, 3, 8 };
        int k = 3;
        Test1 g = new Test1();
        ArrayList<Integer> list = g.GetLeastNumbers_Solution(input, k);
        if (list != null) {
            for (int i = 0; i < list.size(); i++)
                System.out.print(list.get(i) + " ");
        } else {
            System.out.print(" ");
        }
    }
}
