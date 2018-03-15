package edu.info6205.parallelSort;

import static edu.info6205.parallelSort.Helper.*;

public class MergeSort {
	
	@SuppressWarnings({ "rawtypes" })
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for(int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++) {
			if(i > mid) 					a[k] = aux[j++];
			else if(j > hi) 				a[k] = aux[i++];
			else if(less(aux[j], aux[i])) 	a[k] = aux[j++];
			else 							a[k] = aux[i++];
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if(hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}
	
	@SuppressWarnings("rawtypes")
	public void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
	}
	
}
