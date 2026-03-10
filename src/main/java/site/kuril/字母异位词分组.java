package site.kuril;

import java.util.*;

public class 字母异位词分组 {

    public static void main(String[] args) {
    }
}
class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            if(!map.containsKey(new String(chars))) {
                map.put(new String(chars), new ArrayList<>());
            }
            map.get(new String(chars)).add(str);
        }
        return new ArrayList<>(map.values());
    }


}