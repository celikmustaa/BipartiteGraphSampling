import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ReadFile.readAndWrite();
        BipartiteGraph graph = CreateGraph.createGraph();

//        System.out.println(graph.L.size() +" "+ graph.R.size() +" "+ graph.map.size());
//
//        System.out.println(graph.L);
//        System.out.println(graph.R);

        int[] k_sets = {100, 200, 500, 1000, 3000, 6000, 10000};
        Sample sample = new Sample(graph);
        System.out.println("Number of nodes: " + graph.map.size()+"\tNumber of edges: "+graph.edge_list.size());
        System.out.println("Exact: " + ExactCount.ExactBFC(graph)+"\n");
        System.out.println("    k   VSamp    ESamp    WSamp");

        for(int k: k_sets){
            System.out.printf("%5d%8d ", k, (int) sample.VSamp(k));
            System.out.printf("%8d ", (int) sample.ESamp(k));
            System.out.printf("%8d ", (int) sample.WSamp(k));
            System.out.println();

        }

    }
}
