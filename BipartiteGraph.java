import java.util.*;

public class BipartiteGraph {
    public int N;
    public int M;
    public HashMap<Integer, Integer> L;    // node_id: node
    public HashMap<Integer, Integer> R;    // node_id: node
    public HashMap<Integer, Node> map; // all nodes
    public ArrayList<ArrayList<Integer>> edge_list; // [[node1, node2],[n3, n4] ...]
    LinkedList<Integer> look_children;

    public BipartiteGraph() {
        this.L = new HashMap<>();
        this.R = new HashMap<>();
        this.map = new HashMap<>();
        this.edge_list = new ArrayList<>();
        this.look_children = new LinkedList<>();
    }

    public long sumOfSquaresOfDegrees(HashMap<Integer, Integer> V){
        long count = 0;
        for (int id : V.values()) {
            count += Math.pow(this.map.get(id).degree, 2) ;
        }
        return count;
    }

//    public void addLeft(int node_id){
//        if (!this.L.containsKey(node_id)){
//            Node node = new Node(node_id);
//            this.L.put(node.id, node);
//            this.map.put(node.id, node);
//        }
//    }
//
//    public void addRight(int node_id){
//        if (!this.R.containsKey(node_id)){
//            Node node = new Node(node_id);
//            this.R.put(node.id, node);
//            this.map.put(node.id, node);
//        }
//    }

    public void addNode(int id){
        if(!this.map.containsKey(id)){
            this.map.put(id, new Node(id));
        }
    }

    public void connect(int left_id, int right_id){

        if(left_id == right_id){ // no self loops
            //System.out.println("SU KEYLERDE SIKINTI VAR: " + left_id + " " + right_id + " ");
            return;
        }

        this.addNode(left_id); this.addNode(right_id);
        Node left = this.map.get(left_id);
        Node right = this.map.get(right_id);

        if((!(right.adjacency_list.containsKey(left)))){ // no multiple edges
            left.adjacency_list.put(right, 1);
            right.adjacency_list.put(left, 1);

            left.degree++; right.degree++;

            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(left_id); edge.add(right_id);
            this.edge_list.add(edge);
        }
    }


    public void split(){
        HashMap<Integer, Integer> assigned = new HashMap<>(); // 0 indicates left, 1 indicates right

        this.L.put(1, 1);
        this.look_children.add(1);
        while(look_children.size() != 0){
            int id = look_children.removeFirst();
            this.assignSide(id);
        }
    }

    public void assignSide(int id){

        for(Node node: this.map.get(id).adjacency_list.keySet()){
            if(this.L.containsKey(id)){
                if(!this.R.containsKey(node.id)){
                    this.R.put(node.id, node.id);
                    this.look_children.add(node.id);
                }
            }
            else if(this.R.containsKey(id)){
                if(!this.L.containsKey(node.id)){
                    this.L.put(node.id, node.id);
                    this.look_children.add(node.id);
                }
            }
        }

    }
}   // class
