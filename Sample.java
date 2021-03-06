import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Sample {
    public BipartiteGraph graph;
    public long number_of_wedges = 0;
    public long[] cumulative_wedge_array;
    public int[] indices;

    public Sample(BipartiteGraph graph){
        this.graph = graph;
        this.cumulative_wedge_array = new long[graph.map.size()];
        this.indices = new int[graph.map.size()];

        int i = 0;
        for(Node node: this.graph.map.values()){
            this.indices[i] = node.id;
            long wedge_of_node = (long)(node.degree)*(node.degree - 1) / 2;
            if(i == 0){
                this.cumulative_wedge_array[i] = wedge_of_node;
            }
            else{
                this.cumulative_wedge_array[i] = wedge_of_node + this.cumulative_wedge_array[i-1];
            }
            this.number_of_wedges += wedge_of_node;
            i++ ;
        }
    }


    // Algorithm 4
    public double VSamp(int k){
        double final_result = 0;
        for(int i=0; i<k; i++){
            Random generator = new Random();
            Object[] values = this.graph.map.values().toArray();
            Node randomNode = (Node) values[generator.nextInt(values.length)];

            final_result += ( ((double)ExactCount.vBFC(randomNode) ) * this.graph.map.size()) / 4;
        }
        return final_result/k;
    }


    // Algorithm 5
    public double ESamp(int k, int ident){ // if ident == 1 then we need to use FastEBFC
        double final_result = 0;
        for(int i=0; i<k; i++){
            Random generator = new Random();
            ArrayList<Integer> randomEdge = this.graph.edge_list.get(generator.nextInt(this.graph.edge_list.size()));

            if(ident == 1){
                final_result += ( ((double) FastEBFC(randomEdge) ) * this.graph.edge_list.size()) / 4;
            }
            else{
                ArrayList<Node> node_array = new ArrayList<>();
                node_array.add(graph.map.get(randomEdge.get(0))); node_array.add(graph.map.get(randomEdge.get(1)));
                final_result += ( ((double)ExactCount.eBFC(node_array) ) * this.graph.edge_list.size()) / 4;
            }

        }
        return final_result/k;
    }


    // Algorithm 6
    public double WSamp(int k){
        double final_result = 0;
        for(int x=0; x<k; x++){
            long randomLong = ThreadLocalRandom.current().nextLong(1, this.number_of_wedges + 1);
            Node node_u = this.graph.map.get(this.indices[binarySearch(randomLong, this.cumulative_wedge_array)]);

            int randomInt1 = ThreadLocalRandom.current().nextInt(0, node_u.adjacency_list.size());
            int randomInt2 = ThreadLocalRandom.current().nextInt(0, node_u.adjacency_list.size() - 1);
            if(randomInt2 >= randomInt1){
                randomInt2++;
            }

            List<Node> keysAsArray = new ArrayList<>(node_u.adjacency_list.keySet());
            Node node_v = keysAsArray.get(randomInt1);
            Node node_w = keysAsArray.get(randomInt2);

            long beta = 0;

            if(node_v.degree < node_w.degree){
                for(Node node1: node_v.adjacency_list.keySet()){
                    if(node_w.adjacency_list.containsKey(node1)){
                        beta += 1;
                    }
                }
            }
            else{
                for(Node node1: node_w.adjacency_list.keySet()){
                    if(node_v.adjacency_list.containsKey(node1)){
                        beta += 1;
                    }
                }
            }


            final_result += (double) beta * this.number_of_wedges / 4 - 1;

            //System.out.print("x:"+x+" ");
        }
        //System.out.println();
        return final_result/k;

    }


    // Algorithm 7
    public long FastEBFC(ArrayList<Integer> edge){
        long beta = 0;
        Node u = graph.map.get(edge.get(0));
        Node v = graph.map.get(edge.get(1));

        List<Node> keysAsArray = new ArrayList<>(u.adjacency_list.keySet());
        Node w = keysAsArray.get(ThreadLocalRandom.current().nextInt(0, u.adjacency_list.size()));
        keysAsArray = new ArrayList<>(v.adjacency_list.keySet());
        Node x = keysAsArray.get(ThreadLocalRandom.current().nextInt(0, v.adjacency_list.size()));

        if(w.adjacency_list.containsKey(x)){
            return (long) u.degree*v.degree;
        }
        return beta;
    }


    // Algorithm 8
    public double ESPAR(double p) throws IOException {
        BipartiteGraph graph = CreateGraph.createGraph(p);
        long beta = ExactCount.ExactBFC(graph);

        return beta * Math.pow(p, -4);
    }


    // Algorithm 9
    public double CLRSPAR(int N) throws IOException {
        BipartiteGraph graph = CreateGraph.createGraph(N);
        long beta = ExactCount.ExactBFC(graph);

        return beta * Math.pow((double) 1/N, -3);
    }


    // returns an index so that randomNumber is either smaller than or equal to the number in
    // the cumulative array at that index but also greater than the number before that index
    public static int binarySearch(long randomNumber,  long[] cumulative_array){
        int left = 0; int right = cumulative_array.length; // left inclusive, right exclusive
        while (true){
            if (randomNumber <= cumulative_array[left]){
                return left;
            }

            int middle = left + (right-left)/2;

            if (randomNumber <= cumulative_array[middle] && randomNumber > cumulative_array[middle-1]){
                return middle;
            }

            if (randomNumber <= cumulative_array[middle]){
                right = middle;
            }
            else{
                left = middle + 1;
            }
        }
    }
}
