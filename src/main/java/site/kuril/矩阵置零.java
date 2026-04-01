package site.kuril;

public class 矩阵置零 {
    public static void main(String[] args) {

    }
    class Solution {
        public void setZeroes(int[][] matrix) {
            int[][] zero = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    zero[i][j] = matrix[i][j] == 0 ? 0 : 1;
                }
            }

            for(int i = 0; i < zero.length; i++) {
                for(int j = 0; j < zero[0].length; j++) {
                    if(zero[i][j] == 0) {
                        for(int k = 0; k < matrix.length; k++) {
                            matrix[k][j] = 0;
                        }
                        for(int k = 0; k < matrix[0].length; k++) {
                            matrix[i][k] = 0;
                        }
                    }
                }
            }
        }
    }
}
