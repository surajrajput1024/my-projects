package dsaproblems.leetcode75;

import java.util.Arrays;
import java.util.Stack;

public class LeetCode75 {
    /*
     * Pattern 1: Monotonic Stack
     * 
     * How to identify?
     * 1. The problem is asking for the next greater/smaller element on the left/right.
     * 2. The problem is asking for the maximum/minimum element in a sliding window.
     * 
     * Approach in simple words:
     * 1. Use a stack to keep track of the elements.
     * 2. Iterate through the array from left to right or right to left.
     * 3. For each element, pop the elements from the stack until the condition is satisfied.
     * 4. Push the current element into the stack.
     * 
     */
    /**
     * Daily Temperatures: LeetCode 739
     * 
     * Problem Statement in simple words:
     * Given an array of integers temperatures represents the daily temperatures, return an array answer
     * such that answer[i] is the number of days you have to wait after the ith day to get a warmer
     * temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
     * 
     * Example:
     * Input: temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
     * Output: [1, 1, 4, 2, 1, 1, 0, 0]
     * Explanation: For the first day, the next day is warmer. For the second day, the next day is warmer.
     * For the third day, the next day is warmer. For the fourth day, the next day is warmer. For the fifth
     * day, the next day is warmer. For the sixth day, the next day is warmer. For the seventh day, there is
     * no future day for which this is possible. For the eighth day, there is no future day for which this is
     * possible.
     * 
     * Didn't understand the problem? Let's understand the problem with an example:
     * Input: temperatures = [30, 40, 50, 60]
     * Output: [1, 1, 1, 0]
     * Explanation: For the first day, the next day is warmer. For the second day, the next day is warmer. For
     * the third day, the next day is warmer. For the fourth day, there is no future day for which this is
     * possible.
     * 
     * Do I need to get only next day? No, you need to get the number of days you have to wait after the ith
     * day to get a warmer temperature.
     * 
     * Can you explain me in other way, I didn't get it? Sure, let's take an example:
     * Input: temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
     * Output: [1, 1, 4, 2, 1, 1, 0, 0]
     * 
     * For the first day, the next day is warmer. So, the output is 1.
     * For the second day, the next day is warmer. So, the output is 1.
     * For the third day, the next day is warmer. So, the output is 4. Why? Because the next warmer day is at
     * the 6th index. So, the number of days you have to wait is 6 - 2 = 4.
     * For the fourth day, the next day is warmer. So, the output is 2. Why? Because the next warmer day is at
     * the 6th index. So, the number of days you have to wait is 6 - 3 = 3.
     * For the fifth day, the next day is warmer. So, the output is 1. Why? Because the next warmer day is at
     * the 6th index. So, the number of days you have to wait is 6 - 4 = 2.
     * 
     * Now got it.
     * 
     * Let's start with naive brute force approach:
     * What is intuition? For each element, iterate through the array to find the next warmer day.
     * Psuedo code:
     * 1. Initialize an array answer with 0s.
     * 2. Iterate through the array from left to right.
     * 3. For each element, iterate through the array from i + 1 to n.
     * 4. If the current element is less than the next element, update the answer[i] with the number of days
     * you have to wait.
     * 5. Return the answer.
     * 
     * Time complexity: O(n^2)
     * Space complexity: O(n) - answer array
     * Interviewer: Can you optimize it?
     * 
     * Let's optimize it using the monotonic stack approach: 
     * What is monotonic stack? A monotonic stack is a stack that is either entirely non-increasing or
     * non-decreasing. How will I make sure that?
     * 1. If you want to make sure that the stack is non-increasing, push the elements into the stack until
     * the current element is greater than the top of the stack.
     * 2. If you want to make sure that the stack is non-decreasing, push the elements into the stack until
     * the current element is less than the top of the stack.
     * 
     * Ok got it. Let's start with the intuition of optimizing the problem:
     * First of all, understand the intuition of the problem:
     * 1. Use a stack to keep track of the indices of the elements.
     * 2. Iterate through the array from left to right.
     * 3. For each element, pop the elements from the stack until the condition is satisfied.
     * 4. Push the current element into the stack.
     * 
     * Dry run the example: temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
     * 1. Initialize an array answer with 0s.
     * 2. Initialize a stack. Let's take an empty stack of integers.
     * 3. Iterate through the array from left to right.
     * 4. For the first element, the stack is empty. So, push the index 0 into the stack.
     * 5. For the second element, the stack is [0]. The current element is greater than the top of the stack.
     * So, pop the top of the stack and update the answer[0] with 1 - 0 = 1. Push the index 1 into the stack.
     * 6. For the third element, the stack is [1]. The current element is greater than the top of the stack.
     * So, pop the top of the stack and update the answer[1] with 2 - 1 = 1. Push the index 2 into the stack.
     * 7. For the fourth element, the stack is [2]. The current element is less than the top of the stack. So,
     * push the index 3 into the stack.
     * 8. For the fifth element, the stack is [2, 3]. The current element is less than the top of the stack. So,
     * push the index 4 into the stack.
     * 9. For the sixth element, the stack is [2, 3, 4]. The current element is greater than the top of the stack.
     * So, pop the top of the stack and update the answer[4] with 6 - 4 = 2. Push the index 5 into the stack.
     * 10. For the seventh element, the stack is [2, 5]. The current element is greater than the top of the stack.
     * So, pop the top of the stack and update the answer[5] with 6 - 5 = 1. Push the index 6 into the stack.
     * 11. For the eighth element, the stack is [2, 6]. The current element is less than the top of the stack. So,
     * push the index 7 into the stack.
     * 12. Return the answer.
     * 
     * Psuedo code:
     * 1. Initialize an array answer with 0s.
     * 2. Initialize a stack of integers.
     * 3. Iterate through the array from left to right.
     * 4. For each element, pop the elements from the stack until the condition is satisfied.
     * 5. If the stack is not empty, update the answer with the number of days you have to wait.
     * 6. Push the current element into the stack.
     * 7. Return the answer.
     * 
     * Time complexity: O(n)
     * Space complexity: O(2n) - answer array and stack
     * 
     * Can we optimise more? Yes, we can optimise the space complexity.
     * And till? We can optimise the space complexity to O(n). it's already polynomial time complexity.
     * 
     * We can do it using one array I guess.
     * 
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }

        return answer;
    }

    /**
     * Let's take another problem to understand the monotonic stack approach:
     * Online Stock Span: LeetCode 901
     * 
     * Problem Statement in simple words:
     * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of
     * that stock's price for the current day. The span of the stock's price today is defined as the maximum
     * number of consecutive days (starting from today and going backward) for which the price of the stock
     * was less than or equal to today's price.
     * 
     * 
     * Didn't understand the problem? Let's understand the problem with an example:
     * Input: ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"], [[], [100], [80], [60],
     * [70], [60], [75], [85]]
     * 
     * Output: [null, 1, 1, 1, 2, 1, 4, 6]
     * 
     * Explanation: StockSpanner stockSpanner = new StockSpanner(); stockSpanner.next(100); // return 1
     * stockSpanner.next(80); // return 1 stockSpanner.next(60); // return 1 stockSpanner.next(70); // return 2
     * stockSpanner.next(60); // return 1 stockSpanner.next(75); // return 4 stockSpanner.next(85); // return 6
     * 
     * Didn't get it?
     * Let me understand with simple example:
     * what's given?
     * what is stock span? The span of the stock's price today is defined as the maximum number of consecutive
     * days (starting from today and going backward) for which the price of the stock was less than or equal to
     * today's price.
     * 
     * Can you explain with array?
     * Input: [100, 80, 60, 70, 60, 75, 85]
     * Output: [1, 1, 1, 2, 1, 4, 6]
     * 
     * For the first element, the span is 1. Why? Because there is no element before the first element.
     * For the second element, the span is 1. Why? Because the previous element is greater than the current
     * element.
     * For the third element, the span is 1. Why? Because the previous element is greater than the current
     * element.
     * For the fourth element, the span is 2. Why? Because the previous element is less than the current element. why 2?
     *  because 70 is greater than 60 and 60 is less than 70.
     * 
     * Now let's come to the origin problem.
     * 
     * What is the problem saying is it giving array?
     * No, it's giving a sequence of operations. 
     * what are operations? ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
     * what are the prices? [[], [100], [80], [60], [70], [60], [75], [85]]
     * 
     * possible operations are StockSpanner and next.
     * what is next operation? It's giving the price of the stock. 
     * and what is StockSpanner operation? It's initializing the stock spanner. cool. 
     * 
     * and we need to return the span of the stock's price for the current day in array.
     * 
     * Let's start with naive brute force approach:
     * What is intuition? For each element, iterate through the array to find the span from the right to left.
     * Psuedo code:
     * 1. Initialize an array answer with 0s.
     * 2. Iterate through the array from left to right.
     * 3. For each element, iterate through the array from i - 1 to 0.
     * 4. If the current element is less than or equal to the next element, update the answer[i] with the span.
     * 5. Return the answer.
     * 
     * Time complexity: O(n^2)
     * Space complexity: O(n) - answer array
     * Interviewer: Can you optimize it?
     * 
     * Why monotonic stack approach?
     * 1. The problem is asking for the maximum number of consecutive days (starting from today and going
     * backward) for which the price of the stock was less than or equal to today's price.
     * 2. The problem is asking for the next greater/smaller element on the left/right.
     * 
     * Let's optimize it using the monotonic stack approach:
     * What is intuition? Use a stack to keep track of the indices of the elements.
     * Psuedo code:
     * 1. Initialize an array answer with 0s.
     * 2. Initialize a stack of integers.
     * 3. Iterate through the array from left to right.
     * 4. For each element, pop the elements from the stack until the condition is satisfied.
     * 5. If the stack is not empty, update the answer with the span.
     * 6. Push the current element into the stack.
     * 7. Return the answer.
     * 
     * Time complexity: O(n)
     * Space complexity: O(2n) - answer array and stack
     * 
     * Can we optimise more? Yes, we can optimise the space complexity.
     * And till? We can optimise the space complexity to O(n). it's already polynomial time complexity.
     * 
     * How should I approach it like it's given a class with one constructor and one method?
     * what will be constructor? It will initialize the stack.
     * what will be the method? It will return the span of the stock's price for the current day.
     * @param args
     * 
     * It's giving wrong output for the below input:
     * ["StockSpanner","next","next","next","next","next","next","next"]
     *  [[],[100],[80],[60],[70],[60],[75],[85]]
     * 
     * Expected output: [null,1,1,1,2,1,4,6]
     * 
     * getting [null,1,1,1,3,1,8,7] why? what could be possible issue?
     * 
     * Let's dry run the above code:
     * StockSpanner stockSpanner = new StockSpanner();
     * stockSpanner.next(100); // return 1 how? stack is empty. push 100 into the stack. return 1.
     * stockSpanner.next(80); // return 1 how? stack is [100]. 80 is less than 100. pop 100. return 1.
     * stockSpanner.next(60); // return 1 how? stack is [80]. 60 is less than 80. pop 80. return 1.
     * stockSpanner.next(70); // return 3 how? stack is [60, 70]. 70 is greater than 60. pop 60. 70 is greater
     * than 70. pop 70. return 3.
     * stockSpanner.next(60); // return 1 how? stack is [70, 60]. 60 is less than 70. pop 70. return 1.
     * stockSpanner.next(75); // return 8 how? stack is [60, 75]. 75 is greater than 60. pop 60. 75 is greater
     * than 75. pop 75. return 8.
     * stockSpanner.next(85); // return 7 how? stack is [75, 85]. 85 is greater than 75. pop 75. return 7.
     *  
     * but it's wrong?
     * it should return [null,1,1,1,2,1,4,6] ?
     * 
     * what could be the issue?
     * tell? will you be able to tell me?
     * 
     * We've already dried run the code. Let's see the issue:
     * 1. For the fourth element, the stack is [60, 70]. The current element is greater than the top of the stack.
     * So, pop the top of the stack and update the answer[3] with 3 - 2 = 1. Push the index 3 into the stack.
     * 2. For the sixth element, the stack is [60, 75]. The current element is greater than the top of the stack.
     * So, pop the top of the stack and update the answer[5] with 5 - 4 = 1. Push the index 5 into the stack.
     * 3. For the seventh element, the stack is [60, 75, 85]. The current element is greater than the top of the stack.
     * So, pop the top of the stack and update the answer[6] with 6 - 5 = 1. Push the index 6 into the stack.
     * 4. For the eighth element, the stack is [60, 75, 85]. The current element is greater than the top of the stack.
     * So, pop the top of the stack and update the answer[7] with 7 - 6 = 1. Push the index 7 into the stack.
     * 
     * where things are going wrong?
     * 
     * What would be time complexity? O(n) as we are iterating through the array.
     * 
     * what would be space complexity? O(2n) as we are using two stacks. 
     */
    class StockSpanner {
        Stack<Integer> prices;
        Stack<Integer> index;

