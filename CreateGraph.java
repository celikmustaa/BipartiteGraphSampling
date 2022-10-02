import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CreateGraph {
    //public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\brunson_revolution\\out.brunson_revolution_revolution";
    public static String path = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\amazon-ratings\\out.amazon-ratings";

    public static BipartiteGraph createGraph() throws IOException {

        BipartiteGraph graph = new BipartiteGraph();

        BufferedReader br = new BufferedReader((new FileReader(path)));

        String line;
        while ((line = br.readLine()) != null){
            String[] splitted = line.split(" ");
            int left_id = Integer.parseInt(splitted[0]);
            int right_id = (-1) * Integer.parseInt(splitted[1]); // 300 iQ O YE
            graph.connect(left_id, right_id);

        }
        //graph.split();
        return graph;
    }

    // for ESPAR
    public static BipartiteGraph createGraph(double p) throws IOException {
        Random generator = new Random();
        double randomDouble;
        BipartiteGraph graph = new BipartiteGraph();

        BufferedReader br = new BufferedReader((new FileReader(path)));
        String line;

        while ((line = br.readLine()) != null){
            randomDouble = generator.nextDouble();
            if(randomDouble < p){
                String[] splitted = line.split(" ");
                int left_id = Integer.parseInt(splitted[0]);
                int right_id = Integer.parseInt(splitted[1]);
                graph.connect(left_id, right_id);
            }
        }
        //graph.split();
        return graph;
    }


    public static BipartiteGraph createGraph(int N) throws IOException {

        BipartiteGraph graph = new BipartiteGraph();
        BufferedReader br = new BufferedReader((new FileReader(path)));
        HashMap<Integer, Integer> colors = new HashMap<>();

        String line;
        while ((line = br.readLine()) != null){
            String[] splitted = line.split(" ");
            int left_id = Integer.parseInt(splitted[0]);
            int right_id = Integer.parseInt(splitted[1]);
            if(!colors.containsKey(left_id)){
                colors.put(left_id, ThreadLocalRandom.current().nextInt(1, N + 1));
            }
            if(!colors.containsKey(right_id)){
                colors.put(right_id, ThreadLocalRandom.current().nextInt(1, N + 1));
            }
            if(colors.get(left_id).equals(colors.get(right_id))){
                graph.connect(left_id, right_id);
            }
        }
        //graph.split();
        return graph;
    }

}
