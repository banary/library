package com.banary.suanfa;

/**
 * @Description 归并排序
 * 1. 分解
 * 设子序列长度为gap, 将长度为n的序列R[0...n-1]拆分成n/gap段，分别表示为 R[0, gap-1], R[gap...2gap-1],...,R[(n/gap)gap...n-1]
 * 2. 合并
 *
 *
 *
 * @Author eden
 * @Date 2018/8/29 上午10:56
 */
public class MergeSort {

    public static void merge(int[] array, int low, int middle, int high){
        int i = low;
        int j = middle + 1;
        int k = 0;
        int[] array2 = new int[high - low + 1];

        while(i <= middle && j<= high){
            if(array[i] <= array[j]){
                array2[k] = array[i];
                i++;
                k++;
            }else{
                array2[k] = array[j];
                j++;
                k++;
            }
        }

        while(i <= middle){
            array2[k] = array[i];
            i++;
            k++;
        }

        while(j <= high){
            array2[k] = array[j];
            j++;
            k++;
        }

        for(k=0, i = low;i<= high; i++, k++){
            array[i] = array2[k];
        }
    }
}