        public StockSpanner() {
            prices = new Stack<>();
            index = new Stack<>();
        }
        
        public int next(int price) {
            int span = 1;
            while(!prices.isEmpty() && prices.peek() <= price) {
                prices.pop();
                span += index.pop();
            }
            prices.push(price);
            index.push(span);
            return span;
        }
    }

    /**
     * Have you changed the approach?
     * Yes, I've changed the approach.
     * what is the new approach? I'm using two stacks to keep track of the prices and indices.
     * 
     * Let me understand the new approach:
     * 1. Use two stacks to keep track of the prices and indices. why two stacks? To keep track of the prices
     * and indices. but why do we need both things? 
     * 2. Iterate through the array from left to right.
     * 3. For each element, pop the elements from the stack until the condition is satisfied.
     * 4. If the stack is not empty, update the answer with the span.
     * 5. Push the current element into the stack.
     * 6. Return the answer.
     * 
     * @param args
     */


    /**
     * Let's dry run above code:
     * StockSpanner stockSpanner = new StockSpanner();
     * stockSpanner.next(100); // return 1 
     * stockSpanner.next(80); // return 1
     * stockSpanner.next(60); // return 1
     * stockSpanner.next(70); // return 2
     * stockSpanner.next(60); // return 1
     * stockSpanner.next(75); // return 4
     * stockSpanner.next(85); // return 6
     * 
     * why do we need prices array? To keep track of the prices.
     * why do we need index? To keep track of the index.
     * @param args
     * 
     * will more problems on monotonic stack approach in the next session.
     */

