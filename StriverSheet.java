import java.util.*;
import java.util.Arrays;

public class StriverSheet {

  // ============================
  // ========== SORTING ==========
  // ============================

  /**
   * Selection Sort: 
   * - Find the minimum element in the array and swap it with the first element
   * - Repeat the process for the remaining elements
   * - Time complexity: O(n^2)
   * - Space complexity: O(1)
   * 
   */
  private static void selectionSort(int[] nums) {
    int n = nums.length;
    int minIndex = 0;

    for(int i = 0; i < n-1; i++) {
      minIndex = i;

      for(int j = i+1; j < n; j++) {
        if(nums[j] < nums[minIndex]) {
          minIndex = j;
        }
      }

      int temp = nums[minIndex];
      nums[minIndex] = nums[i];
      nums[i] = temp;
    }
  }

  /**
   * Bubble Sort: 
   * - Compare adjacent elements and swap them if they are in the wrong order
   * - Repeat the process until the array is sorted
   * - Time complexity: O(n^2)
   * - Space complexity: O(1)
   */
  private static void bubbleSort(int[] nums) {
    int n = nums.length;

    for(int i = 0; i < n-1; i++) {
      for(int j = 0; j < n-i-1; j++) {
        if(nums[j] > nums[j+1]) {
          int temp = nums[j];
          nums[j] = nums[j+1];
          nums[j+1] = temp;
        }
      }
    }
  }

  /**
   * Insertion Sort: 
   * - Insert the current element in the correct position in the sorted part of the array
   * - Repeat the process until the array is sorted
   * - Time complexity: O(n^2)
   * - Space complexity: O(1)
   */
  private static void insertionSort(int[] nums) {
    int n = nums.length;

    for(int i = 1; i < n; i++) {
      int key = nums[i];
      int j = i-1;

      while(j >= 0 && nums[j] > key) {
        nums[j+1] = nums[j];
        j--;
      }

      nums[j+1] = key;
    }
  }

  /**
   * Merge Sort Recursive: 
   * - Divide the array into two halves
   * - Sort the two halves
   * - Merge the two sorted halves
   * - Time complexity: O(n log n)
   * - Space complexity: O(n)
   */
//  private static void mergeSortRecursive(int[] nums) {
//    int n = nums.length;
//
//    if(n <= 1) {
//      return;
//    }
//
//    int mid = n / 2;
//    int[] left = Arrays.copyOfRange(nums, 0, mid);
//    int[] right = Arrays.copyOfRange(nums, mid, n);
//
//    mergeSortRecursive(left);
//    mergeSortRecursive(right);
//
//    merge(nums, left, right);
//  }

  /**
   * Merge two sorted arrays
   * - Time complexity: O(n + m)
   * - Space complexity: O(n + m)
   */
  private static void merge(int[] nums, int[] left, int[] right) {
    int i = 0, j = 0, k = 0;

    while(i < left.length && j < right.length) {
      if(left[i] <= right[j]) {
        nums[k++] = left[i++];
      } else {
        nums[k++] = right[j++];
      }
    }

    while(i < left.length) {
      nums[k++] = left[i++];
    }

    while(j < right.length) {
      nums[k++] = right[j++];
    }
  } 

  /** 
   * Recursive bubble sort 
   * - Time complexity: O(n^2)
   * - Space complexity: O(n)
   */
  private static void bubbleSortRecursive(int[] nums, int n) {
    if(n == 1) {
      return;
    }

    for(int i = 0; i < n-1; i++) {
      if(nums[i] > nums[i+1]) {
        int temp = nums[i];
        nums[i] = nums[i+1];
        nums[i+1] = temp;
      }
    }

    bubbleSortRecursive(nums, n-1);
  }

  /**
   * Recursive insertion sort
   * - Time complexity: O(n^2)
   * - Space complexity: O(n)
   */
  private static void insertionSortRecursive(int[] nums, int n) {
    if(n <= 1) {
      return;
    }

    insertionSortRecursive(nums, n-1);

    int last = nums[n-1];
    int j = n-2;

    while(j >= 0 && nums[j] > last) {
      nums[j+1] = nums[j];
      j--;
    }

    nums[j+1] = last;
  }

