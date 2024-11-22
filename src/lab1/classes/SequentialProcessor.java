package classes;

import java.io.*;

public class SequentialProcessor {
    public static void processFile(String inputFilename, String outputFilename) {
        long startTime = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                long number = Long.parseLong(line);
                writer.write((number * 2) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println("Время последовательной обработки: " + (endTime - startTime) + " нс");
    }

    public static void processFile(String inputFilename, String outFilename, int operation){
        long startTime;
        long endTime;
        switch (operation) {
            case 1:
                startTime = System.nanoTime();
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(outFilename))) {
                     String line;
                     while ((line = reader.readLine()) != null) {
                        int number = Integer.parseInt(line);
                        writer.write((ComplexOperations.square(number)) + "\n");
                     }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                endTime = System.nanoTime();
                System.out.println("Время последовательной обработки при возведении в степень: " + (endTime - startTime) + " нс");
                break;

            case 2:
                startTime = System.nanoTime();
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outFilename))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        int number = Integer.parseInt(line);
                        writer.write((ComplexOperations.fibonacci(number)) + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                endTime = System.nanoTime();
                System.out.println("Время последовательной обработки при числах Фибоначчи: " + (endTime - startTime) + " нс");
                break;
            
            case 3:
                startTime = System.nanoTime();
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outFilename))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        int number = Integer.parseInt(line);
                        writer.write((ComplexOperations.factorial(number)) + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                endTime = System.nanoTime();
                System.out.println("Время последовательной обработки при факториале: " + (endTime - startTime) + " нс");
                break;

            default:
                break;
        }
    }
}
