import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    public int degree;
    public int id;
    public HashMap<Node, Integer> adjacency_list;

    public Node(int id) {
        this.id = id;
        this.adjacency_list = new HashMap<>();
    }
}
