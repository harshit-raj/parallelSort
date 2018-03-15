package main.sort;

import static main.sort.Helper.*;

public class MergeSort<X> implements Sort<X>{
	
//	@SuppressWarnings({ "rawtypes" })
//	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
//		for(int k = lo; k <= hi; k++) {
//			aux[k] = a[k];
//		}
//		int i = lo, j = mid+1;
//		for(int k = lo; k <= hi; k++) {
//			if(i > mid) 					a[k] = aux[j++];
//			else if(j > hi) 				a[k] = aux[i++];
//			else if(less(aux[j], aux[i])) 	a[k] = aux[j++];
//			else 							a[k] = aux[i++];
//		}
//	}
//	
//	@SuppressWarnings("rawtypes")
//	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
//		if(hi <= lo) return;
//		int mid = lo + (hi - lo)/2;
//		sort(a, aux, lo, mid);
//		sort(a, aux, mid+1, hi);
//		merge(a, aux, lo, mid, hi);
//	}
//	
//	@SuppressWarnings("rawtypes")
//	public void sort(Comparable[] a) {
//		Comparable[] aux = new Comparable[a.length];
//		sort(a, aux, 0, a.length-1);
//	}

	@SuppressWarnings("rawtypes")
	public void sort(Comparable<X>[] xs, int from, int to) {
		// TODO Auto-generated method stub
		Comparable[] aux = new Comparable[xs.length];
		sort(xs, aux, from, to);
	}
	
	@SuppressWarnings("rawtypes")
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if(hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}
	
	@SuppressWarnings({ "rawtypes" })
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for(int k = lo; k < hi; k++) {
			//System.out.println("Lenth of aux : "+ aux.length+" a: "+ a.length+ " K : "+ k);
			aux[k] = a[k];
		}
		int i = lo, j = mid+1;
		for(int k = lo; k < hi; k++) {
			//System.out.println("----------------");
			//System.out.println("Lenth of aux : "+ aux.length+" a: "+ a.length);
			//System.out.println("K : "+k+" J : "+j+" I : "+i) ;
			if(i > mid) 					a[k] = aux[j++];
			else if(j >= hi) 				a[k] = aux[i++];
			else if(less(aux[j], aux[i])) 	a[k] = aux[j++];
			else 							a[k] = aux[i++];
		}
	}
	
}
