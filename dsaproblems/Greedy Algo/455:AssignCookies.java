package dsaproblems.Greedy Algo;

public class 455:AssignCookies {
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
}
