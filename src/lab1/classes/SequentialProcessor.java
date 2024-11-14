package lab1.classes;

import java.io.*;

public class SequentialProcessor {
    public static void processFile(String inputFilename, String outputFilename, int multiplier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                writer.write((number * multiplier) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

