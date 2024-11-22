import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import classes.FileGenerator;
import classes.ParallelProcessor;
import classes.SequentialProcessor;

public class Main {
    public static void main(String[] args) {
        String inputfile = "numbers.txt";
        String outputfile = "output.txt";
        String configName = "app.config";
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(configName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int N = Integer.parseInt(prop.getProperty("N"));
        int threadsCount = Integer.parseInt(prop.getProperty("THREADS_COUNT"));
        int operation = Integer.parseInt(prop.getProperty("OPERATION"));
        FileGenerator.generateFile(N, "numbers.txt");
        SequentialProcessor.processFile(inputfile, outputfile);
        ParallelProcessor.processArray(inputfile, N, threadsCount, outputfile);
        //SequentialProcessor.processFile(inputfile, outputfile, operation);
        //ParallelProcessor.processArray(inputfile, N, threadsCount, outputfile, operation);
    }
}
