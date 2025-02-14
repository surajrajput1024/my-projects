public class DynamicProgramming {
    private static int fibRec(int n) {
       return (n <= 1) ? n : fibRec(n-1) + fibRec(n- 2);
    }

    private static int fibMem(int n, int[] dp) {
        if(n <= 1) {
            dp[n] = n;
            return n;
        }

        if(dp[n] != -1) return dp[n];

        dp[n] = fibMem(n - 1, dp) + fibMem(n - 2, dp);

        return dp[n];
    }

    private static int fibMem(int n) {
        int[] dp = new int[n+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }

        return fibMem(n, dp);
    }

    private static int fibTab(int n){
        if(n <= 1) {
            return n;
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    private static int fibOptimal(int n){
        if(n <= 1) {
            return n;
        }

        int dpiMinus1 = 1;
        int dpiMinus2 = 0;
        int dpi = 0;


        for (int i = 2; i <= n; i++){
        dpi = dpiMinus1 + dpiMinus2;
            dpiMinus2 = dpiMinus1;
            dpiMinus1 = dpi;
        }

        return dpi;
    }

    private static int climbStairsRec(int n) {
        return n <= 2 ? n : climbStairsRec(n-1) + climbStairsRec(n-2);
    }

    private static int climbStairsMemHelper(int n, int[] dp) {
        if(n <= 2) {
            dp[n] = n;
            return n;
        }

        if(dp[n] != -1) return dp[n];

        dp[n] = climbStairsMemHelper(n - 1, dp) + climbStairsMemHelper(n - 2, dp);
        return dp[n];
    }

    private static int climbStairsMem(int n) {
        int[] dp = new int[n + 1];

        for(int i = 0; i< dp.length; i++) {
            dp[i] = -1;
        }

        return climbStairsMemHelper(n, dp);
    }

    private static int climbStairsTabular(int n) {
        if(n <= 2) return n;


        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    private static int climbStairsOptimal(int n) {
        if(n <= 2) return n;


        int dpiMinus1 = 2;
        int dpiMinus2 = 1;
        int dpi = 0;

        for (int i = 3; i <= n; i++){
         dpi = dpiMinus1 + dpiMinus2;
            dpiMinus2 = dpiMinus1;
            dpiMinus1 = dpi;
        }

        return dpi;
    }

    private static int frogJumpMinCostHelper(int[] nums, int index) {
        if(index == 0) return 0;

        int left = frogJumpMinCostHelper(nums, index - 1) + nums[index] - nums[index-1];
        int right = Integer.MAX_VALUE;
        if (index > 1) right = frogJumpMinCostHelper(nums, index - 2) + nums[index] - nums[index - 2];

        return Math.min(left, right);
    }

    private static int frogJumpMinCost(int[] nums) {
       return frogJumpMinCostHelper(nums, nums.length - 1);
    }

    private static int frogJumpMinCostMemHelper(int[] nums, int[] dp, int index) {
        if(index == 0) return 0;

        if(dp[index] != -1) return dp[index];

        int left = frogJumpMinCostHelper(nums, index - 1) + nums[index] - nums[index-1];
        int right = Integer.MAX_VALUE;
        if (index > 1) right = frogJumpMinCostHelper(nums, index - 2) + nums[index] - nums[index - 2];

        dp[index] = Math.min(left, right);
        return dp[index];
    }

    private static int frogJumpMinCostMem(int[] nums) {
        int[] dp = new int[nums.length + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }

        return frogJumpMinCostMemHelper(nums, dp, nums.length - 1);
    }

    private static int frogJumpMinCostTab(int[] nums, int index) {
        if(index == 0) return 0;

        int[] dp = new int[index+1];
        dp[0] = 0;

        for(int i = 1; i < dp.length; i++) {
            int left = dp[i-1] + Math.abs(nums[i] - nums[i - 1]);
            int right = Integer.MAX_VALUE;
            if(i > 1) {
                right = dp[i-2] + Math.abs(nums[i] - nums[i - 2]);
            }

            dp[i] = Math.min(left, right);
        }

        return dp[index];
    }

    private static int frogJumpWithKDistanceRecHelper(int[] nums, int k, int index){
        if(index == 0) return 0;
        int ans = Integer.MAX_VALUE;

        for(int j = 1; j <= k; j++){
            if  (index >= j ) {
                int withJump = frogJumpWithKDistanceRecHelper(nums, k, index - j) + Math.abs(nums[index] - nums[index - j]);
                ans = Math.min(ans, withJump);
            }
        }

        return ans;
    }
    
    private static int frogJumpWithKDistanceRec(int[] nums, int k) {
        return frogJumpWithKDistanceRecHelper(nums, k, nums.length - 1);
    }

    private static int frogJumpWithKDistanceMem(int[] nums, int k, int index, int[] dp) {
        if(index == 0) return 0;

        if(dp[index] != -1) return dp[index];

        int ans = Integer.MAX_VALUE;

        for(int j = 1; j <= k; j++){
            if  (index >= j ) {
                int withJump = frogJumpWithKDistanceMem(nums, k, index - j, dp) + Math.abs(nums[index] - nums[index - j]);
                ans = Math.min(ans, withJump);
            }
        }

        dp[index] = ans;
        return ans;
    }

    public static int minimizeCostTab(int k, int nums[]) {
        int n = nums.length;
        int[] dp = new int[n];
    
        dp[0] = 0;
    
        for (int i = 1; i < n; i++) {
            int ans = Integer.MAX_VALUE;
    
            for (int j = 1; j <= k; j++) {
                if (i >= j) {
                    int withJump = dp[i - j] + Math.abs(nums[i] - nums[i - j]);
                    ans = Math.min(ans, withJump);
                }
            }
    
            dp[i] = ans;
        }
    
        return dp[n - 1]; 
    }
 
    public static void main(String[] args) {
        System.out.println(fibRec(10));
        System.out.println(fibMem(10));
        System.out.println(fibTab(10));
        System.out.println(fibOptimal(10));
        System.out.println(climbStairsRec(5));
        System.out.println(climbStairsMem(5));
        System.out.println(climbStairsTabular(5));
        System.out.println(climbStairsOptimal(5));

        int[] nums = {10,2,30,43,56};
        System.out.println(frogJumpMinCost(nums));
        System.out.println(frogJumpMinCostMem(nums));
        System.err.println(frogJumpMinCostTab(nums, nums.length - 1));

        System.out.println(frogJumpWithKDistanceRec(nums, 1));

        int[] dp = new int[nums.length];
        
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }

        System.out.println(frogJumpWithKDistanceMem(nums, 2, nums.length - 1,dp ));
    }
    
}