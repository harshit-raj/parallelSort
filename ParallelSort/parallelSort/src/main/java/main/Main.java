package main;

import java.util.Random;

import main.sort.InsertionSort;
import main.sort.MergeSort;
import main.sort.QuickSort;
import main.sort.SelectionSort;
import main.sort.ShellSort;
import main.sort.Sort;

public class Main {
	Sort<Integer> selectionSort;
	Sort<Integer> insertionSort;
	Sort<Integer> shellSort;
	Integer[] seletionSortArray;
	Integer[] insertionSortArray;
	Integer[] shellSortArray;
	Integer[] mergeSortArray;
	Integer[] quickSortArray;
	
	public static void main(String[] args) {
		Main test = new Main();
		test.run();	
	}
	
	public Main() {
		selectionSort = new SelectionSort<>();
		insertionSort = new InsertionSort<>();
		shellSort = new ShellSort<>();
		seletionSortArray = new Integer[] {3,2,6,4,1,7,8,10};
		insertionSortArray = new Integer[500];
		Random random = new Random();
		for(int i = 0; i < 500; i++) insertionSortArray[i] = random.nextInt(2001);
		shellSortArray = new Integer[] {3,2,6,4,1,7,8,10};
		mergeSortArray = new Integer[100];
		for(int i = 0; i < 100; i++) mergeSortArray[i] = random.nextInt(101);
		quickSortArray = new Integer[] {3,2,6,4,1,7,8,10};
//		for(int i = 0; i < 100; i++) quickSortArray[i] = random.nextInt(101);
	}
	
	void run() {
//		selectionSort.sort(seletionSortArray,2,4);
//		System.out.println("Selection Sort result: ");
//		for(Integer x : seletionSortArray) {
//			System.out.print(x + " ");
//		}
//		insertionSort.sort(insertionSortArray);
//		System.out.println();
//		System.out.println("Insertion Sort result: ");
//		for(Integer x : insertionSortArray) {
//			System.out.print(x + " ");
//		}
//		shellSort.sort(shellSortArray);
//		System.out.println();
//		System.out.println("Shell Sort result: ");
//		for(Integer x : shellSortArray) {
//			System.out.print(x + " ");
//		}
//		MergeSort merge = new MergeSort();
//		merge.sort(mergeSortArray);
//		System.out.println();
//		System.out.println("Merge Sort result: ");
//		for(Integer x : mergeSortArray) {
//			System.out.print(x + " ");
//		}
		QuickSort quick = new QuickSort();
		quick.sort(quickSortArray);
		System.out.println();
		System.out.println("Quick Sort result: ");
		for(Integer x : quickSortArray) {
			System.out.print(x + " ");
		}
	}
}
