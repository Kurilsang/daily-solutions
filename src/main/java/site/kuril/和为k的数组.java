package site.kuril;

public class 和为k的数组 {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        int k = 3;
        System.out.println(Solution.subarraySum(a,k));

    }
    class Solution {
        public static int subarraySum(int[] nums, int k) {
            int left = 0;
            int right = 0;
            int sum = 0;
            int result = 0;

            for(left = 0; left < nums.length; left++) {
                right=left;
                sum=0;
                while(right < nums.length) {

                    sum+=nums[right];
                    if(sum == k) {
                        result++;
                    }
                    right++;
                }
            }

            return result;
        }
    }
}
