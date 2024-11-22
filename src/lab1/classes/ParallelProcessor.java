package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelProcessor {
    public static void processArray(String inputFilename, int N, int threadsCount, String outputFilename) {
        long startTime = System.nanoTime();
        long[] array = new long[N];
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                array[index++] = Long.parseLong(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int chunkSize = (int) Math.ceil((double) array.length / threadsCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        for (int i = 0; i < threadsCount; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, array.length);

            executor.execute(() -> {
                for (int j = start; j < end; j++) {
                    array[j] *= 2;
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for(int i = 0; i < array.length; i++){
                writer.write(array[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println("Время выполнения для " + threadsCount + " потоков: " + (endTime - startTime) + " нс");
    }

    public static void processArray(String inputFilename, int N, int threadsCount, String outputFilename, int operation) {
        long startTime = System.nanoTime();
        long[] array = new long[N];
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                array[index++] = Long.parseLong(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int chunkSize = (int) Math.ceil((double) array.length / threadsCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        for (int i = 0; i < threadsCount; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, array.length);

        switch (operation) {
            case 1:
                executor.execute(() -> {
                    for (int j = start; j < end; j++) {
                        array[j] = ComplexOperations.square(array[j]);
                    }
                });
                break;
                
            case 2:
                executor.execute(() -> {
                    for (int j = start; j < end; j++) {
                        array[j] = ComplexOperations.fibonacci(array[j]);
                    }
                });
                break;
            
            case 3:
                executor.execute(() -> {
                    for (int j = start; j < end; j++) {
                        array[j] = ComplexOperations.factorial(array[j]);
                    }
                });
                break;
            default:
                break;
        }
    }
        executor.shutdown();
        while (!executor.isTerminated()) {}
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for(int j = 0; j < array.length; j++){
                    writer.write(array[j] + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            long endTime = System.nanoTime();
            System.out.println("Время выполнения  для " + threadsCount + " потоков операции " + (operation == 1 ? "возведение в степень" : operation == 2 ? "Фибоначчи" : "интеграл") + ": "  + (endTime - startTime) + " нс");
        }
    }
