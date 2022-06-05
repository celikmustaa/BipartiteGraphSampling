import java.util.*;

public class BipartiteGraph {
    public int N;
    public int M;
    public HashMap<Integer, Node> L;    // node_id: node
    public HashMap<Integer, Node> R;    // node_id: node
    public HashMap<Integer, Node> map; // all nodes
    public ArrayList<ArrayList<Node>> edge_list; // [[node1, node2],[n3, n4] ...]

    public BipartiteGraph() {
        this.L = new HashMap<>();
        this.R = new HashMap<>();
        this.map = new HashMap<>();
        this.edge_list = new ArrayList<>();
    }

    public long sumOfSquaresOfDegrees(HashMap<Integer, Node> V){
        long count = 0;
        for (Node node : V.values()) {
            count += node.degree;
        }
        return count;
    }

    public void addLeft(int node_id){
        if (!this.L.containsKey(node_id)){ // should be L
            Node node = new Node(node_id);
            this.L.put(node.id, node);
            this.map.put(node.id, node);
        }
    }

    public void addRight(int node_id){
        if (!this.R.containsKey(node_id)){ // shoul be R
            Node node = new Node(node_id);
            this.R.put(node.id, node);
            this.map.put(node.id, node);
        }
    }

    public void connect(int left_id, int right_id){
        if(R.containsKey(left_id) || L.containsKey(right_id) || left_id == right_id){
            // System.out.println("SU KEYLERDE SIKINTI VAR: " + left_id + " " + right_id + " ");
            return;
        }

        this.addLeft(left_id); this.addRight(right_id);
        Node left = this.map.get(left_id);
        Node right = this.map.get(right_id);

        // if right has lower degree, search is faster
        if((!(right.adjacency_list.contains(left)))){ // no multiple edges
            left.adjacency_list.add(right);
            right.adjacency_list.add(left);

            left.degree++; right.degree++;

            ArrayList<Node> edge = new ArrayList<>();
            edge.add(left); edge.add(right);
            this.edge_list.add(edge);
        }
    }


}   // class
