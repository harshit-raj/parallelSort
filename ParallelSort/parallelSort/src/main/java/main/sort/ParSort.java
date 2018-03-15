package main.sort;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import main.Benchmark;

public class ParSort<X> implements Sort<X> {
	ThreadGroup tGroup = new ThreadGroup("ms");
//	public static int cores;
	//public  static ExecutorService eService = Executors.newFixedThreadPool(200);
	public static int thread = 0;
//	public ExecutorService eService= /*new ForkJoinPool(4);*/ Executors.newFixedThreadPool(200);
//	public ForkJoinPool eService = new ForkJoinPool(200);
	
	public static int recusionCount = 0;
	public static int recursionLimit = 1000;
    public static int cutoff = 1000;
    
    @SuppressWarnings("unchecked")
	public void sort(Comparable<X>[] array, int from, int to) {
    	int size = to - from;
        int mid = from +size/2;
    	if (size <= cutoff) {
    		Arrays.sort(array, from, to);
    		//eService.shutdown();
    	}
    	
        else {
//        	System.out.println("First split");
            CompletableFuture<Comparable<X>[]> parsort1 = parsort(array, from, mid) ; //called parasort on first half
//            System.out.println("Second split");
            CompletableFuture<Comparable<X>[]> parsort2 = parsort(array, mid,to); // called parasort on second half
            //callback when parasort 1 completes
            CompletableFuture<Comparable<X>[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
//                        System.out.println("Merging-----------");
                        Comparable<X>[] result = new Comparable[xs1.length + xs2.length];
                        int x1 = 0, x2 = 0, rs = 0;
                        int lxs1 = xs1.length, lxs2 = xs2.length;
                        //merging
                        while(x1<lxs1 && x2< lxs2) {
                        	int comp = xs1[x1].compareTo((X) xs2[x2]);
                        	
                        	if(comp == -1|comp == 0) {
                        		result[rs] = xs1[x1];
                        		x1++;
                        	}
                        	else {
                        		result[rs] = xs2[x2];
                        		x2++;
                        	}
                        	rs++;
                        }
                        
                        while(x1<lxs1) {
                        	result[rs++] = xs1[x1++];
                        }
                        
                        while(x2<lxs2) {
                        	result[rs++] = xs2[x2++];
                        }
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {
            	for (int i = 0; i < result.length; i++) {
					array[i] = result[i];
				}
            }); 
            parsort.join();
        }
    	//eService.shutdown();
    }

    @SuppressWarnings("unchecked")
	private CompletableFuture<Comparable<X>[]> parsort(Comparable<X>[] array, int from, int to) {
        //recusionCount++;
        //System.out.println("Recursion Count "+recusionCount);
//    	System.out.println("CompFuture called");
   // 	thread++;
		return CompletableFuture.supplyAsync(
                () -> {
                	//System.out.println("from : "+from+ "to : "+to+ "to - from : "+(to-from));
                	Comparable<X>[] result = new Comparable[to  - from];
                	int i = 0;
                	//result = Arrays.copyOfRange(array, from,to);
                	while(i < result.length) {
                		result[i] = array[from+i];
                		i++;
                	}
                	//call sort again with the smaller array
//                	for(Comparable<X> p:result) System.out.print(p + " ");
//                	System.out.println();
                	ParSort<X> par = new ParSort<>();
                    par.sort(result,0,result.length);

                	
                	return result;
                }
        );
    }

//	@Override
//	public void sort(Comparable<X>[] xs, int from, int to) {
//		// TODO Auto-generated method stub
//		
//	}
}
