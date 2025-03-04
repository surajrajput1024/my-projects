package dsaproblems.questionoftheday;

public class CheckPowersOfThree {
    /**
     * Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.
     * An integer y is a power of three if there exists an integer x such that y == 3^x.
     * Example 1:
     * Input: n = 12
     * Output: true
     *
     * Example 2:
     * Input: n = 91
     * Output: true
     *
     * Approach:
     * 1. Keep dividing the number by 3 until the number becomes 1.
     * 2. If at any point the remainder is 2, then return false.
     *
     * @param n
     * @return
     */
    public static boolean checkPowersOfThree(int n) {
        while(n > 1) {
            if(n % 3 == 2) {
                return false;
            }
            n = n / 3;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkPowersOfThree(12));
        System.out.println(checkPowersOfThree(91));
        System.out.println(checkPowersOfThree(21));
    }
}