     // ====================
     // Pattern 2: Intervals 
     // ====================
     /**
      * Pattern 2: Intervals
      * what is the interval? An interval is a set of values between two endpoints. 
      * What is the interval pattern? The interval pattern is used to solve problems involving intervals.
      *
      * How to identify? 
        * 1. The problem is asking for the maximum number of non-overlapping intervals.
        * 2. The problem is asking for the minimum number of intervals to remove to make the intervals non-overlapping.
        * 3. The problem is asking for the maximum number of intervals that can be merged or overlapped or
        * non-overlapping or merged or removed or added or inserted or deleted or combined or covered or
        * intersected or combined or spanned or covered or intersected or combined or spanned or covered.
        * 
        * Some of the real world examples:
        * 1. Meeting rooms: Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
        * return the minimum number of conference rooms required. (But this is greedy approach right?)
        *  above is greedy ? yes, it's greedy approach. 

        * 2. Non-overlapping intervals: Given an array of intervals intervals where intervals[i] = [starti, endi],
        * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

        Let's start with the first problem:
      * @param args
      */
      /**
       * Non-overlapping Intervals: LeetCode 435
       * 
       * Problem Statement in simple words:
       * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of
       * intervals you need to remove to make the rest of the intervals non-overlapping.
       * 
       * Example:
       * Input: intervals = [[1, 2], [2, 3], [3, 4], [1, 3]]
       * Output: 1
       * Explanation: [1, 3] can be removed and the rest of the intervals are non-overlapping.
       * 
       * Didn't understand the problem? Let's understand the problem with an example:
       * Input: intervals = [[1, 2], [2, 3], [3, 4], [1, 3]]
       * Output: 1
       * 
       * For the first interval, the start is 1 and the end is 2.
       * For the second interval, the start is 2 and the end is 3.
       * For the third interval, the start is 3 and the end is 4.
       * For the fourth interval, the start is 1 and the end is 3 and it's overlapping with the first interval.
       * 
       * Do I need to get the overlapping intervals? No, you need to return the minimum number of intervals you
       * need to remove to make the rest of the intervals non-overlapping.
       * 
       * Okay let's jump on intuition behind solving the problem:
       * Let's start with naive brute force approach:
       * What is intuition? For each interval, iterate through the array to find the overlapping intervals.
       * Psuedo code:
       * 1. Initialize a variable count with 0.
       * 2. Iterate through the array from left to right.
       * 3. For each interval, iterate through the array from i + 1 to n.
       * 4. If the current interval is overlapping with the next interval, increment the count.
       * 5. Return the count.
       * 
       * Time complexity: O(n^2)
       * Space complexity: O(1)
       * Interviewer: Can you optimize it?
       * 
       * Let's optimize it using the intervals approach or greedy approach:
       * from where did you get the greedy approach? It's a greedy approach as we are sorting the intervals.
       * 
       * how will i know i need to sort based on end time? 
       * 
       * Now start giving example to optimise it using greedy approach:
       * and dry running the code.
       * 
       * interval = [[1, 2], [2, 3], [3, 4], [1, 3]]
       * 
       * how will i get to know i need to sort explain that? 
       * 
       * Optimised approach:
       * 1. Sort the intervals based on the end time. why end time? To get the overlapping intervals. 
       * 2. Initialize a variable count with 0.
       * 3. Initialize a variable end with the first interval's end time.
       * 4. Iterate through the array from left to right.
       * 5. For each interval, if the start time is less than the end time, increment the count.
       * 6. If the start time is greater than the end time, update the end time with the current interval's end time.
       * 7. Return the count.
       * 
       * Time complexity: O(nlogn) - sorting + O(n) - iterating = O(nlogn)
       * Space complexity: O(1)
       * 
       * Let's dry run the above code:
       * interval = [[1, 2], [2, 3], [3, 4], [1, 3]]
       * 1. Sort the intervals based on the end time. [[1, 2], [1, 3], [2, 3], [3, 4]]
       * 2. Initialize a variable count with 0.
       * 3. Initialize a variable end with the first interval's end time. end = 2.
       * 4. Iterate through the array from left to right.
       * 5. For the first interval, the start time is less than the end time. So, increment the count. count = 1.
       * 6. end = 2. 
       * 7. For the second interval, the start time is greater than the end time. So, update the end time with the
       * current interval's end time. end = 3.
       * 8. For the third interval, the start time is less than the end time. So, increment the count. count = 2.
       * 9. end = 3.
       * 10. For the fourth interval, the start time is less than the end time. So, increment the count. count = 3.
       * 11. Return the count.
       * 
       * Can we optimise more? No, it's already optimal.
       * 
       * @param args
       */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 0;
        int end = intervals[0][1];

        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < end) {
                count++;
            } else {
                end = intervals[i][1];
            }
        }
        return count;
    }

    /**
     * Let's take another example of pattern 2 that is intervals:
     * 
     * Minimum number of arrows to burst balloons: LeetCode 452
     * 
     * Problem Statement in simple words:
     * There are some spherical balloons spread in two-dimensional space. For each balloon, provided input is
     * the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't
     * matter, and hence the x-coordinates of start and end of the diameter suffice. The start is always smaller
     * than the end. An arrow can be
     * shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts
     * 
     * what is matter? 
     * 
     * 
     * @param args
     */

    public static void main(String[] args) {

    }
}
