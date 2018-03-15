/*
 * Copyright (c) 2017. Phasmid Software
 */

package main;

import org.junit.Test;

import main.sort.ParSort;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SortTest {

    @SuppressWarnings("unchecked")
	@Test
    public void sort() throws Exception {
		Random random = new Random(0L);
		Comparable<Integer>[] array = new Comparable[3000];
		for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
		ParSort<Integer> parSort = new ParSort<>();
        parSort.sort(array, 0, array.length);

        Comparable<Integer>[] array1= new Comparable[]{93,26,11,144,55,22};
        parSort.sort(array1, 0, array1.length);
        Comparable<Integer>[] toCompare= new Comparable[]{11,22,26,55,93,144};
        assertEquals(array[0], 11);
        assertArrayEquals(array1, toCompare);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void sort1() throws Exception {
        Comparable<Integer>[] array1= new Comparable[]{93,26,11,144,55,22};
        ParSort<Integer> parSort = new ParSort<>();
        parSort.sort(array1, 0, array1.length);
        Comparable<Integer>[] toCompare= new Comparable[]{11,22,26,55,93,144};
        assertArrayEquals(array1, toCompare);
    }

}