package edu.info6205.parallelSort;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        if (args.length>0) ParSort.cutoff = Integer.parseInt(args[0]);
        Random random = new Random(0L);
        int[] array = new int[900000];
        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
        System.out.println("--------------------Presorted array--------------------");
        //for(int x: array) System.out.println(x);
        
        ParSort.sort(array, 0, array.length);
        System.out.println("---------------------Sorted  array---------------------");
        //for(int x: array) System.out.println(x);
        if (array[0]==11) System.out.println("Success!");
    }
}
