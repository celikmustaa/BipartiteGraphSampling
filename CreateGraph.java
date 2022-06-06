import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CreateGraph {

    public static BipartiteGraph createGraph() throws IOException {

        BipartiteGraph graph = new BipartiteGraph();

        BufferedReader br = new BufferedReader((new FileReader(ReadFile.write_location)));

        String line;
        while ((line = br.readLine()) != null){
            String[] splitted = line.split(" ");
            int left_id = Integer.parseInt(splitted[0]);
            int right_id = Integer.parseInt(splitted[1]);
            graph.connect(left_id, right_id);

        }

        return graph;
    }

    // for ESPAR
    public static BipartiteGraph createGraph(double p) throws IOException {
        BipartiteGraph graph = new BipartiteGraph();

        BufferedReader br = new BufferedReader((new FileReader(ReadFile.write_location)));
        String line;

        while ((line = br.readLine()) != null){
            Random generator = new Random();
            double randomDouble = generator.nextDouble();
            if(randomDouble < p){
                String[] splitted = line.split(" ");
                int left_id = Integer.parseInt(splitted[0]);
                int right_id = Integer.parseInt(splitted[1]);
                graph.connect(left_id, right_id);
            }
        }
        return graph;
    }


    public static BipartiteGraph createGraph(int N) throws IOException {

        BipartiteGraph graph = new BipartiteGraph();
        BufferedReader br = new BufferedReader((new FileReader(ReadFile.write_location)));
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
        return graph;
    }

}
