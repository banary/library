package com.banary.suanfa;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 鸡尾酒排序，冒泡排序的改进
 * 分类 -------------- 内部比较排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- O(n^2)
 * 最优时间复杂度 ---- 如果序列在一开始已经大部分排序过的话,会接近O(n)
 * 平均时间复杂度 ---- O(n^2)
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 稳定
 * @Author eden
 * @Date 2018/5/14 下午3:43
 */
public class CocktailSort {

    private static <T extends Comparable> void swap(List<T> ts, int i, int j){
        T temp = ts.get(i);
        ts.set(i, ts.get(j));
        ts.set(j, temp);
    }



    public static <T extends Comparable> void sort(List<T> ts){
        int left = 0;
        int right = ts.size()-1;
        while (left < right){
            for (int i = left; i < right; i++) {    // 前半轮,将最大元素放到后面
                System.out.println("比较(" + i + "，" + (i+1) + ")");
                if(ts.get(i).compareTo(ts.get(i+1))>0){
                    swap(ts, i, i+1);
                }
            }
            right--;
            for (int i = right; i > left ; i--) {    // 后半轮,将最小元素放到前面
                System.out.println("比较(" + (i-1) + "，" + i + ")");
                if(ts.get(i-1).compareTo(ts.get(i))>0){
                    swap(ts, i-1, i);
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
