public class Arrays {
    /**
     * Next permutation 
     * Probelem: https://leetcode.com/problems/next-permutation/
     * Video: https://www.youtube.com/watch?v=umhrBhdLkaY
     * 
     * Time complexity: O(3N)
     * Space Complexity: O(1)
     * @param nums
     */
    private static void nextPermutation(int[] nums) {
        int firstIndex = 0; // Index to track where 
        int secondIndex = 0;
        int n = nums.length;

        for(int i = n-1; i>0; i--) {
          if(nums[i] > nums[i-1]) {
            firstIndex = i;
            break;
          }
        }

        for(int i = n-1; i >0; i--) {
            if(nums[i] > nums[firstIndex]) {
                secondIndex = i;
                break;
            }
        }

        swap(nums, firstIndex, secondIndex);

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
