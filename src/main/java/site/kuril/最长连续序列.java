package site.kuril;

import java.lang.reflect.Array;
import java.util.*;

public class 最长连续序列 {
    public static void main(String[] args) {

    }
}
class Solution3 {
    public int longestConsecutive(int[] nums) {


        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }



        for(Integer num : set) {
            if (set.contains(num - 1)) {
                continue;
            }

            int tCount = 1;
            int start = num;
            int end = num+1;
            while(set.contains(end)) {
                tCount++;
                end++;
            }
            count = Math.max(count, tCount);
        }

        return count;
    }
}