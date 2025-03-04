package tree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;


// ===========================
// ======== Tree =============
// ===========================

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class FindElements {
    // Store recovered values for O(1) lookup
    private Set<Integer> values = new HashSet<>(); 

    public FindElements(TreeNode root) {
        recoverTree(root, 0);
    }

    private void recoverTree(TreeNode node, int val) {
        if (node == null) return;
        
        node.val = val;  
        values.add(val); 
        
        recoverTree(node.left, 2 * val + 1);  
        recoverTree(node.right, 2 * val + 2);
    }

    public boolean find(int target) {
        return values.contains(target);
    }
}

class Tree<T>{
    T data;
    Tree<T> left;
    Tree<T> right;

    public Tree(T data) {
     this.data = data;
    }


    public void createTree(Tree<Integer> root, ArrayList<Integer> nodes ){
        root.left = new Tree<Integer>(nodes.get(1));
        root.right = new Tree<Integer>(nodes.get(2));
        
        root.left.left = new Tree<>(nodes.get(3));
        root.left.right = new Tree<>(nodes.get(4));
        root.right.left = new Tree<>(nodes.get(5));
        root.right.right = new Tree<>(nodes.get(6));
    }

    private static List<Integer> inOrderTraversal(Tree<Integer> root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        ans.addAll(inOrderTraversal(root.left));
        ans.add(root.data);
        ans.addAll(inOrderTraversal(root.right));

        return ans;
    }

    private static List<Integer> inOrderTraversalI(Tree<Integer> root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Stack<Tree<Integer>> stack = new Stack<>();
        Tree<Integer> currentNode = root;

        while (true) {
            if(currentNode != null) {
                stack.push( currentNode);
                currentNode = currentNode.left;
            } else {
                if(stack.isEmpty()) {
                    break;
                }

                currentNode = stack.pop();
                ans.add(currentNode.data);
                currentNode = currentNode.right;
            }
        }

        return ans;
    }

    private static List<Integer> preOrderTraversal(Tree<Integer> root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        ans.add(root.data);
        ans.addAll(preOrderTraversal(root.left));
        ans.addAll(preOrderTraversal(root.right));

        return ans;
    }

    private static List<Integer> preOrderTraversalI(Tree<Integer> root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Stack<Tree<Integer>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Tree<Integer> currentNode = stack.pop();

            if(currentNode.right != null) stack.add(currentNode.right);
            if(currentNode.left != null) stack.add(currentNode.left);

            ans.add(currentNode.data);
        }

