package forkJoinBasic;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class Search extends RecursiveTask<Integer> {

    private final int[] arr;
    private final int start;
    private final int end;
    private final int searchEle;

    public Search(int[] arr, int start, int end, int searchEle) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchEle = searchEle;
    }

    @Override
    protected Integer compute() {
        System.out.println("Thread --> " + Thread.currentThread());
        int result;
        int size = end - start + 1;
        if (size > 3) {
            int mid = (start + end) / 2;
            Search task1 = new Search(arr, start, mid, searchEle);
            Search task2 = new Search(arr, mid + 1, end, searchEle);
            task1.fork();
            task2.fork();
            result = task1.join() + task2.join();
        } else {
            result = computeSearch(arr, start, end, searchEle);
        }
        return result;
    }

    private Integer computeSearch(int[] arr, int start, int end, int searchEle) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (arr[i] == searchEle) {
                count++;
            }
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) {

        int[] arr = {1, 2, 9, 4, 5, 6, 7, 8, 9, 10, 9, 12};
        Search newSearch = new Search(arr, 0, arr.length - 1, 9);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Integer result = pool.invoke(newSearch);
        System.out.printf("%d element found %d times%n", 9, result);
    }
}
