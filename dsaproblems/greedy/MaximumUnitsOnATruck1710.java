package dsaproblems.greedy;
import java.util.Arrays;


public class MaximumUnitsOnATruck1710 {
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
      int[][] boxTypes = {{1,3},{2,2},{3,1}};
      int truckSize = 4;
      MaximumUnitsOnATruck1710 maximumUnitsOnATruck1710 = new MaximumUnitsOnATruck1710();

      System.out.println(maximumUnitsOnATruck1710.maximumUnits(boxTypes, truckSize));

    }
}
