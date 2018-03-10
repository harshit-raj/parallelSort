package edu.info6205.parallelSort;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	ArrayList<CSVData> listData = new ArrayList<CSVData>();

    public static void main(String[] args) {
    	if (args.length>0) ParSort.cutoff = Integer.parseInt(args[0]);
//    	Main mn = new Main();
//    	mn.run();
//    	
//    	
    	if (args.length>0) ParSort.cutoff = Integer.parseInt(args[0]);
        Random random = new Random(0L);
        int[] array = new int[2000];
        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
        System.out.println("--------------------Presorted array--------------------");
        //for(int x: array) System.out.println(x);
        
        ParSort.sort(array, 0, array.length);
        System.out.println("---------------------Sorted  array---------------------");
        
        FileHandler<CSVData> fh = new FileHandlerImpl_CSV<>();
        for(int x: array) System.out.println(x);
        if (array[0]==11) System.out.println("Success!");
    }
    
    
    
    
    private void run() {
    	//run test
    	double startTime = 0;
        double endTime = 0;
        int warmUpRun = 10;
        int arraySize = 2000;
        boolean startTiming = false;
        boolean secondLoop = false;
        boolean firstLoop = true;
        double averageTime = 0;
        int testCount = 1;
        
        
        
        
        for(int i=8; i<=8; i+=100) {                  //outermost loop
        	
        	ParSort.cutoff=2;
        	System.out.println("Cutoff is -------"+ i);
        	for(int j = 0;j<testCount;j++ ) {
        		int[] array = generateArray(i);
        		startTime = System.nanoTime();
        		ParSort.sort(array, 0, array.length);
        		endTime = System.nanoTime();
        		if(j > warmUpRun) {
        			averageTime += (endTime - startTime);
        		}
        	}
        	
    		averageTime /= (testCount-warmUpRun);
        	averageTime /= 1000000; //converting to millisecons
        	System.out.println("Time take -------"+ averageTime);
        	CSVData data = new CSVData(arraySize, averageTime, ParSort.cutoff);
        	listData.add(data);
        }
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
    
    private int[] generateArray(int size) {
    	Random random = new Random(0L);            	
		int[] array = new int[size];
        for (int j = 0; j < array.length; j++) array[j] = random.nextInt(10000);
        return array;
    }
    
    private boolean testSort() {
    	ParSort.cutoff = 1000;
        
    	Random random = new Random(0L);
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
        //System.out.println("--------------------Presorted array--------------------");
        for(int x: array) System.out.println(x);
        
        ParSort.sort(array, 0, array.length);
        //System.out.println("---------------------Sorted  array---------------------");
        
        //FileHandler<CSVData> fh = new FileHandlerImpl_CSV<>();
        for(int x: array) System.out.println(x);
        if (array[0]==11) {
        	System.out.println("Sort tested Correctly");
        	return true;
        }
        return false;
    }




	private static void writeToCSV(ParSort ps) {
    	for(int i=100; i<=10000; i+=10) {
    		ps.cutoff = i;
    		
    	}
    }
}
