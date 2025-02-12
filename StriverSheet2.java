import java.util.*;
class Node<T> {
    T data;
    Node<T> next;

    Node() {
        this.data = null;
        this.next = null;
    }

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

class DoublyLLNode<T> {
    T data;
    DoublyLLNode<T> prev;
    DoublyLLNode<T> next;

    DoublyLLNode() {
        this.data = null;
        this.prev = null;
        this.next = null;
    }

    DoublyLLNode(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    } 
}

public class StriverSheet2 {

    // ==========================
    // ======== Arrays ==========
    // ==========================

    /**
     * Two Sum: https://leetcode.com/problems/two-sum/description/
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the array
     * - Time complexity: O(n^2)
     * - Space complexity: O(1)
     */
    private static int[] twoSumBruteForce(int[] nums, int target) {
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }

        return new int[] {-1, -1};
    }

    /**
     * Two Sum: https://leetcode.com/problems/two-sum/description/
     * 
     * Optimized approach:
     * - Use a hash map to store the number and its index
     * - Time complexity: O(n)
     * - Space complexity: O(n)
     */
    private static int[] twoSumOptimized(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int complement = target - nums[i];

            if(map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);
        }

        return new int[] {-1, -1};
    }

    /**
     * 3 Sum: https://leetcode.com/problems/3sum/description/
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the array
     * - Time complexity: O(n^3)
     * - Space complexity: O(1)
     */
    private static List<List<Integer>> threeSumBruteForce(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        return result;
    }

    /**
     * 3 Sum: https://leetcode.com/problems/3sum/description/
     * 
     * Better approach:
     * - Sort the array
     * - Use two pointers to find the sum
     * - Skip duplicates for all three pointers
     * - Time complexity: O(n^2)
     * - Space complexity: O(1)
     */
    private static List<List<Integer>> threeSumBetter(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0; i < n - 2; i++) {
            // Skip duplicates for i
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = n - 1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if(sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for left pointer
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicates for right pointer
                    while(left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                }
                else if(sum < 0) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }

        return result;
    }

    /*
    * Sort Colors: https://leetcode.com/problems/sort-colors/description/
    * 
    * Brute force approach:
    * - Use a for loop to iterate through the array
    * - Time complexity: O(n^2)
    * - Space complexity: O(1)
    */
    private static void sortColorsBruteForce(int[] nums) {
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    /*
    * Sort Colors: https://leetcode.com/problems/sort-colors/description/
    * 
    * Optimized approach:
    * - Use a hash map to store the number and its index
    * - Time complexity: O(n)
    * - Space complexity: O(1)
    */
    private static void sortColorsOptimized(int[] nums) {
        int n = nums.length;
        int low = 0, mid = 0, high = n - 1;

        while(mid <= high) {
            if(nums[mid] == 0) {
                swap(nums, low++, mid++);
            }
            else if(nums[mid] == 1) {
                mid++;
            }
            else {
                swap(nums, mid, high--);
            }
        }   
    }

    /*
     * Majority Elements [n/2]: https://leetcode.com/problems/majority-element/description/
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the array
     * - Time complexity: O(n^2)
     * - Space complexity: O(1)
     */
    private static int majorityElementBruteForce(int[] nums) {
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            int count = 0;

            for(int j = 0; j < n; j++) {
                if(nums[i] == nums[j]) {
                    count++;
                }
            }

            if(count > n / 2) {
                return nums[i];
            }
        }

        return -1;
    }

    /*
     * Majority Elements [n/2]: https://leetcode.com/problems/majority-element/description/
     * 
     * Optimized approach:
     * - Use a hash map to store the number and its count
     * - Time complexity: O(n)
     * - Space complexity: O(n)
     */
    private static int majorityElementOptimized(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > n / 2) {
                return entry.getKey();
            }
        }

        return -1;
    }

    /**
     * Maximum Subarray Sum: https://leetcode.com/problems/maximum-subarray/description/
     * (Kadane's Algorithm)
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the array
     * - Time complexity: O(n^2)
     * - Space complexity: O(1)
     */
    private static int maxSubArrayBruteForce(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            int currentSum = 0;

            for(int j = i; j < n; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }

    /**
     * Maximum Subarray Sum: https://leetcode.com/problems/maximum-subarray/description/
     * (Kadane's Algorithm)
     * 
     * Optimized approach:
     * - Time complexity: O(n)
     * - Space complexity: O(1)
     */
    private static int maxSubArrayOptimized(int[] nums) {
       int max = Integer.MIN_VALUE;
       int sum = 0;

       for(int num: nums) {
        sum +=  num;

        if(sum > max) {
            max = sum;
        }

        if(sum < 0){
            sum = 0;
        }
       }

       return max;
    }

    /**
     * Print maximum subarray sum:
     * Kadane's algo
     * 
     * Traverse the array and it won't make sense if we'll consiser negative in our sum
     * so If sum < 0 will reset it to 0. 
     */
    private static void printMaximumSubArrayOptimised(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int startIndex, endIndex;
        startIndex = endIndex = 0;


       for(int i = 0; i < n; i++) {
        sum += nums[i];

        if(sum == 0) {
            startIndex = 0;
        }

        if(sum > max) {
         max = sum;
         endIndex = i;
        }
        
        if(sum < 0) {
            sum = 0;
        }
       }

      System.out.println(Arrays.toString(Arrays.copyOfRange(nums, startIndex, endIndex)));
    }

    /**
     * Pair with max sum
     * Sliding window:
     * https://www.geeksforgeeks.org/problems/max-sum-in-sub-arrays0824/0
     * Maximum in subarrays minimums
     */
    private static int pairWithMaxSum(int[] nums){
        int n = nums.length;

        if(n < 2) {
            return -1;
        }

        int max = Integer.MIN_VALUE;

        // window size of 2
        for(int i = 1; i< n; i++){
            int sum = nums[i] + nums[i-1];
            if(sum > max) {
                max = sum;
            }
        }

        return max;
    }

    /**
     * Stocks problem
     * Best time to buy and sell stock
     */
    private static int bestTimeToBuyAndSell(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            int cost = prices[i] - min;
            maxProfit = Math.max(maxProfit, cost);
            min = Math.min(min, prices[i]);
        }

        return maxProfit;
    }

    /**
     * Rearrange array based on sign
     * https://leetcode.com/problems/rearrange-array-elements-by-sign/
     * Brute force
     * Time complexity: O(2n)
     * Space: O(n)
     */
    private static int[] rearrangeArrayBruteForce(int[] nums) {
     ArrayList<Integer> negative = new ArrayList<>();
     ArrayList<Integer> positive = new ArrayList<Integer>();

     for (int num: nums){
        if(num > 0) {
            positive.add(num);
        } else {
            negative.add(num);
        }
     }

     for (int i = 0; i < nums.length / 2; i++) {
        nums[2 * i] = positive.get(i);
        nums[2 *i + 1] = negative.get(i);
     }

     return nums;
    }

    /**
     * Rearrange array based on sign
     * https://leetcode.com/problems/rearrange-array-elements-by-sign/
     * Iteration can be one
     * Time: O(n)
     * Space: O(n)
     */
    private static int[] rearrangeArrayOptimal(int[] nums) {
        int[] ans = new int[nums.length];
        int posIndex = 0, negIndex = 1;   
    
        for (int num : nums) {
            if (num > 0) {
                ans[posIndex] = num;
                posIndex += 2;
            } else {
                ans[negIndex] = num;
                negIndex += 2;
            }
        }
        
        return ans;
    }
    
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int[] getFloorAndCeil(int x, int[] nums) {
       int[] ans = {-1, -1};
       int floor = x, ceil = x;

       for(int num: nums) {
        if(num <= x) {
            floor = Math.max(floor, num);
        }
        if(num >= x) {
        ceil = Math.min(ceil, num);
        }
       }

       ans[0] = floor;
       ans[1] = ceil;
       
       return ans;
    }


    // ==========================
    // ======== Binary Search ===
    // ==========================

    /**
     * Binary Search: https://leetcode.com/problems/binary-search/description/
     * 
     * Optimized approach:
     * - Use binary search to find the target
     * - Time complexity: O(log n)
     * - Space complexity: O(1)
     */
    private static int binarySearchBruteForce(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int mid = left + (right - left) / 2;

        while(left <= right) {
            mid = left + (right - left) / 2;

            if(nums[mid] == target) {
                return mid;
            }
            else if(nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Floor in a Sorted Array: https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1 
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the array
     * - Time complexity: O(n)
     * - Space complexity: O(1)
     */
    private static int floorInSortedArrayBruteForce(int[] nums, int target) {
        int n = nums.length;
        int largestFloor = -1;

        for(int i = 0; i < n; i++) {
            if(nums[i] <= target && nums[i] > largestFloor) {
                largestFloor = nums[i];
            }
        }

        return largestFloor;    
    }

    /**
     * Floor in a Sorted Array: https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1 
     * 
     * Optimized approach:
     * - Use binary search to find the floor
     * - Time complexity: O(log n)
     * - Space complexity: O(1)
     */
    private static int floorInSortedArrayOptimized(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums[0] > target) {
            return -1;
        }
        
        int n = nums.length;
        int left = 0, right = n - 1;
        int largestFloorIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;  
            }
            else if (nums[mid] < target) {
                largestFloorIndex = mid;  // Current element could be floor
                left = mid + 1;  // Look in right half for potentially larger floor
            }
            else {
                right = mid - 1;  // Look in left half
            }
        }

        return largestFloorIndex;
    }

    // ==========================
    // ======== Linked List =====
    // ==========================

    /**
     * Construct a linked list from an array
     * https://www.geeksforgeeks.org/problems/introduction-to-linked-list/1
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the array
     * - Time complexity: O(n)
     * - Space complexity: O(n)
     */
    private static Node<Integer> constructLinkedList(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }

        Node<Integer> head = new Node<>(nums[0]);
        Node<Integer> current = head;

        for(int i = 1; i < nums.length; i++) {
            current.next = new Node<>(nums[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Recursive to construct a linked list from an array
     * https://www.geeksforgeeks.org/problems/introduction-to-linked-list/1
     * 
     * Recursive approach:
     * - Use recursion to construct the linked list
     * - Time complexity: O(n)
     * - Space complexity: O(n)
     */
    private static Node<Integer> constructLLHelper(int[] nums, int index) {
        if(index == nums.length - 1) {
            return new Node<>(nums[index]);
        }

        Node<Integer> head = new Node<>(nums[index]);
        head.next = constructLLHelper(nums, index + 1);

        return head;
    }

    private static Node<Integer> constructLinkedListRecursive(int[] nums) {
        return constructLLHelper(nums, 0);
    }

    /**
     * Print a linked list
     * https://www.geeksforgeeks.org/problems/print-linked-list-elements/1
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the linked list
     * - Time complexity: O(n)
     * - Space complexity: O(1)
     */
    private static void printLinkedList(Node<Integer> head) {
        Node<Integer> current = head;

        while(current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    /**
     * Print a linked list
     * https://www.geeksforgeeks.org/problems/print-linked-list-elements/1
     * 
     * Recursive approach:
     * - Use recursion to print the linked list
     * - Time complexity: O(n)
     * - Space complexity: O(n)
     */
    private static void printLinkedListRecursive(Node<Integer> head) {
        if(head == null) {
            return;
        }

        System.out.print(head.data + " ");
        printLinkedListRecursive(head.next);
    }

    /**
     * Search a node in linked list
     * https://www.geeksforgeeks.org/problems/search-a-node-in-linked-list/1
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the linked list
     * 
     */
    private static boolean searchNodeInLinkedList(Node<Integer> head, int target) {
        Node<Integer> current = head;

        while(current != null) {
            if(current.data == target) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    /**
     * Search a node in linked list
     * https://www.geeksforgeeks.org/problems/search-a-node-in-linked-list/1
     * 
     * Recursive approach:
     * - Use recursion to search the node
     * - Time complexity: O(n)
     * - Space complexity: O(n)
     */
    private static boolean searchNodeInLinkedListRecursive(Node<Integer> head, int target) {
        if(head == null) {
            return false;
        }

        if(head.data == target) {
            return true;
        }

        return searchNodeInLinkedListRecursive(head.next, target);
    }

    /**
     * Count nodes of linked list
     * https://www.geeksforgeeks.org/problems/count-nodes-of-linked-list/1
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the linked list
     * - Time complexity: O(n)
     * - Space complexity: O(1)
     */
    private static int countNodesOfLinkedList(Node<Integer> head) {
        Node<Integer> current = head;
        int count = 0;

        while(current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

   
    /**
     * Insert a node at the beginning of the linked list
     * https://www.geeksforgeeks.org/problems/insert-node-at-the-beginning-of-the-linked-list/1
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the linked list
     * 
     */
    private static Node<Integer> insertNodeAtBeginning(Node<Integer> head, int target) {
        Node<Integer> newNode = new Node<>(target);
        newNode.next = head;

        return newNode;
    }

    /**
     * Insert a node at the end of the linked list
     * https://www.geeksforgeeks.org/problems/insert-node-at-the-end-of-the-linked-list/1
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the linked list
     * 
     */
    private static Node<Integer> insertNodeAtEnd(Node<Integer> head, int target) {
        Node<Integer> newNode = new Node<>(target);
        Node<Integer> current = head;

        while(current.next != null) {
            current = current.next;
        }

        current.next = newNode;

        return head;
    }

    /**
     * Insert a node at a specific position in the linked list
     * https://www.geeksforgeeks.org/problems/insert-node-at-a-specific-position-in-linked-list/1
     * 
     * Brute force approach:
     * - Use a for loop to iterate through the linked list
     * 
     */
    private static Node<Integer> insertNodeAtPosition(Node<Integer> head, int target, int position) {
        Node<Integer> newNode = new Node<Integer>(target);
        Node<Integer> current = head;

        for(int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;

        return head;
    }

    /**
     * Delete a node in a linked list
     * https://leetcode.com/problems/delete-node-in-a-linked-list/
     * 
     * 
     * - Use a for loop to iterate through the linked list
     * - Time commplexity: O(1)
     */
    private static void deleteNode(Node<Integer> node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }

    /**
     * Doubly linked list
     * https://www.geeksforgeeks.org/problems/introduction-to-doubly-linked-list/1
     * 
     */
    private static DoublyLLNode<Integer> constructDoublyLL(int[] nums) {
        if(nums.length == 1) {
            return new DoublyLLNode<>(nums[0]);
        }

        DoublyLLNode<Integer> header = new DoublyLLNode<>(nums[0]);
        DoublyLLNode<Integer> current = header;

        for(int i = 1; i< nums.length; i++) {
         DoublyLLNode<Integer> newNode = new DoublyLLNode<>(nums[i]);
         newNode.prev = current;
         current.next = newNode;
         current = current.next;
        }

        return header; 
    }

    /**
     * Insert a new Node in doubly LL
     * @param args
     */
    private static DoublyLLNode<Integer> insertNodeDoublyLL(DoublyLLNode<Integer> head, int p, int x) {
          DoublyLLNode<Integer> newNode = new DoublyLLNode<>(x);
          
          if(p == 0) {
            newNode.next = head;
            return newNode;
          }

          DoublyLLNode<Integer> current = head;
          int currentIndex = 0;

          while(currentIndex < p) {
            current = current.next;
            currentIndex++;
          }

          newNode.next = current.next;
          newNode.prev = current;
          current.next = newNode;

          return head;
    }

    private static DoublyLLNode<Integer> deleteNodeInDLL(DoublyLLNode<Integer> head, int position) {
        if(position == 1) {
        head = head.next;
        if(head != null) {
            head.prev = null;
        }
        return head;
        }

        DoublyLLNode<Integer> current = head;
        int currentIndex = 1;

        while (current != null && currentIndex < position) {
            current = current.next;
            currentIndex++;
        }

        if(current == null) {
            return head;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        }

        if (current.prev != null) {
            current.prev.next = current.next;
        }

        return head;
    }

    private static DoublyLLNode<Integer> reverseDLLHelper(DoublyLLNode<Integer> head, DoublyLLNode<Integer> currentNode) {
        if(currentNode.next == null){
            currentNode.prev = null;
            return currentNode;
        }

        currentNode.next = reverseDLLHelper(head, head.next);
        return currentNode;

    }


    private static DoublyLLNode<Integer> reverseDLL(DoublyLLNode<Integer> head) {
     return reverseDLLHelper(head, head);
    }


    // ===============================
    // ==== Dynamic programming ======
    // ===============================

    /**
     * Fibonacci series
     * @param n
     * @return
     */
    private static int fibRec(int n) {
        if(n <= 1) return n;

        return fibRec(n - 1) + fibRec( n -2);
    }

    private static int _fibHelper(int n, int[] dp) {
        if(n <= 1) {
            dp[n] = n;
            return dp[n];
        }

        if(dp[n] != -1) return dp[n];

        dp[n] = _fibHelper(n-1, dp) + _fibHelper(n-2, dp);
        return dp[n];
    }

    private static int fibMem(int n) {
        int dp[] = new int[n+1];
        Arrays.fill(dp, -1);

        return _fibHelper(n, dp);
    }

    private static int fibTab(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    /**
     * Stair case
     */
    private static int climbStairsRec(int n){
        if(n <= 2) return n;

        return climbStairsRec(n - 1) + climbStairsRec(n-2);
    }

    private static int _climbStairsHelper(int n, int[] dp) {
        if(n <= 2) {
            dp[n] = n;
            return dp[n];  
        }

        if(dp[n] != -1) return dp[n];

        dp[n] = _climbStairsHelper(n - 1, dp) +_climbStairsHelper(n - 2, dp);
        return dp[n];
    }

    private static int climbStairsMem(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        return _climbStairsHelper(n, dp);
    }

    private static int climbStairTab(int n) {
        if( n <= 2) {
            return n;
        }

        int[] dp = new int[n+1];

        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i< dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    // =================
    // ==== Graph ======
    // =================

    /**
     * TIme Complexity: O(V+ E
     * Space: O(V + E)
     * @param V
     * @param edges
     * @return
     */
    public List<List<Integer>> printGraph(int V, int edges[][]) {
        List<List<Integer>> adjList = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Populate adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u); // For an **undirected** graph
        }

        return adjList;
    }




    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 9;

        int[] result = twoSumBruteForce(nums, target);
        System.out.println("Two Sum Brute Force: " + Arrays.toString(result));

        result = twoSumOptimized(nums, target);
        System.out.println("Two Sum Optimized: " + Arrays.toString(result));

        int[] nums2 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Three Sum: " + threeSumBetter(nums2)); // [[-1,-1,2],[-1,0,1]]
        
        int[] nums3 = {0, 0, 0};
        System.out.println("Three Sum: " + threeSumBetter(nums3)); // [[0,0,0]]
        
        int[] nums4 = {0, 1, 1};
        System.out.println("Three Sum: " + threeSumBetter(nums4)); // []

        int[] nums5 = {1, 2, 4, 8, 10, 12, 14};
        System.out.println("Floor of 5: " + floorInSortedArrayOptimized(nums5, 5));  // Should return 1 (index of 4)
        System.out.println("Floor of 0: " + floorInSortedArrayOptimized(nums5, 0));  // Should return -1
        System.out.println("Floor of 13: " + floorInSortedArrayOptimized(nums5, 13)); // Should return 5 (index of 12)
        System.out.println("Floor of 15: " + floorInSortedArrayOptimized(nums5, 15)); // Should return 6 (index of 14)
    }
    
}
