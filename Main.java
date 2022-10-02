import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //ReadFile.readAndWrite();
        BipartiteGraph graph = CreateGraph.createGraph();

//        System.out.println(graph.L.size() +" "+ graph.R.size() +" "+ graph.map.size());

//        System.out.println(graph.L);
//        System.out.println(graph.R);

        // Traingles
//        for(int i: graph.map.keySet()){
//            for(Node j: graph.map.get(i).adjacency_list.keySet()){
//                for(Node k: j.adjacency_list.keySet()){
//                    if(k.adjacency_list.containsKey(graph.map.get(i))){
//                        System.out.println(i +" "+j.id +" " + k.id);
//                    }
//                }
//            }
//        }


        // Results
//        Sample sample = new Sample(graph);
//        int[] k_sets = {100, 200, 500, 1000, 3000, 6000, 10000}; // authors use 1000 iterations
//
//        double p = (double) 1 / graph.map.size() ;
//        int N = 10;
//
        System.out.println("Number of L nodes: " + graph.L.size()+"\tNumber of R nodes: "+graph.R.size());
        System.out.println("Number of nodes: " + graph.map.size()+"\tNumber of edges: "+graph.edge_list.size());
//        System.out.println("Exact: " + ExactCount.ExactBFC(graph));
//        //System.out.println("Exact with ESPAR with p="+p+": " + sample.ESPAR(p));
//        //System.out.println("Exact with CLRSPAR with N="+N+": " + sample.CLRSPAR(N));
//
//        System.out.println("\n              k             VSamp              ESamp           FastEBFC              WSamp");
//
//        for(int k: k_sets){
//            System.out.printf("%15d%18d ", k, (long) sample.VSamp(k));
//            System.out.printf("%18d ", (long) sample.ESamp(k, 0)); // 0 indicates we use eBFC
//            System.out.printf("%18d ", (long) sample.ESamp(k, 1)); // 1 indicates we use FastEBFC
//            System.out.printf("%18d ", (long) sample.WSamp(k));
//            System.out.println();
//
//        }

    }
}
