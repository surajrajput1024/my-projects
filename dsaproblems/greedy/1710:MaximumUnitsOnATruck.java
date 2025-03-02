import java.util.List;

import algorithms.ArrayList;


public class 1710:MaximumUnitsOnATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> Integer.compare(b[1], a[1]));

        int maxUnits = 0;
        for (int[] box : boxTypes) {
            int boxesToTake = Math.min(truckSize, box[0]);
            maxUnits += boxesToTake * box[1];
            truckSize -= boxesToTake;
            if (truckSize == 0) break; 
        }

        return maxUnits;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] boxTypes = {{1,3}, {2,2}, {3,1}};
        int truckSize = 4;
        System.out.println(sol.maximumUnits(boxTypes, truckSize)); 
    }
}