  /**
   * Partition the array
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int partition(int[] nums, int low, int high) {
    int pivot = nums[low];
    int i = low;
    int j = high;

    while(i < j) {
      while(nums[i] <= pivot && i < high) {
        i++;
      }

      while(nums[j] > pivot && j > low) {
        j--;
      }

      if(i < j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
      }
    }

    int temp = nums[low];
    nums[low] = nums[j];
    nums[j] = temp;

    return j;
  }

  /** Quick sort
   * - Time complexity: O(n log n)
   * - Space complexity: O(log n)
   */
  private static void quickSort(int[] nums, int low, int high) {
    if(low < high) {
      int pivot = partition(nums, low, high);
      quickSort(nums, low, pivot-1);
      quickSort(nums, pivot+1, high);
    }
  }

  /**
   * 
   */
  // ============================
  // ========== ARRAYS ==========
  // ============================

  /**
   * Find the largest element in an array
   * 
   * 1. Brute force approach:
   * - Sort the array and return the last element
   * - Time complexity: O(n log n)
   * - Space complexity: O(1)
   * 
   * 2. Optimal approach:
   * - Iterate through the array and keep track of the largest element
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int largetElementInAnArray(int[] nums) {
    int max = nums[0];

    for (int num : nums) {
      max = Math.max(max, num);
    }

    return max;
  }

  /**
   * Find the second largest element in an array
   * 
   * 1. Brute force approach:
   * - Sort the array and return the second last element
   * - Time complexity: O(n log n)
   * - Space complexity: O(1)
   * 
   * 2. Optimal approach:
   * - Iterate through the array and keep track of the largest and second largest elements
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int secondLargestElementInAnArray(int[] nums) {
    int largest = Integer.MIN_VALUE;
    int secondLargest = Integer.MIN_VALUE;

    for(int num : nums) {
      if(num > largest) {
        secondLargest = largest;
        largest = num;
      } else if (num > secondLargest && num != largest) {
        secondLargest = num;
      }
    }

    return secondLargest;
  }

  /**
   * Check if an array is sorted and rotated
   * 
   * 1. Optimal approach:
   * - Check if the array is sorted and rotated by iterating through the array and checking if the next element is greater than the current element
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   * 
   */
  private static boolean checkIfArrayIsSortedAndRotated(int[] nums) {
    int count = 0;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > nums[(i + 1) % nums.length]) {
        count++;
      }
    }

    return count <= 1;
  }

  /**
   * Remove duplicates from a sorted array and return the new length of the array after removing duplicates
   * Note: The array is sorted in non-decreasing order and we need to modify the array in place
   * 
   * 1. Brute force approach:
   * - Use a set to store the unique elements
   * - Time complexity: O(n)
   * - Space complexity: O(n)
   * 
   * 2. Optimal approach:
   * - Use two pointers to iterate through the array
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   * 
   * @param nums
   * @return
   */
  private static int removeDuplicates(int[] nums) {
    int i = 0;
    int n = nums.length;
    for(int j = 1; j < n; j++) {
       if(nums[j] != nums[i]) {
        nums[i+1] = nums[j];
        i++;
       }
    }

    return i + 1;
  }

  private static void removeDuplicates(ArrayList<Integer> nums) {
    int i = 0;
    int n = nums.size();
    for(int j = 1; j < n; j++) {
      if(nums.get(j) != nums.get(i)) {
        nums.set(i+1, nums.get(j)); 
        i++;
      }
    }

    nums.subList(0, i+1).clear();
  }
  


  /**
   * Left rotate an array by one position
   * 
   * 1. Brute force approach:
   * - Use a new array to store the rotated array
   * - Time complexity: O(n)
   * - Space complexity: O(n)
   * 
   * 2. Optimal approach:
   * - Use a single variable to store the first element
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int[] leftRotateArrayByOne(int[] nums) {
    int n = nums.length;
    int temp = nums[0];

    for(int i = 1; i < n; i++) {
      nums[i-1] = nums[i];
    }

    nums[n-1] = temp;

    return nums;
  }

  private static void reverseArray(int[] nums, int start, int end) {
    while(start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
      start++;
      end--;
    }
  }

  /**
   * Left rotate an array by d positions
   * 
   * 1. Brute force approach:
   * - Use a new array to store the rotated array
   * - Time complexity: O(n)
   * - Space complexity: O(n)
   * 
   * 2. Optimal approach:
   * - Reverse the array in parts: reverse the first d elements, reverse the remaining elements, and then reverse the entire array
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int[] leftRotateArrayByDPositions(int[] nums, int d) {
    int n = nums.length;

    reverseArray(nums, 0, d-1);
    reverseArray(nums, d, n-1);
    reverseArray(nums, 0, n-1);

    return nums;
  }

  /**
   * Right rotate an array by d positions
   * 
   * 1. Brute force approach:
   * - Use a new array to store the rotated array
   * - Time complexity: O(n)
   * - Space complexity: O(n)
   * 
   * 2. Optimal approach:
   * - Reverse the array in parts: reverse the first n-d elements, reverse the remaining elements, and then reverse the entire array
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int[] rightRotateArrayByDPositions(int[] nums, int d) {
    int n = nums.length;

    reverseArray(nums, 0, n-d-1);
    reverseArray(nums, n-d, n-1);
    reverseArray(nums, 0, n-1);

    return nums;
  }

  /**
   * Move all zeroes to the end of the array
   * 
   * Note: The array is sorted in non-decreasing order and we need to modify the array in place
   * 
   * 1. Brute force approach:
   * - Use a new array to store the non-zero elements
   * - Time complexity: O(n)
   * - Space complexity: O(n)
   * 
   * 2. Optimal approach:
   * - Use two pointers to iterate through the array
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int[] moveZeroesToEnd(int[] nums) {
    int n = nums.length;
    int left = 0; // pointer to the first zero
    int right = 0; // pointer to the first non-zero element

    while(right < n) {
      if(nums[right] != 0) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
        left++;
      }
      right++;
    }

    return nums;
  }


  /** 
   * Linear search in an array
   * 
   * 1. Optimal approach:
   * - Use a for loop to iterate through the array
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int linearSearch(int[] nums, int target) {
    int n = nums.length;

    for(int i = 0; i < n; i++) {
      if(nums[i] == target) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Find the union of two sorted arrays
   * 
   * 1. Brute force approach: 
   * - Use a set to store the union of the two arrays
   * - Time complexity: O(n log n + m log m)
   * - Space complexity: O(n + m)
   * 
   */
  private static List<Integer> findUnionOfTwoSortedArrays(int[] nums1, int[] nums2) {
    HashSet<Integer> set = new HashSet<>();
    
    for(int num : nums1) {
      set.add(num);
    }

    for(int num : nums2) {
      set.add(num);
    } 

    List<Integer> ans = new ArrayList<>(set);
    return ans;
  }

  /**
   * Find the union of two sorted arrays (Most Optimal)
   * 
   * Optimal approach:
   * - 
   */
  private static ArrayList<Integer> findUnionOfTwoSortedArraysOptimal(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    int i = 0;
    int j = 0;

    ArrayList<Integer> ans = new ArrayList<>();

    while(i < n && j < m) {
      int lastElement = ans.isEmpty() ? Integer.MIN_VALUE : ans.get(ans.size() - 1);

      if(nums1[i] <= nums2[j]) {
        if(nums1[i] != lastElement) {
          ans.add(nums1[i]);
        }
        i++;
      } else {
        if(nums2[j] != lastElement) {
          ans.add(nums2[j]);
        }
        j++;
      }
    }

    while(i < n) {
      if(ans.isEmpty() || ans.get(ans.size() - 1) != nums1[i]) {
        ans.add(nums1[i]);
      }
      i++;
    }

    while(j < m) {
      if(ans.isEmpty() || ans.get(ans.size() - 1) != nums2[j]) {
        ans.add(nums2[j]);
      }
      j++;  
    }

    return ans;
  }

  /**
   * Find the intersection of two sorted arrays
   * 
   * Brute force approach:
   * - Use one visited array to store the elements of the first array (ideally smaller array)
   * - Time complexity: O(n * m)
   * - Space complexity: O(n)
   */
  private static int[] findIntersectionOfTwoSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    int[] visited = new int[n];
    int[] ans = new int[n];
    for(int i = 0; i < n; i++) {
      visited[i] = 0;
    }

    int k = 0;
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        if(nums1[i] == nums2[j] && visited[i] == 0) {
          visited[i] = 1;
          ans[k++] = nums1[i];
        }
      }
    }

