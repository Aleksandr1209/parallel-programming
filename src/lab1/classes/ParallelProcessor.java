package lab1.classes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelProcessor {
    public static void processArray(int[] array, int multiplier, int numThreads) {
        int chunkSize = (int) Math.ceil((double) array.length / numThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, array.length);

            executor.execute(() -> {
                for (int j = start; j < end; j++) {
                    array[j] *= multiplier;
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}
    }
}

