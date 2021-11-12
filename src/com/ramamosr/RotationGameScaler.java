package com.ramamosr;
import java.util.Scanner;

public class RotationGameScaler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while(T-- > 0){
            int N = sc.nextInt();
            int[] A = new int[N];
            for(int i = 0; i < N; i++){
                A[i] = sc.nextInt();
            }
            int B;
            B = sc.nextInt();
            B = B % N;

            int[] ans = new int[N];
            for (int i = 0; i < N; i++){
                ans[i] = A[(i-B+N)%N];
                System.out.print(i);
            }

            for(int i = 0; i < N; i++){
                System.out.print(ans[i] + " ");
            }
            System.out.println();
        }
    }
}

