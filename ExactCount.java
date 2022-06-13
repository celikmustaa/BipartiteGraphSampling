import java.util.ArrayList;
import java.util.HashMap;

public class ExactCount {

    // Algorithm 1
    public static long ExactBFC(BipartiteGraph graph){
        long count = 0;

        ArrayList<Integer> A;

        if(graph.sumOfSquaresOfDegrees(graph.L) < graph.sumOfSquaresOfDegrees(graph.R)){
            A = new ArrayList<>(graph.R.keySet());
        }
        else{
            A = new ArrayList<>(graph.L.keySet());
        }

        for(int v: A){
            HashMap<Node, Integer> map = new HashMap<>();  // C

            for(Node u: graph.map.get(v).adjacency_list.keySet()){
                for(Node w: u.adjacency_list.keySet()){
                    if(w.degree < graph.map.get(v).degree || (w.degree == graph.map.get(v).degree && w.id < graph.map.get(v).id)){
                        map.put( w, ((map.containsKey(w)) ? map.get(w)+1 : 0) );
                    }
                }
            }

            for(Node w: map.keySet()){
                count += ((long) (map.get(w)) * (map.get(w) + 1) / 2);
            }
        }

        return count;
    }


    // Algorithm 2
    public static long vBFC(Node v){
        long count = 0;

        HashMap<Node, Integer> map = new HashMap<>();  // C

        for(Node u: v.adjacency_list.keySet()){
            for(Node w: u.adjacency_list.keySet()){
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

        for(Node w: edge.get(0).adjacency_list.keySet()){
            for(Node x: w.adjacency_list.keySet()){
                if(edge.get(1).adjacency_list.containsKey(x)){
                    count += 1;
                }
            }
        }

        return count;
    }


}     // class
