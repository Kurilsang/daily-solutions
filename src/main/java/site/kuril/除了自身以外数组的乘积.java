package site.kuril;

public class 除了自身以外数组的乘积 {
    public static void main(String[] args) {

    }
//    class Solution {
//        public int[] productExceptSelf(int[] nums) {
//            int[] result = new int[nums.length];
//            for(int i = 0; i < result.length; i++){
//                result[i] = 1;
//                for(int j = 0; j < nums.length; j++){
//                    if(i==j)
//                    {
//                        continue;
//                    }
//                    result[i] *= nums[j];
//                }
//            }
//
//
//            return result;
//        }
//    }
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for(int i = 1; i < nums.length; i++){
            result[i] = result[i-1] * nums[i-1];
        }
        int right = 1;
        for(int i = nums.length-1; i >= 0; i--){
            result[i] = result[i]*right;
            right = right*nums[i];
        }

        return result;
    }
}
}
