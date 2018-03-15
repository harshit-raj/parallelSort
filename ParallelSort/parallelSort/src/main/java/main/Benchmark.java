package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

import filehandler.FileHandler;
import filehandler.FileHandlerImpl_CSV;
import main.sort.InsertionSort;
import main.sort.SelectionSort;
import main.sort.ParSort;
import main.sort.MergeSort;
import main.sort.Sort;

public class Benchmark<T> {
	private static ArrayList<CSVData> listData = new ArrayList<CSVData>();
	public static int tcount = 1;
	public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

    public double run(T t, int n) {
        double startTime = 0;
        double endTime = 0;
        int warmUpRun = 10;
    	for(int i = 0; i <= n + warmUpRun; i++) {
    		if(i == warmUpRun) {
        		startTime = System.nanoTime();
        	}
        	f.apply(t);
        }
    	endTime = System.nanoTime();
    	return (endTime - startTime)/(1000000*n);  // divide the nanoseconds by 1,000,000 to get milliseconds, then divide by n to get mean value of timing
    }

    private final Function<T, Void> f;

    public static void main(String[] args) {
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        int arrayLength = 10000;
        Integer[] randomArray = new Integer[arrayLength];
//        Integer[] ascendArray = new Integer[arrayLength];
//        Integer[] descendArray = new Integer[arrayLength];
        Integer[] copiedArray = new Integer[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++) randomArray[i] = random.nextInt(arrayLength+1); // TODO populate the array with real random data        
//        for (int i = 0; i < arrayLength; i++) ascendArray[i] = i;
//        for (int i = 0; i < arrayLength; i++) descendArray[i] = arrayLength - i;
        
        
        int n = 200;
     // TODO You need to apply doubling to n
        
        for(int i = 0; i < randomArray.length; i++) copiedArray[i] = randomArray[i];
        //Time vs. Cutoff
        for(int i = 1000; i < arrayLength/2; i+=50) {
        	System.out.println("cutoff is: " + i);
        	benchmarkSort(copiedArray, arrayLength, "ParallelSort", new ParSort<>(), m, i);
        }  
        //Time vs. Array Size
//        for(int i = 3000; i < 10000; i+=10) {
//        	System.out.println("Array size is: " + i);
//        	benchmarkSort(copiedArray, i, "ParallelSort", new ParSort<>(), m, 500);
//        }
        //Time vs. # of Threads
//        for(int i = 1; i <= 1024; i*=2) {
//        	tcount = i;
//        	System.out.println("Number of thread is: " + i);
//        	benchmarkSort(copiedArray, arrayLength, "ParallelSort", new ParSort<>(), m, 500);
//        }
        
//        benchmarkSort(copiedArray, arrayLength, "ParallelSort", new ParSort<>(), m, 10);
//        while(n <= 2000) {         	
//            System.out.println("Random Array Result");
//            for(int i = 0; i < randomArray.length; i++) copiedArray[i] = randomArray[i];
//            benchmarkSort(copiedArray, n, "SelectionSort", new SelectionSort<>(), m);
//            for(int i = 0; i < randomArray.length; i++) copiedArray[i] = randomArray[i];
//            benchmarkSort(copiedArray, n, "InsertionSort", new InsertionSort<>(), m);
//            ParSort.cutoff=100;
//            for(int i = 0; i < randomArray.length; i++) copiedArray[i] = randomArray[i];
//            benchmarkSort(copiedArray, n, "ParallelSort", new ParSort<>(), m);
//            for(int i = 0; i < randomArray.length; i++) copiedArray[i] = randomArray[i];
//            benchmarkSort(copiedArray, n, "MergeSort", new MergeSort<>(), m);
//            
//          
//            System.out.println("Ascending Array Result");
//            for(int i = 0; i < ascendArray.length; i++) copiedArray[i] = ascendArray[i];
//            benchmarkSort(copiedArray, n, "SelectionSort", new SelectionSort<>(), m); 
//            for(int i = 0; i < ascendArray.length; i++) copiedArray[i] = ascendArray[i];
//            benchmarkSort(copiedArray, n, "InsertionSort", new InsertionSort<>(), m);
//           
//            System.out.println("Descending Array Result");
//            for(int i = 0; i < descendArray.length; i++) copiedArray[i] = descendArray[i];
//            benchmarkSort(copiedArray, n, "SelectionSort", new SelectionSort<>(), m); 
//            for(int i = 0; i < descendArray.length; i++) copiedArray[i] = descendArray[i];
//            benchmarkSort(copiedArray, n, "InsertionSort", new InsertionSort<>(), m);
//            System.out.println();
//            n = n * 2;
//        }
        //write to file
        System.out.println("Saving to file ");
        try {
        	FileHandler<CSVData> fh = new FileHandlerImpl_CSV<>();
            String colName = "Array Size, Run Time, Cutoff\n";
            String fileName = "Cutoff_Analysis.csv";
            fh.writecsv(colName, fileName, listData);
		} catch (Exception e) {
			System.out.println(e);
		}

    }

    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m, int cutoff) {
        Function<Integer, Void> sortFunction = (x) -> {
            sorter.sort(xs, 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        ParSort.cutoff=cutoff;
        double x = bm.run(n, m);
        System.out.println(name + ": " + x + " millisecs for n = " + n);
        //add to arraylist
        CSVData csvData = new CSVData(n, x, cutoff);
        listData.add(csvData);
    }
}
