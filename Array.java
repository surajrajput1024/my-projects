import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Array {
    public static int largestElement(int[] nums){
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        return max;
    }

    public boolean check(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > nums[(i+1) % nums.length]) {
                count++;
            } 
        }

        return count <= 1;
    }

    public static int getSecondLargest(int[] nums) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > largest) {
                secondLargest = largest;
                largest = nums[i];
            } else if (nums[i] > secondLargest && nums[i] < largest) {
                secondLargest = nums[i];
            }
        }

        if(largest == Integer.MIN_VALUE || secondLargest == Integer.MIN_VALUE) {
            return -1;
        }
        
        return largest == secondLargest ? -1 : secondLargest;
    }

    public static int linearSearch(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == target) return i;
        }

        return -1;
    }

    public static void leftRotateByK(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n- k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n-1);

    }

    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;

            i++;
            j--;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare((b[0] * b[0] + b[1] * b[1]), (a[0] * a[0] + a[1] * a[1]))
        );

        for (int[] point : points) {
            maxHeap.add(point);
            
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }

     
    public static void main(String[] args) {
        int[]  nums = {1,2,3,4,5};

        System.out.println(getSecondLargest(nums));
    }


}
