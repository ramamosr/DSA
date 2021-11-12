package com.ramamosr;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FindAllSubseuencesIterative {

    public ArrayList<ArrayList<Integer>> findAllSubSequences(int[] A){

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for(int i =0; i<A.length;i++){
           for(int j=i; j<A.length;j++) {
               ArrayList<Integer> temp2 = new ArrayList<>();
               ArrayList<Integer> temp3 = new ArrayList<>();
               for(int k =i; k<=j;k++){
                        temp2.add(A[k]);
               }
               if(!result.contains(temp2) && temp2.size() > 0)
                   result.add(temp2);
               for(int m = i; m<=j+1 && m<A.length;m++){
                   if(m!=j)
                    temp3.add(A[m]);
               }
               if(!result.contains(temp3) && temp3.size() > 0)
                result.add(temp3);
           }
        }
        System.out.println(result);;
        return result;
    }

    public static void main(String[] args) {
        FindAllSubseuencesIterative fsi = new FindAllSubseuencesIterative();
        fsi.findAllSubSequences(new int[]{1,2,3,4});
    }
}
