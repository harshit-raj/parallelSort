package main.sort;

import static main.sort.Helper.less;
import static main.sort.Helper.swap;

public class SelectionSort<X> implements Sort<X> {
    @Override
    public Comparable<X>[] sort(Comparable<X>[] xs) {
        // TODO implement selection sort
    	int N = xs.length;
    	for(int i = 0; i < N; i++) {
    		int min = i;
    		for(int j = i + 1; j < N; j++) {
    			if(less(xs[j], xs[min])) {
    				min = j;
    			}
    		}
    		swap(xs, i, min);
    	}
    	return xs;
    }

	@Override
	public void sort(Comparable<X>[] xs, int from, int to) {
		// TODO Auto-generated method stub
		for(int i = from; i < to; i++) {
    		int min = i;
    		for(int j = i + 1; j < to; j++) {
    			if(less(xs[j], xs[min])) {
    				min = j;
    			}
    		}
    		swap(xs, i, min);
    	}
	}
}
