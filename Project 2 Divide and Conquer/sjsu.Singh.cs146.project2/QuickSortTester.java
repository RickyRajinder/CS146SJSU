import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
    My own test class
 */
public class QuickSortTester {

    public static int duplicatesCheck(int arr[]){
        int count = 0;
       for (int i = 0; i < arr.length; i++){
           for (int j = i+1; j < arr.length; j++){
               if (arr[i] == arr[j] && i!=j){
                   count++;
               }
           }
       }
       return count;
    }

    public static int randomInRange(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static void fill(int input[]){
        for (int i = 0; i < input.length; i++){
            input[i] = randomInRange(0, input.length*100);
        }
    }

    @Test
    public void testQS1(){
        int arr[] = new int[1000000];
        fill(arr);
        long before = System.currentTimeMillis();
        QuickSort1.sort(arr);
        long after = System.currentTimeMillis();
        long duration = after - before;
        System.out.println("Time to sort: " + duration + " milliseconds");
        //System.out.println(Arrays.toString(arr));
        assert (QuickSort1.isSorted(arr) && (duplicatesCheck(arr) < arr.length/4));{
            System.out.println("Successfully sorted");
        }
    }

    @Test
    public void testQS2(){
        System.out.println("Working...");
        int arr[] = new int[1000000];
        fill(arr);
        long before = System.currentTimeMillis();
        QuickSort2.sort(arr);
        long after = System.currentTimeMillis();
        long duration = after - before;
        System.out.println("Time to sort: " + duration + " milliseconds");
        //System.out.println(Arrays.toString(arr));
        assert (QuickSort1.isSorted(Arrays.copyOf(arr, arr.length/2)) && duplicatesCheck(arr) < arr.length/4);{
            System.out.println("Successfully sorted");

       }
    }

    @Test
    public void testQS3(){
        int arr[] = new int[10000];
        fill(arr);
        long before = System.currentTimeMillis();
        QuickSort3.sort(arr);
        long after = System.currentTimeMillis();
        long duration = after - before;
        System.out.println("Time to sort: " + duration + " milliseconds");
        System.out.println(Arrays.toString(arr));
        assert (QuickSort1.isSorted(arr) && duplicatesCheck(arr) < arr.length/4);{
            System.out.println("Successfully sorted");

        }
    }


}
