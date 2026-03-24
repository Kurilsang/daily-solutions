package site.kuril;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 合并区间 {
    public static void main(String[] args) {

    }
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            List<int[]> result = new ArrayList<>();
            result.add(intervals[0]);
            for(int i = 1; i < intervals.length; i++){
                if(intervals[i][1] >= result.get(result.size() - 1)[1]&&intervals[i][0] < result.get(result.size() - 1)[1]){
                    result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], intervals[i][1]);
                }
                if(intervals[i][0] > result.get(result.size() - 1)[1]){
                    result.add(intervals[i]);
                }
            }

            return result.toArray(new int[result.size()][]);
        }


    }
}
