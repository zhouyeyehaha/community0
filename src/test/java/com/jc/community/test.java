package com.jc.community;

import java.util.*;

public class test {
    public static void main(String[] args) {

//        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//        String digits = "23";
//        System.out.println(numString[digits.charAt(0) - '0']);
//        String str = numString[digits.charAt(0) - '0'];
//        System.out.println(str);
        Deque<String> deque = new LinkedList<>();
//        deque.removeLast();
        List<List<String>> lists = new ArrayList<>();

//        deque.add("x");
//        deque.add("c");
//        lists.add(new ArrayList(deque));
//        deque.add("y");
//        lists.add(new ArrayList(deque));
//        System.out.println(lists);

        String s = "352";
        String str = s.substring(0, 1);
//        System.out.println(str);
        int num = 0;
        num = num * 10 + (s.charAt(1) - '0');
        System.out.println(num);
//        Arrays.fill(used, false);
    }
}
