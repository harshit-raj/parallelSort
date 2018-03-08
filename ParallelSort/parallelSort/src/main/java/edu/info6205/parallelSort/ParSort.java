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
            CompletableFuture<int[]> parsort1 = parsort(array, from, mid) ; //called parasort on first half
            CompletableFuture<int[]> parsort2 = parsort(array, mid,to); // called parasort on second half
            //callback when parasort 1 completes
            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        int x1 = 0, x2 = 0, rs = 0;
                        int lxs1 = xs1.length, lxs2 = xs2.length;
                        //merging
                        while(x1<lxs1 && x2< lxs2) {
                        	
                        	if(xs1[x1]<=xs2[x2]) {
                        		result[rs] = xs1[x1];
                        		x1++;
                        	}
                        	else {
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
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {
            	for (int i = 0; i < result.length; i++) {
					array[i] = result[i];
				}
            }); 
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                	//System.out.println("from : "+from+ "to : "+to+ "to - from : "+(to-from));
                	int[] result = new int[to  - from];
                	int i = 0;
                	while(i < result.length) {
                		result[i] = array[from+i];
                		i++;
                	}
                	//call sort again with the smaller array
                    sort(result,0,result.length);
                	
                	return result;
                }
        );
    }
}
