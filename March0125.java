package abc;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    int data;
    Node[] children;

    Node(int data) {
        this.data = data;
    }

    Node(int data, Node[] childNodes) {
        this.data = data;
        this.children = childNodes;
    }
}

class Pair {
    Node node;
    int length;
    
    Pair(Node node, int length) {
        this.node = node;
        this.length = length;
    }
}

public class March0125 {
    private int maxPathLength;

    public int longestVerticalPath(Node root) {
        if (root == null) return 0;
        dfs(root);
        return maxPathLength;
    }

    private  int dfs(Node node) {
        if (node == null) return 0;

        int longestChildPath = 0;

        for (Node child : node.children) {
            int childPath = dfs(child);
            longestChildPath = Math.max(longestChildPath, childPath);
        }

        int currentLength = (node.data == 1) ? longestChildPath + 1 : 0;
        maxPathLength = Math.max(maxPathLength, currentLength);

        return currentLength;
    }

private static int  longestVerticalPathOf1Rec(Node root) {
 if(root == null) return 0;

 if(root.children == null) {
    return root.data == 1 ? 1 : 0;
 }

 int ans = 0;

 for (int i = 0; i < root.children.length; i++) {
    int currentLength = longestVerticalPathOf1Rec(root.children[i]);
    if(currentLength > ans) {
        ans = currentLength;
    }
 }

 return  root.data == 1 ? ans + 1 : ans;
}

public static int longestVerticalPathOf1BFS(Node root) {
    if (root == null) return 0;

    Queue<Pair> queue = new LinkedList<>();
    queue.offer(new Pair(root, root.data == 1 ? 1 : 0));

    int maxLength = 0;

    while (!queue.isEmpty()) {
        Pair current = queue.poll();
        Node currentNode = current.node;
        int currentLength = current.length;

        maxLength = Math.max(maxLength, currentLength);

        if (currentNode.children != null) {
            for (Node child : currentNode.children) {
                if (child != null) {
                    queue.offer(new Pair(child, child.data == 1 ? currentLength + 1 : 0));
                }
            }
        }
    }

    return maxLength;
}

public static int longestVerticalPathOf1DFS(Node root) {
    if (root == null) return 0;

    Stack<Pair> stack = new Stack<>();
    stack.push(new Pair(root, root.data == 1 ? 1 : 0));

    int maxLength = 0;

    while (!stack.isEmpty()) {
        Pair current = stack.pop();
        Node currentNode = current.node;
        int currentLength = current.length;

        maxLength = Math.max(maxLength, currentLength);

        if (currentNode.children != null) {
            for (Node child : currentNode.children) {
                if (child != null) {
                    stack.push(new Pair(child, child.data == 1 ? currentLength + 1 : 0));
                }
            }
        }
    }

    return maxLength;
}

    
 public static void main(String[] args) {
    Node node1 = new Node(0);
    Node node2 = new Node(1);
    Node node3 = new Node(0);

    Node[] children1  = {node1, node2, node3};
    Node node4 = new Node(1, children1);
    Node node5 = new Node(0);

    Node[] children2 = {node4, node5};
    Node node6 = new Node(1, children2);
    Node node7 = new Node(0);

    Node[] children3 = {node6, node7};
    Node root = new Node(0, children3);

    System.out.println("Longest vertical path containing 1 is: " + longestVerticalPathOf1BFS(root));
 }    
}
