package site.kuril;

import java.util.HashSet;
import java.util.Set;

public class 无重复字符的最长字串 {
    public static void main(String[] args) {


    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int result = 0;
            Set<Character> set = new HashSet<>();
            int left = 0;
//            右边界遍历，当右边界遇到重复的时候要移动左边界
            for(int i = 0; i < s.length(); i++) {
                while(set.contains(s.charAt(i))) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.add(s.charAt(i));
                result = Math.max(result, i - left+1);
            }

            return result;
        }
    }
}
