package site.kuril;

import java.util.*;

public class 找到字符串中所有字母异位词 {
    public static void main(String[] args) {

    }
     class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> result = new ArrayList<>();
//            这边是异位词的一个对比基准
            HashMap<Character, Integer> map = new HashMap<>();
            for (char c : p.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            int left = 0;
            int length = 0;
            for(HashMap.Entry<Character, Integer> entry : map.entrySet()) {
                length+=entry.getValue();
            }
            if (s.length() < length) {
                return result;
            }
            HashMap<Character, Integer> map2 = new HashMap<>();
            for(int right=left+length-1; right<s.length();right++) {
//                初始化
                if(left==0)
                {
                    for(int i = 0; i<=right; i++) {
                        map2.put(s.charAt(i), map2.getOrDefault(s.charAt(i), 0) + 1);
                    }
                }
//                比对是否符合，符合加入左索引
                if(map2.equals(map)) {
                    result.add(left);
                }

//                之后减去左边的，增加右边的
                int leftCount = map2.getOrDefault(s.charAt(left),0)-1;
                if(leftCount>0) {
                    map2.put(s.charAt(left), map2.getOrDefault(s.charAt(left), 0) -1);
                }
                else {
                    map2.remove(s.charAt(left));
                }
                left++;

                if(right+1<s.length()) {
                    map2.put(s.charAt(right+1), map2.getOrDefault(s.charAt(right+1), 0)+1);
                }
            }


            return result;

        }
    }
}
