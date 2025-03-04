import java.util.*;

public class Arrays {
    /**
     * Longest element in an array
     * @param nums
     */
    @SuppressWarnings("unused")
    private static int longestElement(int[] nums) {
        int max = Integer.MIN_VALUE;

        for(int num: nums) {
            max = Math.max(num, max);
        }

        return max;
    }

    // private static int secondLargestWithoutSorting(int[] nums) {
    // }

    /**
     * 
     */

    /**
     * Next permutation 
     * Probelem: https://leetcode.com/problems/next-permutation/
     * Video: https://www.youtube.com/watch?v=umhrBhdLkaY
     * 
     * Time complexity: O(3N)
     * Space Complexity: O(1)
     * Example: 4 1 5 3 2
     * @param nums
     */
    private static void nextPermutation(int[] nums) {
        int firstIndex = -1; // Index to track where we've found longest similar prefix 1 2 3, 1 3 2 so 1 is longest one
        int secondIndex = -1; // from which element to swap with the first index element
        int n = nums.length;

        // find first index by observing pattern from right --> left, first index = 1 that is element 1
        for(int i = n-2; i>=0; i--) {
          if(nums[i] < nums[i+1]) {
            firstIndex = i;
            break;
          }
        }

        // find second index, second index is 4 that is element 2. 
        for(int i = n - 1; i>=0; i--) {
            if(firstIndex != -1 && nums[i] > nums[firstIndex]) {
                secondIndex = i;
                break;
            }
        }

        // swap fist and second index elements, second index is, now array: 4 2 5 3 1
        if(firstIndex != secondIndex && firstIndex != -1 || secondIndex != -1) {
            swap(nums, firstIndex, secondIndex);
        }

        // reverse from first index + 1 to end of the array: array now - 4 2 1 3 5 
        reverse(nums,firstIndex + 1, n - 1);
    }

    /**
     * Leaders in an array
     * 
     * Problem: https://www.geeksforgeeks.org/problems/leaders-in-an-array-1587115620/1
     * Video: https://www.youtube.com/watch?v=cHrH9CQ8pmY
     * 
     * Time complexity: O(n)
     * Space: O(N)
     */
    private static ArrayList<Integer> leaders(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(nums[n-1]);
        int maxLeaders = nums[n-1];
        
        for(int i = n - 2; i >= 0; i--) {
            if(nums[i] >= maxLeaders) {
                maxLeaders = nums[i];
                ans.add(nums[i]);
            }
        }

        reverse(ans, 0, ans.size() - 1);

        return ans;
    }


    /**
     * Problem: https://leetcode.com/problems/longest-consecutive-sequence/
     * Video: https://www.youtube.com/watch?v=oO5uLE7EUlM
     * 
     * Time: O(n)
     * Space O(n)
     * 
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestStreak = 0; 

        for (int num : set) {
            // Check if 'num' is the start of a sequence (num-1 should not exist)
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // how long the consecutive sequence goes
                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    // ========= Helper functions =========
    private static void reverse(int[] nums, int left, int right) {
        
        while(left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private static void reverse(List<Integer> list, int left, int right) {
        while (left < right) {
            Collections.swap(list, left, right);
            left++;
            right--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {16, 17, 4, 3, 5, 2};
        nextPermutation(nums); 
       
        for (int i : nums) {
            System.out.print(i + " ");
        }

        ArrayList<Integer> leaders = leaders(nums);
        System.out.println(leaders.toString());
    }
}
