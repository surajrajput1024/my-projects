import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ListNode {
    int data;
    ListNode next;

    public ListNode(int data){
        this.data = data;
    }
}

class BinaryTreeNode {
    int data;
    BinaryTreeNode left, right;

    public BinaryTreeNode(int data) {
        this.data = data;
        left = right = null;
    }
}

public class Blind75 {
    private static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if(map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{-1,-1};
    }

    private static int maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            int cost = prices[i] - min;
            maxProfit = Math.max(maxProfit, cost);
            min = Math.min(prices[i], min);
        }

        return maxProfit;
    }

    private static boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            if(freq.containsKey(num)) {
                freq.replace(num, freq.get(num) + 1);
            } else {
                freq.put(num, 1);
            }
        }


        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if(e.getValue() > 1) {
                return true;
            }
        }

        return false;
    }

    private static int[] productExceptSelf(int[] nums) {
         HashMap<Integer, ArrayList<Integer>> map = new HashMap<>(); 
         // Note: we can do it using two array left and right  instead of using hashmap but okay.

         int prod = 1;
         for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(prod);
            map.put(nums[i], arr);

            prod *= nums[i];
         }

         prod = 1;
         for (int i = nums.length - 1; i >=  0; i--) {
            map.get(nums[i]).add(prod);

            prod *= nums[i];
         }

         for (int i = 0; i < nums.length; i++) {
            nums[i] = map.get(nums[i]).get(0) * map.get(nums[i]).get(1);
         }

         return nums;
    }

    // Kadane's algo 
    private static int maxSubarraySum(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            sum += num;

            if(sum > max) {
                max = sum;
            }

            if(sum < 0) {
                sum = 0;
            }
        }

        return max;
    }

    private static int maxSubarrayProd(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int suffix = 1, prefix = 1;

        for (int i = 0; i < n; i++) {
            if(prefix == 0) prefix = 1;
            if(suffix == 0) suffix = 1;

            prefix *= nums[i];
            suffix *= nums[n - i - 1];

            max = Math.max(max, Math.max(prefix, suffix));
        }

        return max;
    }

    private static int findMinInRotatedArray(int[] nums) {
      int left = 0;
      int right = nums.length - 1;

      int mid = left + (right - left) / 2;

     if(nums[0]<nums[right]) return nums[0];

      while (left < right) {
        if(nums[mid] > nums[mid + 1]) return nums[mid+1];
        if(nums[mid -1] > nums[mid]) return nums[mid];

        // check wheather we are in first half or second half
        if(nums[mid] > nums[0]) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }

        mid = left + (right - left) / 2;
      }

      return nums[left];
    }

    private static boolean isValidPartition(String num, int remSum) {
        if (remSum < 0) {
            return false; 
        }
    
        if (num.isEmpty()) {
            return remSum == 0;
        }
    
        for (int i = 1; i <= num.length(); i++) {
            String currentSubString = num.substring(0, i);
            int value = Integer.parseInt(currentSubString);
            
            if (isValidPartition(num.substring(i), remSum - value)) {
                return true; 
            }
        }
        
        return false; 
    }

    public static int punishmentNumber(int num) {
        int totalSum = 0;
    
        for (int i = 1; i <= num; i++) {
            int square = i * i;
            if (isValidPartition(String.valueOf(square), i)) {
                totalSum += square;
            }
        }
    
        return totalSum;
    }    
    

    private static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode reversed = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return reversed;
    }

    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
      if(root == null || root == node1 || root == node2) {
        return root;
      }

      BinaryTreeNode left = lowestCommonAncestor(root.left, node1, node2);
      BinaryTreeNode right = lowestCommonAncestor(root.right, node1, node2);

      if(left == null) {
        return right;
      } else if (right == null) {
        return left;
      } else {
        return root;
      }
    }

   



    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

        int[] pair = twoSum(nums, target);

        for (int i : pair) {
            System.out.print(i+ " ");
        }

        System.out.println();

        // best time to buy and sell
        int[] prices = {7,1,5,3,6,4};
        int profit = maxProfit(prices);
        System.out.println(profit);

        int[] nums2 = {1,2,3,4};
        System.out.println(containsDuplicate(nums2));

        int[] ans = productExceptSelf( nums2);

        for (int i : ans) {
            System.out.print(i + " ");
        }

        System.out.println();

        System.out.println(maxSubarraySum(nums2));


        int[] num3 = {2,3,-2,4};
        System.out.println(maxSubarrayProd(num3));

        int[] nums4 = {3,4,5,1,2};
        System.out.println(findMinInRotatedArray(nums4));
        ListNode head = null;
        ListNode tail = null;

        for (int i : nums4) {
            ListNode newNode = new ListNode(i);
            if(head == null && tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = tail.next;
            }
        }

        ListNode reversed = reverseList(head);

        while (reversed != null) {
            System.out.print(reversed.data + " ");
            reversed = reversed.next;
        }

        System.out.println();

        System.out.println(punishmentNumber(10));
    }
}
