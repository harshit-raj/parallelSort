/**
 * 
 */
package edu.info6205.parallelSort;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * @author harsh
 *
 */
public class ParaSortCF {
	public static ExecutorService pool;
	public static int cutoff = 10;
	
	public static void sort(int[] array, int from, int to) {
		int l = array.length;
		int mid = l/2;
		
		CompletableFuture<int[]> parsort1 = parsort(array, from, mid) ;
	}
	
}

class MergeSort implements Callable<Integer []>{
	Integer[] ar;
	
	

	public Integer[] getAr() {
		return ar;
	}



	public void setAr(Integer[] ar) {
		this.ar = ar;
	}



	@Override
	public Integer[] call() throws Exception {
		
		
		if(l < cutoff) {
			Arrays.sort(ar);
			
		}
		else {
			
		}
		
		
		
		
		return null;
	}
	
}
