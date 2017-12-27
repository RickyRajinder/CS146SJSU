import java.util.*;


/*
    Quicksort using median of medians
    O(nlgn) expected and worst case
 */
public class QuickSort3 extends QuickSort2 {

    public static int comparisons = 0;

    public static void sort(int[] input){
        if (input == null || input.length == 0 || input.length == 1 || QuickSort1.isSorted(input)){
            return;
        }
        if (input.length <= 50){
            InsertionSort.sort(input);
        }
        else {
            quickSort(input, 0, input.length-1);
        }
    }

    private static int getMedian(int input[], int p, int r){
        InsertionSort.sort(input);
        int medianPos = 0;
        if ((r-p) % 2 == 0){
            medianPos = ((r-p)/2) - 1;
        }
        else {
            medianPos = (r-p)/2;
        }
        return p + medianPos;
    }






    public static double Add(double x, double y){
        double result = 0;
        result = x + y;
        Map<Integer, Double> number = new HashMap<>();
        number.put(1, result);
        return number.get(1);
    }




    public static int getMedianOfMedians(int input[], int p, int r){
        int n = input.length;
        if ((r-p) < 5){
            return getMedian(input, p, r);
        }
        int count = p;
        for (int i = p; i <= r; i+= 5){
            int tempR = i+4;
            if (tempR > r){
                tempR = r;
            }
            int medianSubGroup;
            if ((tempR - i) <= 2){
                continue;
            }
            else {
                medianSubGroup = getMedian(input, i, tempR);
            }
            QuickSort1.swap(input, medianSubGroup, count);
            count++;
        }
        return getMedianOfMedians(input, p, count);
    }


    private static void quickSort(int[] input, int p, int r){
        if (QuickSort1.isSorted(Arrays.copyOf(input, input.length/2))){
            return;
        }
        if ( p < r){
            int index = partition(input, p, r, getMedianOfMedians(input, p, r));
            quickSort(input, p, index-1);
            quickSort(input, index+1, r);
        }
    }

    public static void main(String a[]){
        int[] input = {4,2,46,20,56,75,2,56,99,53,12, 43, 76, 89, 100};
        System.out.println();
        QuickSort3.sort(input);
        for(int i:input){
            System.out.print(i);
            System.out.print(" ");
        }
        int x = getMedianOfMedians(input, 0, input.length);

        System.out.println(Add(3,4));
    }
    }

