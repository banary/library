package com.banary.suanfa;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 冒泡排序
 * 分类 -------------- 内部比较排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- O(n^2)
 * 最优时间复杂度 ---- 如果能在内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n)
 * 平均时间复杂度 ---- O(n^2)
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 稳定
 * @Author eden
 * @Date 2018/5/14 下午5:32
 */
public class BubbleSort {

    private static <T extends Comparable> void swap(List<T> ts, int i, int j){
        T temp = ts.get(i);
        ts.set(i, ts.get(j));
        ts.set(j, temp);
    }


    public static <T extends Comparable> void sort(List<T> ts){
        for (int i = 0; i < ts.size(); i++) {
            for (int j = 0; j < ts.size()-i-1; j++) {
                System.out.println("比较(" + j + "，" + (j+1) + ")");
                if(ts.get(j).compareTo(ts.get(j+1))>0){
                    swap(ts, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> ts = Arrays.asList(9,8,7,6,5,4,1,2,3);
        sort(ts);
        System.out.println(ts.toString());
    }
}
