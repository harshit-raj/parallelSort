package edu.info6205.parallelSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Integer> {
    private int[] arr;
 
    private static final int THRESHOLD = 20;
 
    public CustomRecursiveTask(int[] arr) {
        this.arr = arr;
    }
 
    @Override
    protected int[] compute() {
        if (arr.length > THRESHOLD) {
            Ar
        	/*return ForkJoinTask.invokeAll(createSubtasks())
              .stream()
              .mapToInt(ForkJoinTask::join)
              .sum();*/
        } else {
            return processing(arr);
        }
    }
 
    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }
 
    private int[] processing(int[] arr2) {
        /*return Arrays.stream(arr)
          .filter(a -> a > 10 && a < 27)
          .map(a -> a * 10)
          .sum();*/
    	Arrays.sort(arr2);
    	return arr2;
    	
    }
}