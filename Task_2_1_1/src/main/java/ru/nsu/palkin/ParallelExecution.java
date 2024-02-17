package ru.nsu.palkin;

/**
 * Class of the parallel solution.
 */
public class ParallelExecution implements Prime {
    private int[] array;
    private int threadNumber;

    /**
     * Class constructor.
     *
     * @param array        - array of numbers
     * @param threadNumber - number of threads
     */
    public ParallelExecution(int[] array, int threadNumber) {
        this.array = array;
        this.threadNumber = threadNumber;
    }

    /**
     * Solution method.
     *
     * @return true or false
     */
    public boolean hasNotPrime() throws InterruptedException {
        int subArrLen = this.array.length / this.threadNumber;
        int residual = this.array.length % this.threadNumber;
        Thread[] threads = new Thread[this.threadNumber];
        ThreadTask[] tasks = new ThreadTask[this.threadNumber];
        int previousPos = 0;
        for (int i = 0; i < this.threadNumber; i++) {
            if (i < residual) {
                tasks[i] = new ThreadTask(previousPos, previousPos + subArrLen + 1, this.array);
                threads[i] = new Thread(tasks[i]);
                previousPos = previousPos + subArrLen + 1;
            } else {
                tasks[i] = new ThreadTask(previousPos, previousPos + subArrLen, this.array);
                threads[i] = new Thread(tasks[i]);
                previousPos = previousPos + subArrLen;
            }
            threads[i].start();
        }
        for (int i = 0; i < this.threadNumber; i++) {
            threads[i].join();
        }
        for (int i = 0; i < this.threadNumber; i++) {
            if (tasks[i].result) {
                return true;
            }
        }
        return false;
    }

    /**
     * Thread's task class.
     */
    private class ThreadTask implements Runnable {
        private int begin;
        private int end;
        private int[] array;
        private volatile boolean result = false;

        /**
         * Class constructor.
         *
         * @param begin - first index
         * @param end   - last index
         * @param array - array of numbers
         */
        public ThreadTask(int begin, int end, int[] array) {
            this.begin = begin;
            this.end = end;
            this.array = array;
        }

        /**
         * Run method.
         */
        @Override
        public void run() {
            for (int i = begin; i < end; i++) {
                if (isNotPrime(this.array[i])) {
                    this.result = true;
                    return;
                }
            }
        }
    }
}
