import java.util.*;

class BinaryTreeNode {
    int data;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode (int data) {
        this.data = data;
        left = right = null;
    }
}

public class Tree {
    
    public static List<Integer> preorderTraversal(BinaryTreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();

        if(root == null) {
            return ans;
        } 

        ans.add(root.data);
        List<Integer> left =  preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);

        for (Integer num : left) {
            ans.add(num);
        }

        for (Integer num : right) {
            ans.add(num);
        }

        return ans;

    }

    public static ArrayList<Integer> serialize(BinaryTreeNode root) {
        if (root == null) return new ArrayList<>();
        
        ArrayList<Integer> ans = new ArrayList<>();
        ans.addAll(serialize(root.left));  
        ans.add(root.data);               
        ans.addAll(serialize(root.right));

        return ans;
    }

    public static BinaryTreeNode deSerialize(ArrayList<Integer> nums) {
        return buildTree(nums, 0, nums.size() - 1);
    }

    private static BinaryTreeNode buildTree(ArrayList<Integer> nums, int left, int right) {
        if(left > right) return null;

        int mid = left + (right - left) / 2;
        BinaryTreeNode root = new BinaryTreeNode(nums.get(mid));

        root.left = buildTree(nums, left, mid - 1);
        root.right = buildTree(nums, mid + 1, right);

        return root;
    }
}
