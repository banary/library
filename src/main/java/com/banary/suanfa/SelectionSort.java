package com.banary.suanfa;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 选择排序
 * 分类 -------------- 内部比较排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- O(n^2)
 * 最优时间复杂度 ---- O(n^2)
 * 平均时间复杂度 ---- O(n^2)
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 不稳定
 * @Author eden
 * @Date 2018/5/15 上午10:23
 */
public class SelectionSort {

    private static <T extends Comparable> void swap(List<T> ts, int i, int j){
        T temp = ts.get(i);
        ts.set(i, ts.get(j));
        ts.set(j, temp);
    }

    public static <T extends Comparable> void sort(List<T> ts){
        for (int i = 0; i < ts.size()-1; i++) {
            int min = i;
            for (int j = i+1; j < ts.size(); j++) {
                if(ts.get(min).compareTo(ts.get(j))>0){
                    min = j;
                }
            }
            if(min != i){
                swap(ts, min, i);           //该操作可能会把稳定性打乱
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> ts = Arrays.asList(9,8,7,6,5,4,1,2,3);
        sort(ts);
        System.out.println(ts.toString());
    }

}
