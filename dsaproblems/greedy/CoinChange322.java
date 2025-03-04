package dsaproblems.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange322 {
    // greedy not applicable in cases like coins = [186,419,83,408] amount = 6249
    // so need to use dp instead of this.
    public int coinChange(int[] coins, int amount) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(coins);
        int n = coins.length;

        System.out.println(Arrays.toString(coins));

        for(int i = n - 1; i >= 0; i--) {
            while(coins[i] <= amount) {
                amount -= coins[i];
                ans.add(coins[i]);
            }
        }

        return amount == 0 ? ans.size() : -1;
    }


    public static void main(String[] args) {
        CoinChange322 obj = new CoinChange322();
        int[] coins = {1,2,5,10,20,50,100,500,1000};
        int amount = 49;
        System.out.println("\nMinimum Coins Needed: " + obj.coinChange(coins, amount));
    }
}
