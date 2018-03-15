package main.sort;

import static main.sort.Helper.*;

import java.util.Arrays;
import java.util.Collections;

public class QuickSort {
	@SuppressWarnings({ "rawtypes" })
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi+1;
		System.out.println("lo starts at index " + i);
		System.out.println("hi starts at index " + j);
		while(true) {
			while(less(a[++i], a[lo])) {
				System.out.println("print out index of lo " + i);
				if(i == hi) break;
			}
			while(less(a[lo], a[--j])) {
				System.out.println("print out index of hi " + j);
				if(j == lo) break;
			}
			
			if(i >= j) break;
			System.out.println("when hi is " + a[j] + " lo is " + a[i] + " swapping them ----------------------------------------");
			swap(a, i, j);
			for(int p = 0; p<a.length;p++)System.out.print(a[p] + " ");
			System.out.println();
		}
		swap(a, lo, j);
		System.out.println("now j is in place");
		for(int p = 0; p<a.length;p++)System.out.print(a[p] + " ");
		System.out.println();
		return j;
	}
	
	public void sort(Comparable[] a) {
		Collections.shuffle(Arrays.asList(a));
		System.out.println("The array is");
		for(int i=0;i<a.length;i++) System.out.print(a[i] + " ");
		System.out.println();
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) return;
		int j = partition(a, lo, hi);
		System.out.println("j is " + a[j]);
		System.out.println("start sorting left half----------");
//		for(int i=0;i<a.length;i++) System.out.print(a[i] + " ");
//		System.out.println();
		sort(a, lo, j-1);
		for(int i=0;i<a.length;i++) System.out.print(a[i] + " ");
		System.out.println();
		System.out.println("start sorting right half----------");
//		for(int i=0;i<a.length;i++) System.out.print(a[i] + " ");
//		System.out.println();
		sort(a, j+1, hi);
	}
}