        return ans;
    }

    private static List<Integer> postOrderTraversal(Tree<Integer> root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        ans.addAll(postOrderTraversal(root.left));
        ans.addAll(postOrderTraversal(root.right));
        ans.add(root.data);

        return ans;
    }

    private static List<Integer> postOrderTraversalI(Tree<Integer> root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Stack<Tree<Integer>> stack1 = new Stack<>();
        Stack<Tree<Integer>> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.add(root);


            if(root.left != null) stack1.add(root.left);
            if(root.right != null) stack1.add(root.right);
        }

        while (!stack2.isEmpty()) {
            ans.add(stack2.pop().data);
        }

        return ans;
    }

    private static List<Integer> postOrderTraversalSingleStack(Tree<Integer> root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
    
        Stack<Tree<Integer>> stack = new Stack<>();
        Tree<Integer> curr = root, lastVisited = null;
    
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;  // Move left
            } else {
                Tree<Integer> peekNode = stack.peek();
    
                // If right child exists and hasn't been visited yet, move to right
                if (peekNode.right != null && peekNode.right != lastVisited) {
                    curr = peekNode.right;
                } else {
                    ans.add(peekNode.data);
                    lastVisited = stack.pop();
                }
            }
        }
        return ans;
    }
    
    private static List<List<Integer>> levelOrderTraversal(Tree<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<Tree<Integer>> queue = new LinkedList<>();
        queue.add(root);

        

        while (!queue.isEmpty()) {
          int size = queue.size();
          List<Integer> currentLevelAns = new LinkedList<>();

          for (int i = 0; i < size; i++) {
            if(queue.peek().left != null) queue.offer(queue.peek().left);
            if(queue.peek().right != null) queue.offer(queue.peek().right);

            currentLevelAns.add(queue.poll().data);
          }

          ans.add(currentLevelAns);
        }

        return ans;
    }

    private static class Pair {
        Tree<Integer> first;
        int second;
        
        Pair(Tree<Integer> first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static List<List<Integer>> getTreeTraversalInSingleScan(Tree<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        List<Integer> preorder = new ArrayList<>(), inOrder = new ArrayList<>(), postOrder = new ArrayList<>();
        
        while (!stack.isEmpty()) {
            Pair num = stack.pop();

            if(num.first == null) continue;

            // preorder
            if(num.second == 1) {
                preorder.add(num.first.data);
                stack.push(new Pair(num.first, 2));
                if(num.first.left != null) stack.push(new Pair(num.first.left, 1));
            } else if (num.second == 2) {
                // inorder
                inOrder.add(num.first.data);
                stack.push(new Pair(num.first, 3));
                if (num.first.right != null) stack.push(new Pair(num.first.right, 1));
            } else {
                //postorder
                postOrder.add(num.first.data);
            }
        }

        ans.add(preorder);
        ans.add(inOrder);
        ans.add(postOrder);

        return ans;
    }

    public static List<List<Integer>> getTreeTraversal(Tree<Integer> root, String type) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;

        switch (type) {
            case "LEVEL_ORDER_TRAVERSAL":
                return levelOrderTraversal(root);        
            default:
                break;
        }

        ans.add(inOrderTraversal(root));
        ans.add(preOrderTraversal(root));
        ans.add(postOrderTraversal(root));
        ans.add(preOrderTraversalI(root));
        ans.add(inOrderTraversalI(root));
        ans.add(postOrderTraversalI(root));
        ans.add(postOrderTraversalSingleStack(root));

        return ans;
    }

    public int maxDepth(Tree<Integer> root) {
        if (root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1; 
    }

    private static int height(Tree<Integer> root) {
        if (root == null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public boolean isBalanced(Tree<Integer> root) {
        return height(root) != -1;
    }

    public static int diameter(Tree<Integer> root, int[] diameter) {
        if(root == null) return 0;

        int leftHeight = diameter(root.left, diameter);
        int rightHight = diameter(root.right, diameter);

        diameter[0] = Math.max(diameter[0], rightHight + leftHeight);

        return 1+ Math.max(leftHeight, rightHight);
    }

    private static int maxPath(Tree<Integer> root, int[] path) {
        if(root == null) {
            return 0;
        }

        int left = Math.max(0, maxPath(root.left, path));
        int right = Math.max(0, maxPath(root.right, path));

        path[0] = Math.max(path[0], left + right + root.data);

        return root.data + Math.max(left, right);
    }

    public int maxPathSum(Tree<Integer> root) {
        int[] path = new int[1];
        path[0] = Integer.MIN_VALUE;
        maxPath(root, path);
        return path[0];
    }

    public boolean isSameTree(Tree<Integer> p, Tree<Integer> q) {
        if(p == null && q == null) return true;

        if(p == null || q == null) return false;

        if(p.data == q.data) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

        return false;
    }

    public List<List<Integer>> zigzagLevelOrder(Tree<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<Tree<Integer>> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelTraversal = new ArrayList<>();

            for (int i = 0; i < size; i++) {
            if(queue.peek().left != null) queue.offer(queue.peek().left);
            if(queue.peek().right != null) queue.offer(queue.peek().right);

            levelTraversal.add(queue.poll().data);
            }

            if(level % 2 == 0) {
                ans.add(levelTraversal.reversed());
            } else {
                ans.add(levelTraversal);
            }
            level++;
        }

        return ans;
    }
    

    private static boolean isLeaf(Tree<Integer> root) {
        return root.left == null && root.right == null;
    }

    private static void addLeftBoundary(Tree<Integer> root, List<Integer> res) {
        Tree<Integer> current = root.left;

        while (current != null) {
            if(!isLeaf(current)) {
                res.add(current.data);
            }

            if(current.left != null) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    private static void addLeaves(Tree<Integer> root, List<Integer> res) {
        if (root == null) return;

        if(isLeaf(root)) {
         res.add(root.data);
        }
 
        if(root.left != null) {
         addLeaves(root.left, res);
        }
 
        if(root.right != null) {
         addLeaves(root.right, res);
        }
     }

    private static void addRightBoundary(Tree<Integer> root, List<Integer> res) {
        Tree<Integer> current = root.right;

        ArrayList<Integer> temp = new ArrayList<>();

        while (current != null) {
            if(!isLeaf(current)) {
              temp.add(current.data);
            }

            if(current.right != null) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        Collections.reverse(temp);
        res.addAll(temp);
    }



    public ArrayList<Integer> boundaryTraversal(Tree<Integer> root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        if(!isLeaf(root)) ans.add(root.data);

        addLeftBoundary(root, ans);
        addLeaves(root, ans);
        addRightBoundary(root, ans);

        return ans;
    }

   private class Tuple {
        int row;
        int col;
        TreeNode node;
    
        public Tuple(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(root, 0, 0));

        while (!queue.isEmpty()) {
            Tuple currentTuple = queue.poll();
            int col = currentTuple.col;
            int row = currentTuple.row;
            TreeNode node = currentTuple.node;

            if(!map.containsKey(row)) {
                map.put(row, new TreeMap<>());
            }


        }

        return new ArrayList<>();
    }
}


class Trie {
    
}

public class Main {
    public static void main(String[] args) {
        
    }
}
