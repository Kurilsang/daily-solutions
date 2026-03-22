package site.kuril;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 滑动窗口最大值 {
    public static void main(String[] args) {
        int[] a = new int[]{1,-1};
        int k = 1;
        int[] b=  Solution.maxSlidingWindow(a,k);
        for(int i = 0; i < b.length; i++){
            System.out.println(b[i]);
        }
    }
    class Solution {
        public static int[] maxSlidingWindow(int[] nums, int k) {
            int left=0;
            int right= k-1;
            ArrayList<Integer> list= new ArrayList<>();
            int tMax = getMax(nums,left,right);
            list.add(tMax);

// 两个问题 一进来的时候检测是不是最大的 ，如果是，直接替换， 如果不是，还要检查移出去的是不是最大值，如果是，要重新找，如果不是，则保持原样
            while(right<nums.length-1)
            {
                right++;
                if(nums[right]>=tMax)
                {
                    tMax = nums[right];
                    list.add(tMax);
                    left++;
                }
                else{
                    int tDelete = nums[left];
                    left++;
                    if(tDelete==tMax)
                    {
                        tMax = getMax(nums,left,right);
                        list.add(tMax);
                    }
                    else{
                        list.add(tMax);
                    }
                }
            }

            return list.stream().mapToInt(i->i).toArray();
        }
        public static int  getMax(int[] nums, int left, int right) {
            int result = -100000;
            while(left<=right)
            {
                int tBigger = nums[left]>=nums[right]?nums[left]:nums[right];
                result = tBigger>=result?tBigger:result;
                left++;
                right--;
            }
            return result;
        }
    }
}
