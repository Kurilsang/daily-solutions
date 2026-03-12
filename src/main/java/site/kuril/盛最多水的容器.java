package site.kuril;

public class 盛最多水的容器 {
    public static void main(String[] args) {
    }
    class Solution {
        public int maxArea(int[] height) {
            int Area = 0;
            int left = 0, right = height.length - 1;
            while(left < right) {
                Area = Math.max(Area, Math.min(height[left], height[right]) * (right - left));
                if(height[left] < height[right]) {
                    left++;
                }
                else{
                    right--;
                }
            }

            return Area;
        }
    }
}
