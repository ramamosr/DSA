package com.ramamosr;

public class SpiralOrderMatrix1 {

    public void printSpiralMatrix(int[][] A){
        int rows = A.length;
        int cols = A[0].length;
        int top = 0, right= cols-1,bottom =rows-1,left = 0;
        while(top <= bottom && left <= right){

            for(int i=left;i<=right;i++){
                System.out.println(A[top][i]);
            }
            top++;
            for(int i=top;i<=bottom;i++){
                System.out.println(A[i][right]);
            }
            right--;

            for(int i=right;i>=left;i--){
                System.out.println(A[bottom][i]);
            }
            bottom--;

            for(int i=bottom;i>=top;i--){
                System.out.println(A[i][left]);
            }
            left++;

        }

    }

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        SpiralOrderMatrix1 sp = new SpiralOrderMatrix1();
        sp.printSpiralMatrix(arr);
    }
}
