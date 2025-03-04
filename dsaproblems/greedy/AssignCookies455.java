package dsaproblems.greedy;

import java.util.Arrays;

public class AssignCookies455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int left = 0, right = 0;

        // loop on size array 
        while(left < s.length && right < g.length) {
            if(s[left] >= g[right]) {
                right++;
            }

            left++;
        }

        return right;
    }

    public static void main(String[] args){
        int[] g = {1,2,3};
        int[] s = {1,1};

        AssignCookies455 obj = new AssignCookies455();
        System.out.println(obj.findContentChildren(g, s));
    }
}

