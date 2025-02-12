public class Arrays {
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

    // ========= Helper functions =========
    private static void reverse(int[] nums, int left, int right) {
        
        while(left < right) {
            swap(nums, left, right);
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
        int[] nums = {1,2,3};
        nextPermutation(nums); 
       
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}
