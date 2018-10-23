package com.banary.suanfa;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 插入排序
 * 分类 ------------- 内部比较排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- 最坏情况为输入序列是降序排列的,此时时间复杂度O(n^2)
 * 最优时间复杂度 ---- 最好情况为输入序列是升序排列的,此时时间复杂度O(n)
 * 平均时间复杂度 ---- O(n^2)
 * 所需辅助空间 ------ O(1)
 * 稳定性 ------------ 稳定
 *
 * @Author eden
 * @Date 2018/5/15 上午11:03
 */
public class InsertionSort {

    public static <T extends Comparable> void sort(List<T> ts){
        for (int i = 1; i < ts.size(); i++) {       //类似抓扑克牌排序, 第一元素作为左手的第一张牌
            T get = ts.get(i);                      //右手抓一张扑克牌
            int j = i-1;                            //拿在左手的扑克牌总是排序好的
            while (j >= 0 && ts.get(j).compareTo(get)>0) {       //将抓到的牌与手牌从右向左进行比较, 首先与左手中最大的牌比较, 如果比最大的牌小, 说明乱序，需要插入并变更位置，
                ts.set(j+1, ts.get(j));
                j--;
            }
            ts.set(j+1, get);
        }
    }

    public static void main(String[] args) {
        List<Integer> ts = Arrays.asList(9,8,7,6,5,4,1,2,3);
        sort(ts);
        System.out.println(ts.toString());
    }
}
