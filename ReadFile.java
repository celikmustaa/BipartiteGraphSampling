import java.io.*;
import java.util.*;

public class ReadFile {
    public static String read_location = "C:\\Users\\musta\\Desktop\\Hacettepe\\DREAM\\wikiElec.ElecBs3.txt";
    public static String write_location = "edges.txt";
    public static ArrayList<String> lines = new ArrayList<String>();

    public static void readAndWrite() throws IOException {
        BufferedReader br = new BufferedReader((new FileReader(read_location)));

        String line = null;
        while ((line = br.readLine()) != null){
            lines.add(line);
        }


        int E = -1; int U_id = -1; int N_id = -1; int V_id = -1;

        // Step 1: Create an object of BufferedWriter
        BufferedWriter f_writer = new BufferedWriter(new FileWriter(write_location));

        for (String s: lines) {
            String[] splitted = s.split("\t");

            if (s.startsWith("E")) {
                E = Integer.parseInt(splitted[1]);
            }

            else if (s.startsWith("U")) {
                U_id = Integer.parseInt(splitted[1]);
            }

            else if (s.startsWith("N")) {
                N_id = Integer.parseInt(splitted[1]);
            }

            else if (s.startsWith("V") && E == 1) {
                V_id = Integer.parseInt(splitted[2]);
                int vote = Integer.parseInt(splitted[2]);

                if (vote == 0 || (vote == 1 && U_id == -1) || (vote == -1 && N_id == -1)) {
                    continue;
                }

                int left_node_id = ((vote == 1) ? U_id: N_id);

                if(left_node_id != -1 && V_id != -1){
                    f_writer.write(left_node_id +" "+V_id+"\n");
                }

            }
        }
        f_writer.close();
    }


} // class
