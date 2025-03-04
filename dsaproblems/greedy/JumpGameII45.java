package dsaproblems.greedy;

public class JumpGameII45 {
    public int jump(int[] nums) {
      int n = nums.length;
      int low = 0, high = 0, jumps = 0;

      while(high < n - 1) {
          int farthest = 0;

          for(int i = low; i <= high; i++) {
              farthest = Math.max(farthest, i + nums[i]);
          }

          low = high + 1;
          high = farthest;
          jumps++;
      }

      return jumps;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        JumpGameII45 obj = new JumpGameII45();
        System.out.println(obj.jump(nums));
    }
}
