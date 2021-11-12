package com.ramamosr;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RotationGameArray {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int useCaseCount = input.nextInt();
        input.nextLine();
        if (useCaseCount > 0) {
            while (input.hasNextLine()) {

                String arrayInput = input.nextLine();
                if (arrayInput.length() > 0) {
                    String[] inputStr = arrayInput.split(" ");
                    int arrayLength = Integer.parseInt(inputStr[0]);
                    int[] numberArray = new int[arrayLength];
                    for (int i = 0; i < inputStr.length - 1; i++) {
                        numberArray[i] = Integer.parseInt(inputStr[i + 1]);
                    }
                    int rotateSteps = Integer.parseInt(input.nextLine());
                    if(rotateSteps > numberArray.length){
                        rotateSteps = rotateSteps % numberArray.length;
                    }
                    numberArray = reverseArray(numberArray, 0, numberArray.length - rotateSteps - 1);
                    numberArray = reverseArray(numberArray, numberArray.length - rotateSteps, numberArray.length - 1);
                    numberArray = reverseArray(numberArray, 0, numberArray.length - 1);
                    System.out.println("Rotated Array is " + Arrays.toString(numberArray));
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
