import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    Instructor provided test class
 */
public class QuickSortInstructorTest {

    @Test
    public void testEmpty() {
        int[] array1 = new int[0];
        int[] arr2 = new int[0];
        QuickSort1.sort(array1);
        assertArrayEquals(array1, arr2);
        QuickSort2.sort(array1);
        assertArrayEquals(array1, arr2);
    }

    @Test
    public void testSorted(){
        int[] arr1 = new int[51];
        int[] arr2 = new int[51];
        int[] arr3 = new int[51];
        for (int i = 0; i < 51; i++){
            arr1[i] = i;
            arr2[i] = i;
            arr3[i] = i;
        }
        Arrays.sort(arr3);
        QuickSort1.sort(arr1);
        assertArrayEquals(arr3, arr1);

        QuickSort2.sort(arr2);
        assertArrayEquals(arr3, arr2);
    }

    @Test
    public void testReverseSorted(){
            int[] arr1 = new int[51];
            int[] arr2 = new int[51];
            int[] arr3 = new int[51];

            for (int i = 50; i > 0; i--){
                arr1[i] = i;
                arr2[i] = i;
                arr3[i] = i;
            }
            Arrays.sort(arr3);

            QuickSort1.sort(arr1);
            assertArrayEquals(arr3, arr1);

            QuickSort2.sort(arr2);
            assertArrayEquals(arr3, arr2);
    }

    @Test
    public void testSelect(){
        int[] arr1 = new int[100];
        for (int i = 0; i < 100; i++){
            arr1[i] = i;
        }

        int median = QuickSort2.randomizedSelectIndex(arr1, 0, arr1.length-1, (arr1.length-1)/2);
        System.out.println(median);

        assertEquals(49, median);
    }

    @Test
    public void setTestRandom(){
        int arr1[] = new int[100];
        for (int i = 0; i < 100; i++){
            arr1[i] = (int) Math.random()*100;
        }
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        int[] arr3 = Arrays.copyOf(arr1, arr1.length);

        Arrays.sort(arr3);

        QuickSort1.sort(arr1);
        assertArrayEquals(arr3, arr1);

        QuickSort2.sort(arr2);
        assertArrayEquals(arr3, arr2);
    }

    @Test
    public void testGetPartCount(){
        int[] arr1 = new int[100];
        for(int i = 0; i < 100; i++){
            arr1[i] = i;
        }
        QuickSort1.sort(arr1);
        long compare = QuickSort1.getComparisons();
        System.out.println("Comparisons in already sorted: " + compare);
        assertEquals(45, compare);
    }

    @Test
    public void testGetPartCountA(){
        int[] arr1 = new int[100];
        for (int i = 99; i > 0; i--){
            arr1[i] = i;
        }

        QuickSort1.sort(arr1);
        long compare = QuickSort1.getComparisons();
        System.out.println("Comparisons in already sorted: " + compare);
        assertEquals(45, compare);
    }
}
