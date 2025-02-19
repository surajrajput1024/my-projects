import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Strings {
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
                second = currentNode + "b";
            } else {
                first = currentNode + "a";
                second = currentNode + "b";
            }

            queue.add(first);
            queue.add(second);
        }


        return k - 1 < ans.size() ? ans.get(k - 1) : "";
    }

    public static void main(String[] args) {
        System.out.println(kthHappyString(3, 9));
    }
}
