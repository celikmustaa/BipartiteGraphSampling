import java.util.ArrayList;

public class Node {
    public int degree;
    public int id;
    public ArrayList<Node> adjacency_list;

    public Node(int id) {
        this.id = id;
        this.adjacency_list = new ArrayList<>();
    }
}
