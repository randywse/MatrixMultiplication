/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixmultiplication;

/**
 * Class Description: DCMultiply is the class that implments the matrix multiplication recursively
 */
public class DCMultiply implements MatrixMultiplication {
	public int[][] multiplication(int A [][], int B[][],int size)
	{
		//System.out.println("DCM matrix " + size + "*" +size);
	
		int result[][] = new int[size][size];
		
		if (size == 2) {
					
			//result[0][0] = A[0][0] * B[0][0];
			ClassicalMultiply CM = new ClassicalMultiply();
			
			result = CM.multiplication(A, B, size);
		}
		else {
			result = RecursiveMultiply(size, A, B);
	}
	return result;
}

private int[][] RecursiveMultiply(int size, int[][] A, int[][] B) {
	//Matrix A
	int A11[][] = new int[size/2][size/2];
	int A12[][] = new int[size/2][size/2];
	int A21[][] = new int[size/2][size/2];
	int A22[][] = new int[size/2][size/2];
	divideMatrix(A, A11, 0, 0);
	divideMatrix(A, A12, 0, size/2);
	divideMatrix(A, A21, size/2, 0);
	divideMatrix(A, A22, size/2, size/2);

	//Matrix B
	int B11[][] = new int[size/2][size/2];
	int B12[][] = new int[size/2][size/2];
	int B21[][] = new int[size/2][size/2];
	int B22[][] = new int[size/2][size/2];
	divideMatrix(B, B11, 0, 0);
	divideMatrix(B, B12, 0, size/2);
	divideMatrix(B, B21, size/2, 0);
	divideMatrix(B, B22, size/2, size/2);

	//Recursive process
	
	int C11[][] = conquerMatrix(multiplication(A11, B11,size/2), multiplication(A12, B21,size/2));
	int C12[][] = conquerMatrix(multiplication(A11, B12,size/2), multiplication(A12, B22,size/2));
	int C21[][] = conquerMatrix(multiplication(A21, B11,size/2), multiplication(A22, B21,size/2));
	int C22[][] = conquerMatrix(multiplication(A21, B12,size/2), multiplication(A22, B22,size/2));

	int result[][] = new int[size][size];
	result = combine(C11, C12, C21, C22);
	return result;
}


private int[][] combine(int[][] C11, int[][] C12, int[][] C21, int[][] C22) {
	int size = C11.length *2;
	int c_size = C11.length;
	
	int result[][] = new int [size][size];
	for (int i = 0; i<c_size; i++) {
		for(int j=0; j<c_size; j++) {
			result[i][j] = C11[i][j];
		}
	}
	for (int i = 0; i<c_size; i++) {
		for(int j=0; j<c_size; j++) {
			result[i][size/2+j] = C12[i][j];
		}
	}
	for (int i = 0; i<c_size; i++) {
		for(int j=0; j<c_size; j++) {
			result[size/2+i][j] = C21[i][j];
		}
	}
	for (int i = 0; i<c_size; i++) {
		for(int j=0; j<c_size; j++) {
			result[size/2+i][size/2+j] = C22[i][j];
		}
	}
	return result;
}

private int[][] conquerMatrix(int[][] A, int[][] B) {
	int size = A.length;	
	int [][] result = new int [size][size];
	for (int i=0; i<size; i++) {
		for(int j=0; j<size; j++) {
			result [i][j] = A[i][j] + B[i][j];
		}
	}
	return result;
}

private void divideMatrix(int[][] parent, int[][]  child, int parentRow, int parentCol) {
	int size =child.length;
	for (int i=0, row = parentRow; i<size; i++, row++) {
		for(int j=0, col= parentCol; j<size; j++, col++) {
			child[i][j] = parent [row][col];
		}
	}
}
}
