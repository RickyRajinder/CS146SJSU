import java.util.Arrays;

/*
    Quicksort using the last element of the array as the pivot.
    O(nlgn) expected, O(n^2) worst-case
 */
public class QuickSort1 {

    public static int comparisons = 0;
    public static boolean isSorted(int input[]){
        for (int i = 0; i < input.length - 1; i++){
            if (input[i] > input[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void sort(int[] input){
        if (input == null || input.length <= 1 || isSorted(input)){
            return;
        }
        if (input.length <= 50){
            InsertionSort.sort(input);
        }
        else {
            quickSort(input, 0, input.length-1);
        }
    }
    public static void swap(int input[], int x, int y){
        int temp = input[x];
        input[x] = input[y];
        input[y] = temp;
    }

    public static int partition(int input[], int p, int r, int pivotIndex){
        int pivot = input[pivotIndex];
        int i = p;
        for (int j = p; j < r; j++){
           comparisons++;
            if (input[j] <= pivot){
                swap(input, i, j);
                i++;
            }
        }
        input[r] = input[i];
        input[i] = pivot;
        return i;
    }

    public static int getComparisons(){
        return comparisons;
    }

    private static void quickSort(int[] input, int p, int r){
        if (isSorted(input)){
            return;
        }
        if ( p < r){
            int index = partition(input, p, r, r);
            quickSort(input, p, index-1);
            quickSort(input, index+1, r);
            }
    }

    public static void main(String a[]){
        int[] input = {24,2,46,20,56,75,2,56,99,53,12};
        QuickSort1.sort(input);
        for(int i:input){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println(getComparisons());
    }
}
