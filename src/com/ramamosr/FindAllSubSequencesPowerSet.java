package com.ramamosr;

import java.util.ArrayList;
import java.util.Comparator;

public class FindAllSubSequencesPowerSet {

    public int solve(int[] A){
        long sum = 0;
        int mod = (1000 * 1000 * 1000) + 7;
        int count = 0;
        int maxSize = (int) Math.pow(2,A.length);
        for(int j =0; j<maxSize;j++){
            ArrayList<Integer> aL = new ArrayList<>();
            for(int i = 0; i <A.length;i++){
                if((j & (1<<i)) > 0){
                    aL.add(A[i]);
                }
            }
            if(aL.size() >1) {
                count++;
                System.out.println(aL);
                aL.sort(Comparator.naturalOrder());
                sum += aL.get(aL.size() - 1) - aL.get(0);
            }
        }
        //System.out.println(count);;
        return (int)(sum % mod);
    }

    public static void main(String[] args) {
        FindAllSubSequencesPowerSet fps = new FindAllSubSequencesPowerSet();
        fps.solve(new int[] {7,8,6,4,6});
    }
}
