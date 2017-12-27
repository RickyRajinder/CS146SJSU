/**
 * JUnit tests for checking and comparing the result of regular O(n^3)
 * matrix multiplication algorithm with Strassen's algorithm
 */
import org.junit.Before;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class MatrixTest {

    private int N = 4096;
    private double [][] array1 = new double[N][N], array2 = new double[N][N];
    private Matrix A = new Matrix(array1), B = new Matrix(array2);
    private Matrix productRegularResult = new Matrix(array1), productStrassenResult = new Matrix(array2);


    @Before
    public void setUp() throws Exception{
    }

    /**
     * compare result matrices of regular multiplication method
     * and Strassen multiplication method
     */
    @Test
    public void testProductCompare(){
        System.out.println("Working...");
        A = Matrix.random(array1);
        B = Matrix.random(array2);
        System.out.println("N = " + N);
        double starttime = System.nanoTime();
        productRegularResult = new Matrix((A.productRegular(B.get2DArray())).get2DArray());
        double duration = (System.nanoTime() - starttime)/1000000000;
        System.out.println("Time for regular multiplication method: " + duration + " seconds");
        starttime = System.nanoTime();
        productStrassenResult = new Matrix((A.productStrassen(B.get2DArray())).get2DArray());
        duration = (System.nanoTime() - starttime)/1000000000;
        System.out.println("Time for Strassen's multiplication method: " + duration + " seconds");
        for (int i = 0; i < N; i++) {
            assertArrayEquals(productRegularResult.get2DArray()[i], productStrassenResult.get2DArray()[i], 0.00001);
        }
    }

    @Test
    public void testProductRegular() {
        //expected output
        double[][] expected = {{96.0, 94.0, 81, 128}, {144, 117, 112, 162}, {132, 112, 101, 152}, {112, 86, 87, 130}};

        //input 2D arrays
        double[][] array1 = {{2, 4, 5, 7}, {6, 7, 2, 8}, {4, 6, 3, 9}, {8, 4, 1, 5}};
        double[][] array2 = {{6, 4, 5, 8}, {8, 7, 8, 8}, {2, 6, 5, 9}, {6, 4, 2, 5}};

        Matrix m1 = new Matrix(array1);

        long starttime = System.nanoTime();
        productRegularResult = new Matrix(m1.productRegular(array2).get2DArray());
        long end = System.nanoTime();
        long duration = (end - starttime);
        for (int i = 0; i < N; i++) {
            assertArrayEquals(expected[i], productRegularResult.get2DArray()[i], 0.00001);
        }
        System.out.println("Time for regular multiplication method: " + duration + " nanoseconds");
    }

    @Test
    public void testProductStrassen(){
        double[][] expected = {{96,94,81,128}, {144,117,112,162},{132,112,101,152},
                {112,86,87,130}};

        double[][] array1 = {{2,4,5,7}, {6,7,2,8}, {4,6,3,9}, {8,4,1,5}};
        double[][] array2 = {{6,4,5,8}, {8,7,8,8}, {2,6,5,9}, {6,4,2,5}};

        Matrix m1 = new Matrix(array1);

        long starttime = System.nanoTime();
        productStrassenResult = new Matrix(m1.productStrassen(array2).get2DArray());
        long end = System.nanoTime();
        long duration = (end - starttime);
        for (int i = 0; i < N; i++) {
            assertArrayEquals(expected[i], productStrassenResult.get2DArray()[i], 0.00001);
        }
        System.out.println("Time for Strassen's multiplication method: " + duration + " nanoseconds");
    }

    @org.junit.jupiter.api.Test
    public void testAdd() {
        double[][] array1 = {{2, 4, 5, 7}, {6, 7, 2, 8}, {4, 6, 3, 9}, {8, 4, 1, 5}};
        double[][] array2 = {{6, 4, 5, 8}, {8, 7, 8, 8}, {2, 6, 5, 9}, {6, 4, 2, 5}};

        double[][] expected = {{8,8,10,15}, {14,14,10,16}, {6,12,8,18}, {14,8,3,10}};
        double[][] result = Matrix.add(array1, array2);
        for (int i = 0; i < N; i++) {
            assertArrayEquals(expected[i], result[i], 0.00001);
        }
    }

    @Test
    public void testSubtract(){
        double[][] array1 = {{2, 4, 5, 7}, {6, 7, 2, 8}, {4, 6, 3, 9}, {8, 4, 1, 5}};
        double[][] array2 = {{6, 4, 5, 8}, {8, 7, 8, 8}, {2, 6, 5, 9}, {6, 4, 2, 5}};

        double[][] expected = {{-4,0,0,-1},{-2,0,-6,0},{2,0,-2,0},{2,0,-1,0}};
        double[][] result = Matrix.subtract(array1, array2);
        for (int i = 0; i < N; i++) {
            assertArrayEquals(expected[i], result[i], 0.00001);
        }
    }
}
