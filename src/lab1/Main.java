package lab1;

import lab1.classes.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Параметры
        int N = 1000000; // Количество чисел
        int multiplier = 2; // Множитель
        int numThreads = 4; // Число потоков для многопоточной обработки
        String inputFilename = "numbers.txt";
        String outputFilename = "processed_numbers.txt";

        // Шаг 1: Генерация файла
        FileGenerator.generateFile(N, inputFilename);
        System.out.println("Файл с числами сгенерирован.");

        // Шаг 2: Последовательная обработка
        long startTime = System.nanoTime();
        SequentialProcessor.processFile(inputFilename, outputFilename, multiplier);
        long endTime = System.nanoTime();
        System.out.println("Время последовательной обработки: " + (endTime - startTime) + " нс");

        // Чтение данных из файла в массив
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

        // Шаг 3: Многопоточная обработка
        PerformanceAnalyzer.measurePerformance(array, multiplier, numThreads);

        // Шаг 4: Усложненная операция (пример возведения в квадрат)
        for (int i = 0; i < array.length; i++) {
            array[i] = ComplexOperations.square(array[i]);
        }
        System.out.println("Сложная операция выполнена.");

        // Шаг 5: Пример неравномерной загрузки
        ParallelProcessor.processArray(array, multiplier, 2); // Один поток обрабатывает малую часть
        System.out.println("Неравномерная загрузка потоков выполнена.");
    }
}
