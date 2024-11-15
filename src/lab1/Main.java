package lab1;

import lab1.classes.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int N = 1000000;
        int multiplier = 2;
        int numThreads = 4;
        String inputFilename = "numbers.txt";
        String outputFilename = "processed_numbers.txt";

        FileGenerator.generateFile(N, inputFilename);
        System.out.println("Файл с числами сгенерирован.");

        long startTime = System.nanoTime();
        SequentialProcessor.processFile(inputFilename, outputFilename, multiplier);
        long endTime = System.nanoTime();
        System.out.println("Время последовательной обработки: " + (endTime - startTime) + " нс");

        int[] array = new int[N];
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                array[index++] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PerformanceAnalyzer.measurePerformance(array, multiplier, numThreads);

        for (int i = 0; i < array.length; i++) {
            array[i] = ComplexOperations.square(array[i]);
        }
        System.out.println("Сложная операция выполнена.");

        ParallelProcessor.processArray(array, multiplier, 2);
        System.out.println("Неравномерная загрузка потоков выполнена.");
    }
}
