import java.util.ArrayList;
import java.util.HashMap;

public class ExactCount {

    // Algorithm 1
    public static long ExactBFC(BipartiteGraph graph){
        long count = 0;

        ArrayList<Node> A;

        if(graph.sumOfSquaresOfDegrees(graph.L) < graph.sumOfSquaresOfDegrees(graph.R)){
            A = new ArrayList<>(graph.R.values());
        }
        else{
            A = new ArrayList<>(graph.L.values());
        }

        for(Node v: A){
            HashMap<Node, Integer> map = new HashMap<>();  // C

            for(Node u: v.adjacency_list){
                for(Node w: u.adjacency_list){
                    if(w.degree < v.degree || (w.degree == v.degree && w.id < v.id)){
                        map.put( w, ((map.containsKey(w)) ? map.get(w)+1 : 0) );
                    }
                }
            }

            for(Node w: map.keySet()){
                count += ((long) (map.get(w)) * (map.get(w) + 1) / 2);  // do I need to take long statement outside of the paranthesis
            }
        }

        return count;
    }


    // Algorithm 2
    public static long vBFC(Node v){
        long count = 0;

        HashMap<Node, Integer> map = new HashMap<>();  // C

        for(Node u: v.adjacency_list){
            for(Node w: u.adjacency_list){
                if(w.id != v.id){
                    map.put( w, ((map.containsKey(w)) ? map.get(w)+1 : 0) );
                }
            }
        }

        for(Node w: map.keySet()){
            count += ((long) (map.get(w)) * (map.get(w) + 1) / 2);  // do I need to take long statement outside of the paranthesis
        }

        return count;
    }


    // Algorithm 3
    public static long eBFC(ArrayList<Node> edge){
        long count = 0;

        HashMap<Node, Integer> neighbours_of_v = new HashMap<>();
        for(Node x: edge.get(1).adjacency_list){
            neighbours_of_v.put(x, 1);
        }

        for(Node w: edge.get(0).adjacency_list){
            for(Node x: w.adjacency_list){
                if(neighbours_of_v.containsKey(x)){
                    count += 1;
                }
            }
        }

        return count;
    }



}     // class
