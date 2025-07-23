import java.util.*;

class TopoSortDfs
{
    public static void main(String args[]){
        TopoSortDfs sorter = new TopoSortDfs();

        // Build graph: A → C, B → C, C → D
        Map<Character, List<Character>> graph = new HashMap<>();
        graph.put('A', Arrays.asList('C'));
        graph.put('B', Arrays.asList('C'));
        graph.put('C', Arrays.asList('D'));

        try {
            List<Character> order = sorter.topoSortDFS(graph);
            System.out.println("Topological Sort Order: " + order);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    
    }

    public void dfs(char node, Map<Character, List<Character>> graph, 
                    Set<Character> visited, Set<Character> onPath, Stack<Character> stack)
    {
        
        if(onPath.contains(node)) 
            return;

        if(visited.contains(node))
            return;

        visited.add(node);
        onPath.add(node);
        
        for(char neighbor : graph.getOrDefault(node, new ArrayList<>())){
            dfs(neighbor, graph, visited, onPath, stack);
        }

        onPath.remove(node);
        stack.push(node);
    }

    public List<Character> topoSortDFS(Map<Character, List<Character>> graph) {
        Set<Character> visited = new HashSet<>();
        Set<Character> onPath = new HashSet<>(); // To detect cycles
        Stack<Character> stack = new Stack<>();

        for (char node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, graph, visited, onPath, stack);
            }
        }

        // Reverse the stack to get the correct topological order
        List<Character> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }
}