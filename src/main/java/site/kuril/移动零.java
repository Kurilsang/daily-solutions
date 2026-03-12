package site.kuril;

public class 移动零 {
    public static void main(String[] args) {


    }
    class Solution {
        public void moveZeroes(int[] nums) {
            int index=0;
            for(int i=0; i<nums.length; i++) {
                if(nums[i]!=0) {
//                    不为0要收集
                    nums[index++]=nums[i];
                }
                else{
//                    为0跳过,索引不增加

                }
            }
            while(index<nums.length) {
                nums[index++]=0;
            }

        }
    }
}
