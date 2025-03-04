package dsaproblems.greedy;

public class JumpGame55 {
    public boolean canJump(int[] nums) {
     int n = nums.length;
     int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            if(i > maxIndex) return false;

            maxIndex = Math.max(maxIndex, i + nums[i]);
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,1,0,4};
        JumpGame55 obj = new JumpGame55();
        System.out.println(obj.canJump(nums));

    }
}
