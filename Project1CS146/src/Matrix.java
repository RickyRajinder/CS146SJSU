
public class Matrix {
    private double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] get2DArray() {
        return matrix;
    }

    /**
     * The product of two matrices is computed in O(n^3) time
     *
     * @param matrixB
     * @return the product of the constructor matix and matrixB
     */
    public Matrix productRegular(double matrixB[][]) {
        if (matrix[0].length != matrixB.length) {
            throw new IllegalArgumentException("Matrices are not compatible");
        }
        double[][] matrixC = new double[matrix.length][matrixB[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                matrixC[i][j] = 0;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    matrixC[i][j] += matrix[i][k] * matrixB[k][j];
                }
            }
        }
        return new Matrix(matrixC);
    }

    /**
     * The product of two matrices is computed using Strassen's algorithm O(lg7)
     * This	method	should	return	product	matrix.
     *
     * @param matrixB
     * @return
     */
    public Matrix productStrassen(double matrixB[][]) {
        int n = matrix.length;
        double[][] result = new double[n][n];
        if ((n % 2 != 0) && (n != 1)) {
            double[][] A1, B1, C1;
            int n1 = n++;
            A1 = new double[n1][n1];
            Matrix matrixA1 = new Matrix(A1);
            B1 = new double[n1][n1];
            C1 = new double[n1][n1];
            Matrix matrixC1 = new Matrix(C1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A1[i][j] = matrix[i][j];
                    B1[i][j] = matrixB[i][j];
                }
            }
            matrixC1 = matrixA1.productStrassen(B1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[i][j] = matrixC1.get2DArray()[i][j];
                }
            }
            return new Matrix(result);
        } else if (n == 1) {
            result[0][0] = matrix[0][0] * matrixB[0][0];
        } else {
            double[][] A11 = new double[n / 2][n / 2];
            double[][] A12 = new double[n / 2][n / 2];
            double[][] A21 = new double[n / 2][n / 2];
            double[][] A22 = new double[n / 2][n / 2];

            double[][] B11 = new double[n / 2][n / 2];
            double[][] B12 = new double[n / 2][n / 2];
            double[][] B21 = new double[n / 2][n / 2];
            double[][] B22 = new double[n / 2][n / 2];

            split(matrix, A11, 0, 0);
            split(matrix, A12, 0, n / 2);
            split(matrix, A21, n / 2, 0);
            split(matrix, A22, n / 2, n / 2);

            split(matrixB, B11, 0, 0);
            split(matrixB, B12, 0, n / 2);
            split(matrixB, B21, n / 2, 0);
            split(matrixB, B22, n / 2, n / 2);

            double[][] P1 = new Matrix(add(A11, A22)).productStrassen(add(B11, B22)).get2DArray();
            double[][] P2 = new Matrix(add(A21, A22)).productStrassen(B11).get2DArray();
            double[][] P3 = new Matrix(A11).productStrassen(subtract(B12, B22)).get2DArray();
            double[][] P4 = new Matrix(A22).productStrassen(subtract(B21, B11)).get2DArray();
            double[][] P5 = new Matrix(add(A11, A12)).productStrassen(B22).get2DArray();
            double[][] P6 = new Matrix(subtract(A21, A11)).productStrassen(add(B11, B12)).get2DArray();
            double[][] P7 = new Matrix(subtract(A12, A22)).productStrassen(add(B21, B22)).get2DArray();

            double[][] C11 = add(subtract(add(P1, P4), P5), P7);
            double[][] C12 = add(P3, P5);
            double[][] C21 = add(P2, P4);
            double[][] C22 = add(subtract(add(P1, P3), P2), P6);

            copy(C11, result, 0, 0);
            copy(C12, result, 0, n / 2);
            copy(C21, result, n / 2, 0);
            copy(C22, result, n / 2, n / 2);
        }
        return new Matrix(result);
    }

    public static double[][] add(double[][] A, double[][] B) {
        int n = A.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    public static double[][] subtract(double[][] A, double[][] B) {
        int n = A.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    private static void split(double[][] p, double[][] c, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < c.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < c.length; j1++, j2++) {
                c[i1][j1] = p[i2][j2];
            }
        }
    }

    private static void copy(double[][] c, double[][] p, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < c.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < c.length; j1++, j2++) {
                p[i2][j2] = c[i1][j1];
            }
        }
    }

    public static Matrix random(double [][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.random() * 100;
            }
        }
        return new Matrix(matrix);
    }

    public static void print(double [][] array)
    {
        int n = array.length;

        System.out.println();
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        double[][] d = new double[5][5];
        Matrix m = new Matrix(d);
        print(random(m.get2DArray()).get2DArray());
    }
}