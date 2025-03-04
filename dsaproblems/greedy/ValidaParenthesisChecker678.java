package dsaproblems.greedy;

public class ValidaParenthesisChecker678 {
    private static boolean checkValidStringHelper(String s, int index, int count) {
        if(count < 0) return false;

        if(index == s.length()) {
            return count == 0;
        }


        if(s.charAt(index) == '(') {
            return  checkValidStringHelper(s, index + 1, count + 1);
        } else if (s.charAt(index) == ')') {
            return  checkValidStringHelper(s, index + 1, count - 1);
        } else {
            return  checkValidStringHelper(s, index + 1, count + 1) ||
                    checkValidStringHelper(s, index + 1, count - 1) ||
                    checkValidStringHelper(s, index + 1, count);
        }
    }

    public boolean checkValidString(String s) {
      return  checkValidStringHelper(s, 0, 0);
    }

    public boolean checkValidStringGreedy(String s) {
        int low = 0, high = 0;

        for(char c : s.toCharArray()) {
            low += c == '(' ? 1 : -1;
            high += c != ')' ? 1 : -1;

            if(high < 0) break;

            low = Math.max(low, 0);
        }

        return  low == 0;
    }

    public static void main(String[] args) {
        ValidaParenthesisChecker678 obj = new ValidaParenthesisChecker678();
        System.out.println(obj.checkValidStringGreedy("(("));
        System.out.println(obj.checkValidStringGreedy("(*)"));
        System.out.println(obj.checkValidStringGreedy("(*))"));
    }
}
