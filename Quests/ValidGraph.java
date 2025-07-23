import java.util.*;

public class ValidGraph {
    public boolean validTree(int n , int[][] edges){

        if(edges.length != n-1) return false;

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for(int i = 0; i<n; i++){
            graph.put(i , new HashSet<>());
        }

        for(int[] edge : edges){
            int u = edge[0]; 
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Set<Integer> visited = new HashSet<>();
        
        if(!dfs(0, -1, graph, visited)) return false;

        return visited.size() == n;
    }

    private boolean dfs(int node, int parent, Map<Integer, Set<Integer>> graph, Set<Integer> visited)
    {
        if(visited.contains(node)) return false;
        visited.add(node);
        for(int neighbor : graph.get(node)){
            if(neighbor == parent) continue;
            if(!dfs(neighbor, node, graph, visited)) return false;
        }

        return true;
    }
    public static void main(String[] args) {
        ValidGraph gvt = new ValidGraph();
        int[][] edges1 = {{0,1},{0,2},{0,3},{1,4}};
        System.out.println("Is valid tree? " + gvt.validTree(5, edges1)); // true

        int[][] edges2 = {{0,1},{1,2},{2,3},{1,3},{1,4}};
        System.out.println("Is valid tree? " + gvt.validTree(5, edges2)); // false
    }
}
