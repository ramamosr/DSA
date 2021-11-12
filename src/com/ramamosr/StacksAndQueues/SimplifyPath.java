package com.ramamosr.StacksAndQueues;

import java.util.Stack;

public class SimplifyPath {

    public String SimplifyPath(String A){
        StringBuilder result = new StringBuilder();
        if (A.isEmpty() || A.equals("/")) return "/";
        String[] strArr = A.split("/");
        Stack<String> pathStack = new Stack<>();
    
        for (int i = 0; i < strArr.length; i++) {
            String curr = strArr[i];
            // "/../" represents a backward move. so remove the last added element from the stack.
            if (curr.equals("..")) {
                if (!pathStack.isEmpty()) pathStack.pop();
                continue;
            }
            // "/./" represents the current directory. so dont do anything.
            if (curr.isEmpty() || curr.equals(".")) continue;
            // add the element to the stack.
            pathStack.push(curr);
        }
        for (String p : pathStack) {
            result.append("/");
            result.append(p);
        }
        if (result.length() == 0) result.append("/");
        return result.toString();
    }

    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();
        System.out.println(sp.SimplifyPath("/a/./b/../c/"));
    }

}