//    return Arrays.copyOf(ans, k)
    return  Arrays.copyOfRange(ans, 0, k);
  }

  /**
   * Find the intersection of two sorted arrays (Most Optimal)
   * 
   * Optimal approach:
   * - Use a hashset to store the elements of the first array
   * - Iterate through the second array and check if the element is present in the hashset
   * - If it is, add it to the result array
   * - Time complexity: O(n + m)
   * - Space complexity: O(n)
   */
  private static int[] findIntersectionOfTwoSortedArraysOptimal(int[] nums1, int[] nums2) {
    HashSet<Integer> set1 = new HashSet<>();
    HashSet<Integer> resultSet = new HashSet<>();

    for (int num : nums1) {
        set1.add(num);
    }

    for (int num : nums2) {
        if (set1.contains(num)) {
            resultSet.add(num);
        }
    }

    int[] result = new int[resultSet.size()];
    int i = 0;
    for (int num : resultSet) {
        result[i++] = num;
    }

    return result;
  }

  /**
   * Find the missing number in an array
   * Note: Numbers should be from 0 to N
   * @link: https://leetcode.com/problems/missing-number/description/
   * 
   * Brute force approach:
   * - Sort the array and check for missing number
   * - Time complexity: O(n log n)
   * - Space complexity: O(1)
   */
  private static int findMissingNumberBruteForce(int[] nums) {
    Arrays.sort(nums);
    
    // Check if 0 is missing
    if (nums[0] != 0) return 0;
    
    // Check for missing number in between
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] != nums[i-1] + 1) {
            return nums[i-1] + 1;
        }
    }
    
    // If no number is missing in between, return n
    return nums.length;
  }

  /**
   * Find the missing number in an array
   * Note: Numbers should be from 0 to N
   * 
   * Better approach:
   * - Use sum formula: sum of first n numbers = n*(n+1)/2
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int findMissingNumberBetter(int[] nums) {
    int n = nums.length;

    // Sum from 0 to n
    int expectedSum = (n * (n + 1)) / 2;  
    int actualSum = 0;
    
    for (int num : nums) {
        actualSum += num;
    }
    
    return expectedSum - actualSum;
  }

  /**
   * Find the missing number in an array
   * Note: Numbers should be from 0 to N
   * 
   * Optimal approach:
   * - Use XOR properties: a^a = 0 and a^0 = a
   * - XOR all numbers from 0 to N with array elements
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int findMissingNumberOptimal(int[] nums) {
    int n = nums.length;
    int xor = 0;
    
    // XOR all numbers from 0 to n
    for (int i = 0; i <= n; i++) {
        xor ^= i;
    }
    
    // XOR with array elements
    for (int num : nums) {
        xor ^= num;
    }
    
    return xor;
  }

  /**
   * Find the maximum number of consecutive 1s in an array
   * 
   * Optimal approach:
   * - Use a for loop to iterate through the array
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int maxConsecutiveOnes(int[] nums) {
    int max = 0;
    int count = 0;

    for(int i = 0; i < nums.length; i++) {
      if(nums[i] == 1) {
        count++;
      } else {
        max = Math.max(max, count);
        count = 0;
      }
    }

    return Math.max(max, count);
  }

  /**
   * Find the single number in which every element appears twice except one
   * 
   * Brute force approach:
   * - Use a for loop to iterate through the array
   * - Time complexity: O(n^2)
   * - Space complexity: O(1)
   */
  private static int findSingleNumberBruteForce(int[] nums) {
    int n = nums.length;

    for(int i = 0; i < n; i++) {
      int count = 0;

      for(int j = 0; j < n; j++) {
        if(nums[i] == nums[j]) {
          count++;
        }
      }

      if(count == 1) {
        return nums[i];
      }
    }

    return -1;
  }

  /**
   * Find the single number in which every element appears twice except one
   * 
   * Optimal approach:
   * - Use XOR properties: a^a = 0 and a^0 = a
   * - XOR all elements in the array
   * - Time complexity: O(n)
   * - Space complexity: O(1)
   */
  private static int findSingleNumberOptimal(int[] nums) {
    int xor = 0;

    for(int num : nums) {
      xor ^= num;
    }

    return xor;
  }

  /**
   * Find the longest subarray with sum k
   * 
   * Brute force approach:
   * - Use a for loop to iterate through the array'
   * - Time complexity: O(n^2)
   * - Space complexity: O(1)
   */
  private static int findLongestSubarrayWithSumKBruteForce(int[] nums, int k) {
    int n = nums.length;
    int maxLength = 0;
    for(int i = 0; i < n; i++) {
      int sum = 0;

      for(int j = i; j < n; j++) {
        sum += nums[j];

        if(sum == k) {
          maxLength = Math.max(maxLength, j - i + 1);
        }
      }
    }

    return maxLength;
  }

  /**
   * Find the longest subarray with sum k
   * - Use a hashmap to store the prefix sum and its index
   * 
   * Optimal approach:
   * - Use a hashmap to store the prefix sum and its index
   * - Time complexity: O(n)
   * - Space complexity: O(n)
   */
  private static int findLongestSubarrayWithSumKOptimal(int[] nums, int k) {
    int n = nums.length;
    int maxLength = 0;
    int sum = 0;

    HashMap<Integer, Integer> map = new HashMap<>();

    for(int i = 0; i < n; i++) {
      sum += nums[i];

      if(sum == k) {
        maxLength = Math.max(maxLength, i + 1);
      }

      if(map.containsKey(sum - k)) {
        maxLength = Math.max(maxLength, i - map.get(sum - k));
      }

      if(!map.containsKey(sum)) {
        map.put(sum, i);
      }
    }

    return maxLength;
  }



  public static void main(String[] args) {
    System.out.println("======= Driver code here =======");

    int[] nums = {1, 2,3,4,3,4,1111,1111};
    int[] nums2 = {3,4,5,1,2};
    int[] nums3 = {2,1,3,4};
    int[] nums4 = {1,2,3,4,5};
    int[] nums5 = {1, 2,3,3,4,4,5,5};
    int[] nums6 = {1,2,3,4,5};
    int[] nums7 = {0,1,0,3,12};
    int[] nums8 = {0,1,2,3,4,5,6,7,8,10};
    int[] nums9 = {1,1,0,1,1,1,0,1,1,1,1,1,1};
    int[] nums10 = {1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9};


    System.out.println("Max element in an array is: " + largetElementInAnArray(nums));
    System.out.println("Second largest element in an array is: " + secondLargestElementInAnArray(nums));
    System.out.println("Check if array is sorted and rotated: " + checkIfArrayIsSortedAndRotated(nums2));
    System.out.println("Check if array is sorted and rotated: " + checkIfArrayIsSortedAndRotated(nums3));
    System.out.println("Check if array is sorted and rotated: " + checkIfArrayIsSortedAndRotated(nums4));

    System.out.println("Remove duplicates from a sorted array: " + removeDuplicates(nums5));
    System.out.println("Left rotate array by one: " + Arrays.toString(leftRotateArrayByOne(nums6)));
    System.out.println("Left rotate array by d positions: " + Arrays.toString(leftRotateArrayByDPositions(nums6, 2)));
    System.out.println("Right rotate array by d positions: " + Arrays.toString(rightRotateArrayByDPositions(nums6, 2)));
    System.out.println("Move zeroes to the end: " + Arrays.toString(moveZeroesToEnd(nums7)));
    System.out.println("Linear search: " + linearSearch(nums, 1111));
    System.out.println("Union of two sorted arrays: " + findUnionOfTwoSortedArrays(nums5, nums6));
    System.out.println("Union of two sorted arrays optimal: " + findUnionOfTwoSortedArraysOptimal(nums5, nums6));
    System.out.println("Missing number in an array: " + findMissingNumberBruteForce(nums8));
    System.out.println("Missing number in an array optimal: " + findMissingNumberOptimal(nums8));
    System.out.println("Missing number in an array better: " + findMissingNumberBetter(nums8));
    System.out.println("Max consecutive ones: " + maxConsecutiveOnes(nums9));
    System.out.println("Single number in an array: " + findSingleNumberBruteForce(nums10));
    System.out.println("Single number in an array optimal: " + findSingleNumberOptimal(nums10));
    System.out.println("Longest subarray with sum k: " + findLongestSubarrayWithSumKBruteForce(nums10, 10));
    System.out.println("Longest subarray with sum k optimal: " + findLongestSubarrayWithSumKOptimal(nums10, 10));
  }

}
