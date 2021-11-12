package com.ramamosr;

import java.util.ArrayList;
import java.util.Comparator;

public class SubSequenceV2 {
    public int findAllSubSequences(int[] A){
        int mod = (1000*1000*1000) + 7;
        long sum = 0;
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for(int i =0; i<A.length;i++){
            for(int j=0; j<A.length;j++) {
                ArrayList<Integer> temp2 = new ArrayList<>();
                ArrayList<Integer> temp3 = new ArrayList<>();
                ArrayList<Integer> temp4 = new ArrayList<>();
                if(j+1 <A.length && i<A.length && i != j+1) {
                    temp4.add(A[i]);
                    temp4.add(A[j + 1]);
                    if (temp4.size() > 1) {
                        result.add(temp4);
                    }
                }
                for(int k =i; k<=j;k++){
                    temp2.add(A[k]);
                }
                if(temp2.size() > 1) {
                    result.add(temp2);
                }
                for(int m = i; m<A.length;m++){
                    if(m!=j)
                        temp3.add(A[m]);
                }
                if(temp3.size() > 1) {
                    result.add(temp3);
                }
            }
        }
        System.out.println(result);
        System.out.println(result.size());

        for(ArrayList<Integer> arr:result)
        {
            arr.sort(Comparator.naturalOrder());
            sum += arr.get(arr.size()-1) - arr.get(0);
        }
        System.out.println(sum);
        return (int)sum%mod;
    }

    public static void main(String[] args) {
        SubSequenceV2 fsi = new SubSequenceV2();
        fsi.findAllSubSequences(new int[]{7,8,6,4,6});
    }
}
