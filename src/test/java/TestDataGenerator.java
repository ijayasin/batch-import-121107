import org.junit.Ignore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @author mh
 * @since 13.01.12
 */
@Ignore
public class TestDataGenerator {

    private static int NODES = 75 * 1000 * 100; // * 1000;
    private static final int RELS_PER_NODE = 50;
    private static final String[] TYPES = {"ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE","TEN"};
    public static final int NUM_TYPES = 10;

    public static void main(String...args) throws IOException {
        Random rnd = new Random();
        long relCount=0, time = System.currentTimeMillis();
        BufferedWriter nodeFile = new BufferedWriter(new FileWriter("nodes.csv"));
        nodeFile.write("Node\tRels\tProperty\tCounter:int\n");
        BufferedWriter relFile = new BufferedWriter(new FileWriter("rels.csv"));
        relFile.write("Start\tEnde\tType\tProperty\tCounter:long\n");
        for (int node = 0; node < NODES; node++) {
            final int rels = rnd.nextInt(RELS_PER_NODE);
            nodeFile.write(node+"\t"+rels+"\tTEST\t"+node+"\n");
            for (int rel = rels; rel >= 0; rel--) {
                relCount++;
                final int node1 = rnd.nextInt(NODES);
                final int node2 = rnd.nextInt(NODES);
                relFile.write(node1 + "\t" + node2 + "\t" + TYPES[rel % NUM_TYPES] + "\t" + "Property"+"\t" + relCount+ "\n");
            }
        }
        nodeFile.close();
        relFile.close();
        System.out.println("Creating "+NODES+" and "+relCount+" Relationships took "+((System.currentTimeMillis()-time)/1000)+" seconds.");
    }
}
