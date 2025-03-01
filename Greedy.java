import java.util.Arrays;

public class Greedy {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int left = 0, right = 0;

        // loop on size array 
        while(left < s.length) {
            if(s[left] >= g[right]) {
                right++;
            }

            left++;
        }

        return right;
    }

    public static void main(String[] args) {
        
    }
}


