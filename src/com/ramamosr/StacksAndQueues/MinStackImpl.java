package com.ramamosr.StacksAndQueues;

import java.util.Stack;

public class MinStackImpl {
    /*
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
NOTE:

All the operations have to be constant time operations.
getMin() should return -1 if the stack is empty.
pop() should return nothing if the stack is empty.
top() should return -1 if the stack is empty.


Problem Constraints

1 <= Number of Function calls <= 107



Input Format

Functions will be called by the checker code automatically.



Output Format

Each function should return the values as defined by the problem statement.



Example Input

Input 1:

push(1)
push(2)
push(-2)
getMin()
pop()
getMin()
top()
Input 2:

getMin()
pop()
top()


Example Output

Output 1:

 -2 1 2
Output 2:

 -1 -1


Example Explanation

Explanation 1:

Let the initial stack be : []
1) push(1) : [1]
2) push(2) : [1, 2]
3) push(-2) : [1, 2, -2]
4) getMin() : Returns -2 as the minimum element in the stack is -2.
5) pop() : Return -2 as -2 is the topmost element in the stack.
6) getMin() : Returns 1 as the minimum element in stack is 1.
7) top() : Return 2 as 2 is the topmost element in the stack.
Explanation 2:

Let the initial stack be : []
1) getMin() : Returns -1 as the stack is empty.
2) pop() :  Returns nothing as the stack is empty.
3) top() : Returns -1 as the stack is empty.
     */
    Stack<Integer> s = new Stack<>();
    Integer minElement;
    public void push(int x) {
        if(s.isEmpty()){
            s.push(x);
            minElement = x;
        }
        else{
            if(x<minElement){
                /*
                we push the top element as 2 times- min element.
                since x is less than minElement, x-minElement will be less than 0.
                so we will push 2x-minElement to the stack.
                This will help in retrieving the prev minElement,
                 in case the current minElement is popped.
                 */
                s.push(2*x - minElement);
                minElement = x;
            }
            else
                s.push(x);
        }
    }

    public void pop() {
        if(s.isEmpty())
            return;
        else {
            Integer temp = s.pop();
            if(temp < minElement){
                minElement = 2* minElement - temp;
            }
        }
    }

    public int top() {
        if(s.isEmpty())
            return -1;
        else{
            Integer temp = s.peek();
            if(temp < minElement)
                return minElement;
            else
                return temp;
        }
    }

    public int getMin() {
        if(s.isEmpty())
            return -1;
        else return  minElement;
    }
}
