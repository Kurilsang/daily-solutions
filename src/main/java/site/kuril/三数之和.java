package site.kuril;

import java.sql.PreparedStatement;
import java.util.*;

public class 三数之和 {
    public static void main(String[] args) {

    }
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Set<List<Integer>> result = new HashSet<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    if (nums[i] + nums[left] + nums[right] == 0) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        right--;
                    }
                    else if(nums[i] + nums[left] + nums[right] < 0)
                    {
                        left++;
                    }
                    else if(nums[i] + nums[left] + nums[right] > 0)
                    {
                        right--;
                    }
                }

            }

            return result.stream().toList();
        }
    }
}
