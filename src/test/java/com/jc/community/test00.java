package com.jc.community;

import java.util.*;

public class test00 {

    static class Solution {
        List<List<String>> lists = new ArrayList<>();
        Deque<String> deque = new LinkedList<>();

        public List<List<String>> partition(String s) {
            backTracking(s, 0);
            return lists;
        }

        private void backTracking(String s, int startIndex) {
            //如果起始位置大于s的大小，说明找到了一组分割方案
            //System.out.println("第" + startIndex + "次");
            if (startIndex >= s.length()) {
                lists.add(new ArrayList(deque));
                //System.out.println("第" + startIndex + "次" + lists);
                return;
            }
            for (int i = startIndex; i < s.length(); i++) {
                //如果是回文子串，则记录
                if (isPalindrome(s, startIndex, i)) {
                    //System.out.println("startIndex" + startIndex + "i" + i);
                    String str = s.substring(startIndex, i + 1);
                    deque.addLast(str);
                    //System.out.println("第" + startIndex + "次" + deque);
                } else {
                    continue;
                }
                //起始位置后移，保证不重复
                backTracking(s, i + 1);
                deque.removeLast();
                //System.out.println(startIndex + "xxx" + deque);
            }
        }
        //判断是否是回文串
        private boolean isPalindrome(String s, int startIndex, int end) {
            for (int i = startIndex, j = end; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        String a = "aab";
        Solution solution = new Solution();
        List<List<String>> partition = solution.partition(a);
        System.out.println(partition);
    }
}
