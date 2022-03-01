package com.ramamosr.Tries;

public class MaximumXORSubArray {

    /*
    Build a prefXor array in which the ith element represents the xor of all elements from 0 to i. To find the xor of any subarray[l..r], we can just take the xor of prefXor[r] and prefXor[l-1].

To find the maximum xor subarray ending at the index i, insert the bit representation(starting from most significant bit) of all the elements of prefXor array upto i-1 into the trie data structure.
We have two possible cases at ith index.

The prefix itself has maximum xor.
We need to remove some prefix (ending at index from 0 to i-1).Try to have most significant bit to be set bit i.e. 1. As we have maintained the trie data structure of bit representation of i-1 elements of prefXor array, we can find the maximum xor in O(logm) where m is the maximum number present in the given array.
We can find the maximum subarray ending at every index and return the subarray which has maximum XOR value.

     */
    static final int INT_SIZE = 32;
    static class TrieNode {
        int value;
        int end;
        TrieNode[] arr = new TrieNode[2];
        public TrieNode() {
            end = -1;
            value = 0;
            arr[0] = null;
            arr[1] = null;
        }
    }
    static TrieNode root;
    static void insert(int pre_xor, int index) {
        TrieNode temp = root;
        for (int i = INT_SIZE - 1; i >= 0; i--) {
            int val = (pre_xor & (1 << i)) >= 1 ? 1 : 0;
            if (temp.arr[val] == null)
                temp.arr[val] = new TrieNode();

            temp = temp.arr[val];
        }
        temp.value = pre_xor;
        temp.end = index;
    }
    static pair query(int pre_xor) {
        TrieNode temp = root;
        for (int i = INT_SIZE - 1; i >= 0; i--) {
            int val = (pre_xor & (1 << i)) >= 1 ? 1 : 0;
            if (temp.arr[1 - val] != null)
                temp = temp.arr[1 - val];
            else if (temp.arr[val] != null)
                temp = temp.arr[val];
        }
        return (new pair(pre_xor ^ (temp.value), temp.end));
    }
    public int[] solve(int[] arr) {
        int n = arr.length;
        root = new TrieNode();
        insert(0, -1);
        int[] res = new int[2];
        int result = Integer.MIN_VALUE;
        int pre_xor = 0;
        int start = -1;
        int end = 0;
        for (int i = 0; i < n; i++) {
            pre_xor = pre_xor ^ arr[i];
            insert(pre_xor, i);
            pair ans = query(pre_xor);
            if (ans.a > result) {
                result = ans.a;
                end = i;
                start = ans.b + 1;
            } else if (ans.a == result) {
                int currLen = end - start + 1;
                int newLen = i - (ans.b + 1) + 1;
                if (newLen < currLen) {
                    start = ans.b + 1;
                    end = i;
                } else if (newLen == currLen && (ans.b + 1) < start) {
                    start = ans.b + 1;
                    end = i;
                }
            }
        }
        res[0] = start + 1;
        res[1] = end + 1;
        return res;
    }
}
class pair {
    int a, b;
    public pair(int c, int d) {
        this.a = c;
        this.b = d;
    }
}

/*    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        ArrayList<Integer> prefix = new ArrayList<Integer>();
        prefix.add(0);
        for(int i = 0; i < A.size(); i++) {
            int n = prefix.get(i);
            prefix.add(n ^ A.get(i));
        }
        ArrayList<Integer> output = new ArrayList<Integer>();
        TrieNode root = new TrieNode();
        int xor = 0;
        insert(root, prefix.get(0), 0);
        for(int i = 1; i < prefix.size(); i++) {
            Pair pair = findXor(root, prefix.get(i));
            int newXor = pair.num ^ prefix.get(i);
            if(newXor > xor) {
                output = new ArrayList<Integer>();
                output.add(pair.idx + 1);
                output.add(i);
                xor = pair.num ^ prefix.get(i);
            } else if(newXor == xor) {
                int oldLen = output.get(1) - output.get(0);
                int newLen = i - (pair.idx + 1);
                if(newLen < oldLen) {
                    output = new ArrayList<Integer>();
                    output.add(pair.idx + 1);
                    output.add(i);
                    xor = pair.num ^ prefix.get(i);
                } else if(newLen == oldLen && i < output.get(0)) {
                    output = new ArrayList<Integer>();
                    output.add(pair.idx + 1);
                    output.add(i);
                    xor = pair.num ^ prefix.get(i);
                }
            }
            insert(root, prefix.get(i), i);
        }
        return output;
    }

    private Pair findXor(TrieNode root, int n) {
        for(int i = 31; i >= 0; i--) {
            int d = n & (1 << i);
            if(d > 0) {
                if(root.zero == null) root = root.one;
                else root = root.zero;
            } else {
                if(root.one == null) root = root.zero;
                else root = root.one;
            }
        }
        return root.pair;
    }

    private void insert(TrieNode root, int n, int idx) {
        for(int i = 31; i >= 0; i--) {
            int d = n & (1 << i);
            if(d > 0) {
                if(root.one == null) {
                    root.one = new TrieNode();
                }
                root = root.one;
            } else {
                if(root.zero == null) {
                    root.zero = new TrieNode();
                }
                root = root.zero;
            }
        }
        root.pair = new Pair(n, idx);
    }
}

class TrieNode {
    TrieNode zero;
    TrieNode one;
    Pair pair;
}

class Pair {
    int num;
    int idx;
    public Pair(int n, int i) {
        this.num = n;
        this.idx = i;
    }*/
