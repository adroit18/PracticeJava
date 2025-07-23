import java.util.*;

class AlienDictionary
{
    public static void main(String[] args) {
        AlienDictionary solver = new AlienDictionary();

        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};

        System.out.println("Alien Language Order: " + solver.AlienDict(words));
    }

    public String AlienDict(String[] words){
        
        HashMap<Character, Set<Character>> graph = new HashMap<>();
        HashMap<Character, Integer> inDegree = new HashMap<>();

        for(String word : words){
            for(char c :  word.toCharArray()){
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        for(int i = 0; i<words.length-1; i+=1){
            String currWord = words[i];
            String nextWord = words[i+1];

            if(currWord.startsWith(nextWord) && currWord.length() > nextWord.length()){
                return "";
            }

            for(int j = 0; j<Math.min(currWord.length(), nextWord.length()); j++){
                char c1 = currWord.charAt(j);
                char c2 = nextWord.charAt(j);

                if(c1 != c2){
                    if(!graph.get(c1).contains(c2))
                    {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();

        for(char c : inDegree.keySet()){
            if(inDegree.get(c) == 0){
                queue.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();

        while(!queue.isEmpty()){
            char current = queue.poll();
            result.append(current);
            
            for(char neighbor : graph.get(current)){
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (result.length() < inDegree.size()) {
            return "";
        }

        return result.toString();

    }
}