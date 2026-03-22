package site.kuril;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class 最小覆盖子串 {
    public static void main(String[] args) {

    }
    class Solution {
        public String minWindow(String s, String t) {
            // 边界条件：s比t短，直接返回空
            if (s.length() < t.length()) {
                return "";
            }

            // 统计t中每个字符的需求数量
            HashMap<Character, Integer> need = new HashMap<>();
            for (char c : t.toCharArray()) {
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            // 统计窗口中满足需求的字符数量
            HashMap<Character, Integer> window = new HashMap<>();
            int left = 0; // 左指针
            int right = 0; // 右指针
            int valid = 0; // 已满足需求的字符种类数
            int minLen = Integer.MAX_VALUE; // 最小窗口长度
            int start = 0; // 最小窗口的起始索引

            while (right < s.length()) {
                // 1. 右移右指针，扩大窗口
                char c = s.charAt(right);
                right++;

                // 如果当前字符是t需要的，更新窗口统计
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    // 当窗口中该字符数量满足需求时，valid+1
                    if (Objects.equals(window.get(c), need.get(c))) {
                        valid++;
                    }
                }

                // 2. 当窗口满足所有需求时，尝试左移缩小窗口
                while (valid == need.size()) {
                    // 更新最小窗口
                    if (right - left < minLen) {
                        start = left;
                        minLen = right - left;
                    }

                    // 左移左指针，缩小窗口
                    char d = s.charAt(left);
                    left++;

                    // 如果移出的字符是t需要的，更新窗口统计
                    if (need.containsKey(d)) {
                        // 当该字符数量刚好满足需求，移出后不再满足，valid-1
                        if (Objects.equals(window.get(d), need.get(d))) {
                            valid--;
                        }
                        window.put(d, window.get(d) - 1);
                    }
                }
            }

            // 没有找到满足条件的窗口，返回空；否则返回最小窗口子串
            return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
        }
    }
}
