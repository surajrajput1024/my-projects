import java.util.Stack;

public class BackTrack {
    public static String smallestNumber(String pattern) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        int n = pattern.length();

        for (int i = 0; i <= n; i++) {
            stack.push(i+1);

            if(i == n || pattern.charAt(i) == 'I') {
                while (!stack.isEmpty()) {
                    ans.append(stack.pop());
                }
            }
        }

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(smallestNumber("IIIDIDDD"));
    }
}
