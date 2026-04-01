package site.kuril;

import java.util.ArrayList;
import java.util.Arrays;

public class 缺失的第一个正数 {
    public static void main(String[] args) {
        int[] a = new int[]{3,4,-1,1};
         Solution.firstMissingPositive(a);


    }
    class Solution {
        public static int firstMissingPositive(int[] nums) {
            Arrays.sort(nums);

            int expected = 1;
            for(int num:nums)
            {
                if(num < expected)
                {
                    continue;
                }
                if(num == expected)
                {
                    expected++;
                }
                else{
                    break;
                }
            }


            return expected;
        }
    }
}
