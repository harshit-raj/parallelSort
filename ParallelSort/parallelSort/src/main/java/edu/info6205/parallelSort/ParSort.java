package edu.info6205.parallelSort;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

class ParSort {

    public static int cutoff = 1000;

    public static void sort(int[] array, int from, int to) {

    	int size = to - from;
        int mid = from +size/2;
    	if (size < cutoff) Arrays.sort(array, from, to);
        else {
            CompletableFuture<int[]> parsort1 = parsort(array, from, mid) ; // TODO implement me
            CompletableFuture<int[]> parsort2 = parsort(array, mid+1,to); // TODO implement me
            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        System.out.println("In combine part");
                        System.out.println("Printing xs1");
                        for(int x : xs1) {
                        	System.out.println(x);
                        }
                        System.out.println("Printing xs2---------------------------------------------");
                        for(int x : xs2) {
                        	System.out.println(x);
                        }
                        int x1 = 0, x2 = 0, rs = 0;
                        int lxs1 = xs1.length, lxs2 = xs2.length, lrs = result.length;
                        
                        while(x1<lxs1 && x2< lxs2) {
                        	
                        	if(xs1[x1]<=xs2[x2]) {
                        		System.out.println("Compared "+xs1[x1] + " , "+xs2[x2] );
                        		result[rs] = xs1[x1];
                        		x1++;
                        	}
                        	else {
                        		System.out.println("Compared "+xs1[x1] + " , "+xs2[x2] );
                        		result[rs] = xs2[x2];
                        		x2++;
                        	}
                        	rs++;
                        }
                        
                        while(x1<lxs1) {
                        	result[rs++] = xs1[x1++];
                        }
                        
                        while(x2<lxs2) {
                        	result[rs++] = xs2[x2++];
                        }
                        
                        
                        
                        System.out.println("Done with the for loop");
                        for(int x : result) {
                        	//System.out.println(x);
                        }
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {
            	System.out.println("Its complete");
            	for (int i = 0; i < result.length; i++) {
					array[i] = result[i];
				}
            }); // TODO implement me
            parsort.join();
            System.out.println("After join");
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to  - from];
                    for(int k = from; k<result.length; k++) {
                    	result[k] =array[k];
                    }
                    //call sort again with the smaller array
                    sort(result,0,result.length-1);
                    return result;
                }
        );
    }
}
