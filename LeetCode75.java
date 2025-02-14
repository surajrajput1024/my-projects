import java.util.*;

public class LeetCode75 {
    public static String mergeAlternately(String word1, String word2) {
        int left = 0, right = 0;
        String ans = "";

        while(left < word1.length() && right < word2.length()) {
            ans += word1.charAt(left);
            ans += word2.charAt(right);
            left++;
            right++;
        }

        while(left < word1.length()) {
            ans += word1.charAt(left);
            left++;
        }

        while(right < word2.length()) {
            ans += word2.charAt(right);
            right++;
        }

        return ans;
    }

    public static String gcdOfStrings(String str1, String str2) {
        if(str2.length() > str1.length()) {
            return gcdOfStrings(str2, str1);
        }

        if(str1.equals(str2)) {
            return str1;
        }

        if(str1.startsWith(str2)) {
            return gcdOfStrings(str1.substring(str2.length()), str2);
        }

        return "";
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> ans = new ArrayList<Boolean>();
        int max = Integer.MIN_VALUE;
        
        for (int candy : candies) {
            max = Math.max(max, candy);
        }

        for(int num: candies) {
            ans.add(num + extraCandies >= max);
        }
        
        return ans;
    }



    public static void main(String[] args) {
        String ans = mergeAlternately("abc", "pqrs");
        System.out.println(ans);

        System.out.println(gcdOfStrings("abab", "ababab"));
    }
}
