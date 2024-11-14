package lab1.classes;

public class PerformanceAnalyzer {
    public static void measurePerformance(int[] array, int multiplier, int numThreads) {
        long startTime = System.nanoTime();
        ParallelProcessor.processArray(array, multiplier, numThreads);
        long endTime = System.nanoTime();
        System.out.println("Время выполнения для " + numThreads + " потоков: " + (endTime - startTime) + " нс");
    }
}

