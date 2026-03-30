package site.kuril;

public class 轮转数组 {
    public static void main(String[] args) {
//        int[] a = new int[]{1,2,3,4,5,6,7};
//        int k = 3;
//        int[] result = Solution.rotate(a,k);
//        for(int i=0;i<result.length;i++)
//        {
//            System.out.println(result[i]);
//        }
    }
    class Solution {
        public static void rotate(int[] nums, int k) {
            int[] result = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int p2 = i+k;
                if(p2>=nums.length)
                {
                    p2%=nums.length;
                }
                result[p2] = nums[i];
            }
            for(int i=0; i<nums.length; i++)
            {
                nums[i] = result[i];
            }
        }
    }
}
