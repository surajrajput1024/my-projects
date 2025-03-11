package dsaproblems.greedy;

import java.util.ArrayList;
import java.util.List;

public class Candy135 {
    private static int minCandyBruteForce(int[] candies) {
        int n = candies.length;
        List<Integer> left = new ArrayList<>();
        left.add(1);
        List<Integer> right = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            right.add(1);
        }

        for (int i = 1; i < n; i++) {
            if(candies[i] > candies[i-1]) {
                left.add(left.get(i-1) + 1);
            } else {
                left.add(1);
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if(candies[i] > candies[i+1]) {
                right.set(i, right.get(i+1) + 1);
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(left.get(i), right.get(i));
        }

        return  sum;
    }

    private static int minCandyBetter(int[] nums) {
        int n = nums.length;
        int left[] = new int[n];
        left[0] = 1;



        for (int i = 1; i < n; i++) {
            if(nums[i] > nums[i-1]) {
                left[i] = left[i-1] + 1;
            } else {
                left[i] = 1;
            }
        }

        int right = 1, current = 1, sum = Math.max(left[n - 1], 1);

        for (int i = n - 2; i >= 0; i--) {
            if(nums[i] > nums[i+1]) {
                right = current + 1;
                current = right;
            } else {
                current = 1;
            }
            sum += Math.max(current, left[i]);
        }

        return sum;
    }

    private static int candy(int[] ratings) {
        int n = ratings.length;
        int totalCandies = n;
        int i = 1;

        while (i < n) {
            if (ratings[i] == ratings[i - 1]) {
                i++;
                continue;
            }

            int currentPeak = 0;
            while (i < n && ratings[i] > ratings[i - 1]) {
                currentPeak++;
                totalCandies += currentPeak;
                i++;
            }

            if (i == n) {
                return totalCandies;
            }

            int currentValley = 0;
            while (i < n && ratings[i] < ratings[i - 1]) {
                currentValley++;
                totalCandies += currentValley;
                i++;
            }

            totalCandies -= Math.min(currentPeak, currentValley);
        }

        return totalCandies;        
     }    

    public static void main(String[] args) {
        int[] candies = {1, 0, 2};
        System.out.println(candy(candies));
    }
}
