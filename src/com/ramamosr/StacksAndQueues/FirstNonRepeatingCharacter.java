package com.ramamosr.StacksAndQueues;

import java.util.HashMap;
import java.util.Deque;
public class FirstNonRepeatingCharacter {
    /*
    Problem Description

Given a string A denoting a stream of lowercase alphabets.

You have to make new string B. B is formed such that we have to find first non-repeating character each time a character is inserted to the stream and append it at the end to B. if no non-repeating character is found then append '#' at the end of B.



Problem Constraints

1 <= |A| <= 100000



Input Format

The only argument given is string A.



Output Format

Return a string B after processing the stream of lowercase alphabets A.



Example Input

Input 1:

 A = "abadbc"
Input 2:

 A = "abcabc"


Example Output

Output 1:

"aabbdd"
Output 2:

"aaabc#"


Example Explanation

Explanation 1:

"a"      -   first non repeating character 'a'
"ab"     -   first non repeating character 'a'
"aba"    -   first non repeating character 'b'
"abad"   -   first non repeating character 'b'
"abadb"  -   first non repeating character 'd'
"abadbc" -   first non repeating character 'd'
Explanation 2:

"a"      -   first non repeating character 'a'
"ab"     -   first non repeating character 'a'
"abc"    -   first non repeating character 'a'
"abca"   -   first non repeating character 'b'
"abcab"  -   first non repeating character 'c'
"abcabc" -   no non repeating character so '#'
     */
    public String solve(String A) {
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        Deque<Character> dq = new ArrayDeque<Character>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<A.length(); i++)
        {
            map.put(A.charAt(i), map.getOrDefault(A.charAt(i), 0)+1);
            if(map.get(A.charAt(i))>1)
            {
                while(!dq.isEmpty() && map.get(dq.peekFirst())>1)
                {
                    dq.removeFirst();
                }
            }
            else
            {
                dq.addLast(A.charAt(i));
            }

            if(!dq.isEmpty())
            {
                sb.append(dq.peekFirst());
            }
            else
            {
                sb.append("#");
            }
        }
        return sb.toString();
    }
    

    public static void main(String[] args) {
        FirstNonRepeatingCharacter fnrc = new FirstNonRepeatingCharacter();
        System.out.println(fnrc.solve("abcabc"));
    }
}
