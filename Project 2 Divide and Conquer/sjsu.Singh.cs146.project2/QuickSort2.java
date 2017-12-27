import java.util.Arrays;
import java.util.Random;

/*
    Randomized quicksort using the median found by random select
    O(nlgn) worst-case and average
 */
public class QuickSort2 extends QuickSort1 {
public static int comparisons = 0;
    public static void sort(int[] input){
        if (input == null || input.length <= 1 || QuickSort1.isSorted(input)){
            return;
        }
        if (input.length <= 50){
            InsertionSort.sort(input);
        }
        else {
            quickSort(input, 0, input.length-1);
        }
    }

    public static int randomizedPartition(int input[], int p, int r){
        Random rand = new Random();
        int i;
        if (p < r){
            i = p + rand.nextInt(r-p);
        }
        else {
            i = r + rand.nextInt(p-r);
        }
        QuickSort1.swap(input, r, i);
        return QuickSort1.partition(input, p, r, r);
    }

    public static int randomizedSelectIndex(int input[], int p, int r, int i){
       if (p==r){
           return p;
       }
       if (r-p <= 100){
           InsertionSort.sort(input);
           return i;
       }
       int q = randomizedPartition(input, p, r);
       int k = q-p+1;
       if (i == k){
           return q;
       }
       else if (i < k){
           return randomizedSelectIndex(input, p, q-1, i);
       }
       else return randomizedSelectIndex(input, q+1, r, i-k);
    }

    private static void quickSort(int[] input, int p, int r){
        if (QuickSort1.isSorted(Arrays.copyOf(input, input.length/4))){
            return;
        }
        if ( p < r){
            int index = partition(input, p, r, randomizedSelectIndex(input, p, r, input.length/2));
            quickSort(input, p, index-1);
            quickSort(input, index+1, r);
        }
        else {
            InsertionSort.sort(input);
        }
    }

    public static int partition(int input[], int p, int r, int pivotIndex){
        QuickSort1.swap(input, pivotIndex, r);
        int pivot = input[r];
        int i = p;
        for (int j = p; j < r; j++){
            comparisons = comparisons++;
            if (input[j] <= pivot){
                QuickSort1.swap(input, i, j);
                i++;
            }
        }
        input[r] = input[i];
        input[i] = pivot;
        return i;
    }

    public static void main(String a[]){
        int[] input = {24,2,45,20,56,75,2,56,99,53,12};
        int y = QuickSort2.randomizedSelectIndex(input, 0, input.length-1, (input.length)/2);
        System.out.println(y);
        QuickSort2.sort(input);
        for(int i:input){
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
