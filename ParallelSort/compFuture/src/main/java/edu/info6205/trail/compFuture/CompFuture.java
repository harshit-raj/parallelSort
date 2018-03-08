/**
 * 
 */
package edu.info6205.trail.compFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author harsh
 *
 */
public class CompFuture {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		
		CompletableFuture<String> completableFuture= new CompletableFuture<String>();
		completableFuture.complete("Result....");
		String result = "";
		try {
			result = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);
		
	
		
		/*	CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
			   try {
			       TimeUnit.SECONDS.sleep(1);
			   } catch (InterruptedException e) {
			       throw new IllegalStateException(e);
			   }
			   return "ABC";
			});
		*/
	/*	CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
			   return "Hello " + name;
			});
		
		try {
			System.out.println(greetingFuture.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		/*
		whatsYourNameFuture.thenAccept(name -> {
			   System.out.println("Hello " + name); 
			});
			*/

	}

}
