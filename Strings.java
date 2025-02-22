import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Arrays;

public class Strings {
    private String res;

    public static String kthHappyString(int n, int k) {
        Queue<String> queue = new LinkedList<>();

        queue.add("a");
        queue.add("b");
        queue.add("c");
        ArrayList<String> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            String currentNode = queue.poll();

            if(currentNode.length() == n) {
                ans.add(currentNode);
                continue;
            }

            String first = currentNode;
            String second = currentNode;

            if(currentNode.endsWith("a")) {
                first = currentNode + "b";
                second = currentNode + "c";
            } else if (currentNode.endsWith("b")) {
                first = currentNode + "a";
                second = currentNode + "c";
            } else {
                first = currentNode + "a";
                second = currentNode + "b";
            }

            queue.add(first);
            queue.add(second);
        }


        return k - 1 < ans.size() ? ans.get(k - 1) : "";
    }


    private boolean buildNumber(Set<String> numbers, String curr, int n) {
        if (curr.length() == n) {
            if (!numbers.contains(curr)) {
                res = curr;
                return true;
            }
            return false;
        }

        // Try '0'
        if (buildNumber(numbers, curr + '0', n)) {
            return true;
        }

        // Try '1'
        if (buildNumber(numbers, curr + '1', n)) {
            return true;
        }

        return false;
    }

    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        Set<String> numbers = new HashSet<>(Arrays.asList(nums));
        buildNumber(numbers, "", n);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(kthHappyString(7, 8));
    }
}
