package site.kuril;

public class 接雨水 {
    public static void main(String[] args) {

    }
    class Solution {
        public int trap(int[] height) {
            int result = 0;
            for (int i = 0; i < height.length - 1; i++) {
                int left = i - 1;
                int right = i + 1;
//                去除两边边界
                if(left == -1)
                {
                    continue;
                }
                if(right == height.length-1)
                {
                    continue;
                }
                if(height[left] > height[i])
//                如果开始下降就判定为一个漏斗形状，往下搜索另一边
                {
                    result += height[left] - height[i];
                    while(height[right]<height[left])
                    {
                        result += height[left] - height[right];
                        right++;
                    }
//                    此时right应该是另一边的高了，还要计算一下中间的面积,面积应该为容器高度（height[left]-移动的时候的每一个height[right]）

                }
                i = right;
//                最后下次查找要从右边开始了，不然会重复
            }

            return result;
        }
    }
}
