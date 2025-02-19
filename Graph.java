import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    public static long countUndirectedGraphs(int n) {
        int pow = (n * (n - 1)) / 2; 
        return (long) Math.pow(2, pow); 
    }

    public List<List<Integer>> printGraph(int V, int edges[][]) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }

    public int countComponents(int n, int[][] edges) {
     
    }

    public ArrayList<Integer> bfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
       Queue<Integer> queue = new LinkedList<>();
       boolean[] visited = new boolean[v];
       ArrayList<Integer> ans = new ArrayList<>();

       // Add root
       queue.add(0);
       visited[0] = true;

       while (!queue.isEmpty()) {
          int node = queue.poll();
          ans.add(node);

          for(Integer i: adj.get(node)) {
            if(visited[i] == false) {
                queue.add(i);
                visited[i] = true;
            }
          }
       }

       return ans;
    }
    

    public static void main(String[] args) {
        System.out.println(countUndirectedGraphs(9));
    }
    
}
