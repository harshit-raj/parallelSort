package main.sort;

import static main.sort.Helper.*;

public class ShellSort<X> implements Sort<X>{

	@Override
	public Comparable<X>[] sort(Comparable<X>[] xs) {
		// TODO Auto-generated method stub
		int N = xs.length;
		int h = 1;
		while(h < N/3) h = h*3 - 1;
		
		while(h >= 1) {
			for(int i = h; i < N; i++) {
				for(int j = i; j >= h && less(xs[j], xs[j-h]); j -= h) {
					swap(xs, j, j-h);
				}
			}
			h = h/3;
		}
		return xs;
	}

	@Override
	public void sort(Comparable<X>[] xs, int from, int to) {
		// TODO Auto-generated method stub
		
	}

}
