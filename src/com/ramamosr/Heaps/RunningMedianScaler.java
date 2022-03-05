package com.ramamosr.Heaps;

import java.util.PriorityQueue;
import java.util.Comparator;  

public class RunningMedianScaler {
   /* SOLUTION APPROACH
    As it is mention in the hint, median is an element of the array such that half elements are smaller and half element are greater than that element.

So, the idea is to use max heap and min heap to store the elements of higher half and lower half.

Max heap and min heap can be implemented using STL.

Algorithm

Create two heaps. One max heap to maintain elements of lower half and one min heap to maintain elements of higher half at any point of time..
Take initial value of median as 0.
For every newly read element, insert it into either max heap or min heap and calculate the median based on the following conditions:

1 If the size of max heap is greater than size of min heap and the element is less than previous median then pop the top element from max heap and insert into min heap and insert the new element to max heap else insert the new element to min heap. Calculate the new median as average of top of elements of both max and min heap.

2 If the size of max heap is less than size of min heap and the element is greater than previous median then pop the top element from min heap and insert into max heap and insert the new element to min heap else insert the new element to max heap. Calculate the new median as average of top of elements of both max and min heap.

3 If the size of both heaps are same. Then check if current is less than previous median or not. If the current element is less than previous median then insert it to max heap and new median will be equal to top element of max heap. If the current element is greater than previous median then insert it to min heap and new median will be equal to top element of min heap.
     */
        static PriorityQueue< Integer > max_heap;
        static PriorityQueue < Integer > min_heap;
        public int[] solve(int[] A) {
            min_heap = new PriorityQueue();
            max_heap = new PriorityQueue(new CustomComp());
            int n = A.length;
            int[] ans = new int[n];
            for (int i = 0; i < n; ++i) {
                add(A[i]);
                ans[i] = get_median();
            }

            return ans;
        }
        public static int get_median() {
            int total = min_heap.size() + max_heap.size();
            int ret;
            if (total % 2 == 1) {
                if (max_heap.size() > min_heap.size())
                    ret = max_heap.peek();
                else
                    ret = min_heap.peek();
            } else {
                ret = Integer.MAX_VALUE;
                if (max_heap.size() != 0)
                    ret = Math.min(ret, max_heap.peek());
                if (min_heap.size() != 0)
                    ret = Math.min(ret, min_heap.peek());
            }
            return ret;
        }
        public static void add(int a) {
            if (max_heap.size() != 0 && (a >= max_heap.peek()))
                min_heap.offer(a);
            else
                max_heap.offer(a);

            if (Math.abs(max_heap.size() - min_heap.size()) > 1) {
                if (max_heap.size() > min_heap.size()) {
                    int temp = max_heap.peek();
                    max_heap.poll();
                    min_heap.offer(temp);
                } else {
                    int temp = min_heap.peek();
                    min_heap.poll();
                    max_heap.offer(temp);
                }
            }
        }

    }
    class CustomComp implements Comparator< Integer > {
        @Override
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    }
