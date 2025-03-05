package dsaproblems.greedy;

import java.util.ArrayList;
import java.util.Arrays;

class Item {
    int id, deadline, profit;

    Item(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }

}
public class JobSequencingProblem {
    public ArrayList<Integer> JobSequencing(int[] id, int[] deadline, int[] profit) {
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            items.add(new Item(id[i], deadline[i], profit[i]));
        }

        items.sort((a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (Item item : items) {
            maxDeadline = Math.max(maxDeadline, item.deadline);
        }

        int[] result = new int[maxDeadline + 1];
        Arrays.fill(result, -1);

        int maxProfit = 0;
        int jobCount = 0;

        for (Item item : items) {
            for (int i = item.deadline; i > 0; i--) {
                if (result[i] == -1) {
                    result[i] = item.id;
                    maxProfit += item.profit;
                    jobCount++;
                    break;
                }
            }
        }


        ArrayList<Integer> ans= new ArrayList<>();
        ans.add(jobCount);
        ans.add(maxProfit);

        return ans;
}

    public static void main(String[] args) {
        int[] id = {1, 2, 3, 4};
        int[] deadline = {4, 1, 1, 1};
        int[] profit = {20,10,40,30};
        JobSequencingProblem obj = new JobSequencingProblem();
        System.out.println(obj.JobSequencing(id, deadline, profit));
    }
}
