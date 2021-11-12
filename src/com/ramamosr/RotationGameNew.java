package com.ramamosr;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RotationGameNew {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int useCaseCount = input.nextInt();
        input.nextLine();
        if (useCaseCount > 0) {
            while (input.hasNextInt()){
                int arrayLength = input.nextInt();
                if (arrayLength > 0) {
                        int i = 0;
                        int[] numberArray = new int[arrayLength];
                        while(i<arrayLength){
                            numberArray[i] = input.nextInt();
                            i++;
                    }
                int rotateSteps = input.nextInt();
                if(rotateSteps > numberArray.length){
                    rotateSteps = rotateSteps % numberArray.length;
                }
                numberArray = reverseArray(numberArray, 0, numberArray.length - rotateSteps - 1);
                numberArray = reverseArray(numberArray, numberArray.length - rotateSteps, numberArray.length - 1);
                numberArray = reverseArray(numberArray, 0, numberArray.length - 1);
                System.out.println(Arrays.toString(numberArray).replace("[","").replace("]", "").replace(",", ""));
                }
            }
        }
    }


    public static int[] reverseArray(int[] A, int start, int end){

        for(int i = start,j=end;i<j;i++,j--){
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
        return A;
    }
}
