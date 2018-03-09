package edu.info6205.parallelSort;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	ArrayList<CSVData> listData = new ArrayList<CSVData>();

    public static void main(String[] args) {
    	if (args.length>0) ParSort.cutoff = Integer.parseInt(args[0]);
    	Main mn = new Main();
    	mn.run();
    	
//    	
//    	if (args.length>0) ParSort.cutoff = Integer.parseInt(args[0]);
//        Random random = new Random(0L);
//        int[] array = new int[1000000];
//        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
//        System.out.println("--------------------Presorted array--------------------");
//        for(int x: array) System.out.println(x);
//        
//        ParSort.sort(array, 0, array.length);
//        System.out.println("---------------------Sorted  array---------------------");
//        
//        FileHandler<CSVData> fh = new FileHandlerImpl_CSV<>();
//        for(int x: array) System.out.println(x);
//        if (array[0]==11) System.out.println("Success!");
    }
    
    
    
    
    private void run() {
    	//run test
    	double startTime = 0;
        double endTime = 0;
        int warmUpRun = 500;
        int arraySize = 2000;
        boolean startTiming = false;
        for(int i=1; i<=1000; i++) {
        	for(int k=0; k<1000; k++) {
        		if(i == warmUpRun) {
            		startTime = System.nanoTime();
//            		System.out.println("start timing---------------");
            		startTiming = true;
            	}
        		Random random = new Random(0L);            	
        		int[] array = new int[arraySize];
                for (int j = 0; j < array.length; j++) array[j] = random.nextInt(10000);
            	ParSort.cutoff = i;
        		ParSort.sort(array, 0, array.length);
//        		System.out.println(i+ ", " + k);
        	}
        	endTime = System.nanoTime();        	
        	double runtime = (endTime - startTime)/(1000000*(1000)); // divide the nanoseconds by 1,000,000 to get milliseconds, then divide by n to get mean value of timing
        	if(startTiming == true) {
//        		System.out.println("end timing. time is " + runtime + " cutoff is: " + i);
        		CSVData data = new CSVData(arraySize, runtime, ParSort.cutoff);
            	listData.add(data);
        	}
        }
    	
    	
    	
//    	double startTime = 0;
//        double endTime = 0;
//        int warmUpRun = 500;
//        int arraySize = 2000;
//        for(int i=400; i<=5000; i+=10) {
//        	for(int k=0; k<1000; k++) {
//        		if(i == warmUpRun) {
//            		startTime = System.nanoTime();
//            		System.out.println("start timing---------------");
//            	}
//        		Random random = new Random(0L);            	
//        		int[] array = new int[arraySize];
//                for (int j = 0; j < array.length; j++) array[j] = random.nextInt(10000);
//            	ParSort.cutoff = i;
//        		ParSort.sort(array, 0, array.length);
//        	}
//        	endTime = System.nanoTime();        	
//	    	double runtime = (endTime - startTime)/(1000000*(1000)); // divide the nanoseconds by 1,000,000 to get milliseconds, then divide by n to get mean value of timing
//	    	System.out.println("end timing. time is " + runtime);
//	    	CSVData data = new CSVData(arraySize, runtime, ParSort.cutoff);
//	    	listData.add(data);
//    	}
//        for(CSVData d:listData) {
//        	System.out.println(d.getRuntime());
//        }
        try {
        	FileHandler<CSVData> fh = new FileHandlerImpl_CSV<>();
            String colName = "Array Size, Run Time, Cutoff\n";
            String fileName = "Cutoff_Analysis.csv";
            fh.writecsv(colName, fileName, listData);
		} catch (Exception e) {
			System.out.println(e);
		}
        
		
        //start the loop
    		//set values for parasort
    			//generate the array
    			//set cutoff value
    			//do warmup runs
    			//start timer and run
    			//stop timer and get run time
    			//new instance of CSVdata and give it all values
    			//add new instance to arraylist
    		//call file hanler and give it the arraylist and other required stuff	
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
