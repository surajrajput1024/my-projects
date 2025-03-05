package dsaproblems.greedy;

import java.util.Arrays;

public class MinimumPlatforms {
    static int findPlatform(int arr[], int dep[]) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0, j =0, count = 0, maxCount = 0;

        while (i < arr.length) {
            if(arr[i] <= dep[j]) {
                count++;
                i++;
            } else {
                count--;
                j++;
            }

            maxCount = Math.max(maxCount, count);
        }

        return  maxCount;
    }

    public static void main(String[] args) {
        int arr[] = {900, 940, 950, 1100, 1500, 1800};
        int dep[] = {910, 1200, 1120, 1130, 1900, 2000};

        System.out.println(findPlatform(arr, dep));
    }
}
