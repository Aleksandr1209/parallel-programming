package lab1.classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
    public static void generateFile(int N, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 1; i <= N; i++) {
                writer.write(i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}