import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

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
        Random generator = new Random();
        double randomDouble = generator.nextDouble();

        BipartiteGraph graph = new BipartiteGraph();

        BufferedReader br = new BufferedReader((new FileReader(ReadFile.write_location)));
        String line;

        while ((line = br.readLine()) != null){
            if(randomDouble < p){
                String[] splitted = line.split(" ");
                int left_id = Integer.parseInt(splitted[0]);
                int right_id = Integer.parseInt(splitted[1]);
                graph.connect(left_id, right_id);
            }
        }
        return graph;
    }


}
