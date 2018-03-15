package edu.info6205.parallelSort;

public interface Sort<X> {

    /**
     * Generic sort method.
     *
     * @param xs sort the array xs in place
     */
    default Comparable<X>[] sort(Comparable<X>[] xs) {
        sort(xs, 0, xs.length);
		return xs;
    }

    /**
     * Generic sort method.
     *
     * @param xs   sort the array xs in place
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(Comparable<X>[] xs, int from, int to);
}
