package com.banary.suanfa.problem;

import java.util.Arrays;

/**
 * @Description 给定一个数组，和一个常数，找出数组中两个数之和为常数的位置
 * @Author eden
 * @Date 2018/9/4 下午2:34
 */
public class Solution {

    public static int[] twoSum(int[] nums, int target) {
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if((nums[i] + nums[j])==target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int i : result){
           System.out.println(i);
        }
    }
}
