package edu.info6205.parallelSort;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

class ParSort {
	
	//public static ExecutorService pool; //= Executors.newFixedThreadPool(200);
	public static ForkJoinPool pool= new ForkJoinPool(16);
	public static int recusionCount = 0;
	public static int recursionLimit = 1000;
    public static int cutoff = 1000;
    public static void sort(int[] array, int from, int to) {
    	//int cores = Runtime.getRuntime().availableProcessors();
    	//int cores = 100;
    	//pool = new ForkJoinPool(cores);
    	int size = to - from;
        int mid = from +size/2;
        //ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)pool;
        //System.out.println("Pool Size : "+threadPoolExecutor.getPoolSize()+"    Active Count: "+threadPoolExecutor.getPoolSize());
    	if (size < cutoff) Arrays.sort(array, from, to);
        else {
        	//System.out.println("First split");
            CompletableFuture<int[]> parsort1 = parsort(array, from, mid,pool) ; //called parasort on first half
            //System.out.println("Second split");
            CompletableFuture<int[]> parsort2 = parsort(array, mid,to,pool); // called parasort on second half
            //callback when parasort 1 completes
            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        //System.out.println("Merging-----------");
                    	int x1 = 0, x2 = 0, rs = 0;
                        int lxs1 = xs1.length, lxs2 = xs2.length;
                        //merging
                        while(x1<lxs1 && x2< lxs2) {
                        	
                        	if(xs1[x1]<=xs2[x2]) {
                        		array[rs] = xs1[x1];
                        		x1++;
                        	}
                        	else {
                        		array[rs] = xs2[x2];
                        		x2++;
                        	}
                        	rs++;
                        }
                        
                        while(x1<lxs1) {
                        	array[rs++] = xs1[x1++];
                        }
                        
                        while(x2<lxs2) {
                        	array[rs++] = xs2[x2++];
                        }
                        //System.out.println(pool.toString());
                        return array;
                    });

            parsort.join();
            //System.out.println(pool.toString());
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to, ForkJoinPool es) {
        recusionCount++;
        //System.out.println("Recursion Count "+recusionCount);
    	//System.out.println("CompFuture called");
    	return CompletableFuture.supplyAsync(
                () -> {
                	//System.out.println("from : "+from+ "to : "+to+ "to - from : "+(to-from));
                	int[] result = new int[to  - from];
                	int i = 0;
                	//result = Arrays.copyOfRange(array, from,to);
                	while(i < result.length) {
                		result[i] = array[from+i];
                		i++;
                	}
                	//call sort again with the smaller array
                    sort(result,0,result.length);
                	
                	return result;
                },es
        );
    }
}
