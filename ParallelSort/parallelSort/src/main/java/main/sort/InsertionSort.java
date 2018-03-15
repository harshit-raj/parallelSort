package main.sort;

import static main.sort.Helper.*;

public class InsertionSort<X> implements Sort<X> {
    @Override
    public Comparable<X>[] sort(Comparable<X>[] xs) {
        // TODO implement selection sort
    	int N = xs.length;
    	for(int i = 0; i < N; i++) {
    		for(int j = i; j > 0; j--) {
    			if(less(xs[j], xs[j-1])) {
    				swap(xs, j-1, j);
    			}
    			else break;
    		}
    	}
    	return xs;
    }

	@Override
	public void sort(Comparable<X>[] xs, int from, int to) {
		// TODO Auto-generated method stub
		for(int i = from; i < to; i++) {
			for(int j = i; j > from; j--) {
				if(less(xs[j], xs[j-1])) {
					swap(xs, j, j-1);
				}
				else break;
			}
		}
	}
}
