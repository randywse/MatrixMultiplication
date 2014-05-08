/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixmultiplication;

/**
 *
 * @author Randy
 */

/**
 *Class Description: StrassenMultiply
*/
public class StrassenMultiply implements MatrixMultiplication {
	@Override
	public int[][] multiplication(int[][] A, int[][] B, int size) {
		
		int result [][] = new int[size][size];
		result = recursiveMultiply(size, A, B);
		return result;
	}


private int[][] recursiveMultiply(int size, int[][] A, int[][] B) {
	int [][] result = new int[size][size];
	if (size==2) {
		ClassicalMultiply CM = new ClassicalMultiply();
		
		result = CM.multiplication(A, B, size);
	} 
	else {
		int A11[][] = new int[size/2][size/2];
		int A12[][] = new int[size/2][size/2];
		int A21[][] = new int[size/2][size/2];
		int A22[][] = new int[size/2][size/2];
		divideMatrix(A, A11, 0, 0);
		divideMatrix(A, A12, 0, size/2);
		divideMatrix(A, A21, size/2, 0);
		divideMatrix(A, A22, size/2, size/2);

		int B11[][] = new int[size/2][size/2];
		int B12[][] = new int[size/2][size/2];
		int B21[][] = new int[size/2][size/2];
		int B22[][] = new int[size/2][size/2];
		divideMatrix(B, B11, 0, 0);
		divideMatrix(B, B12, 0, size/2);
		divideMatrix(B, B21, size/2, 0);
		divideMatrix(B, B22, size/2, size/2);

	//recursive process
		int P[][] = recursiveMultiply(size/2, addMatrix(A11, A22), addMatrix(B11, B22));
		int Q[][] = recursiveMultiply(size/2, addMatrix(A21, A22), B11);
		int R[][] = recursiveMultiply(size/2, A11, subMatrix(B12, B22));
		int S[][] = recursiveMultiply(size/2, A22, subMatrix(B21, B11));
		int T[][] = recursiveMultiply(size/2, addMatrix(A11, A12), B22);
		int U[][] = recursiveMultiply(size/2, subMatrix(A21, A11), addMatrix(B11, B12));
		int V[][] = recursiveMultiply(size/2, subMatrix(A12, A22), addMatrix(B21, B22));
	
		int C11[][] = addMatrix(subMatrix(addMatrix(P,S), T), V);
		int C12[][] = addMatrix(R, T);
		int C21[][] = addMatrix(Q, S);
		int C22[][] = addMatrix(subMatrix(addMatrix(P,R), Q), U);
	
		result = combine(C11, C12, C21, C22);

}
	return result;
}



private void divideMatrix(int[][] parent, int[][] child, int parentRow, int parentCol) {
	
	int size = child.length;
	for(int i = 0, row = parentRow; i < size; i++, row++) {
		for (int j = 0, col = parentCol; j < size; j++, col++) {
		child[i][j] = parent [row][col];
		}
	}
}

private int[][] subMatrix(int[][] A, int[][] B){
	int size = A.length;
	int result[][] = new int[size][size];
	for(int i = 0; i < size; i++) {
		for(int j = 0; j < size; j++) {
		result[i][j] = A[i][j] - B[i][j];
		}
	}
	return result;
}



private int[][] addMatrix(int A[][], int B[][]) {
	int size = A.length;
	int result[][] = new int[size][size];
	for(int i = 0; i < size; i++) {
		for(int j = 0; j < size; j++) {
		result[i][j] = A[i][j] + B[i][j];
		}
	}
	return result;
}


private int[][] combine(int[][] C11, int[][] C12, int[][] C21, int[][] C22) {
	int size = C11.length *2;
	int result[][] = new int[size][size];
	int c_size = C11.length;
	
	for(int i =0; i < c_size; i++) {
		for(int j = 0; j < c_size; j++) {
			result[i][j] = C11[i][j];
		}
	}
	for(int i =0; i < c_size; i++) {
		for(int j = 0; j < c_size; j++) {
			result[i][size/2+j] = C12[i][j];
		}
	}
	for(int i =0; i < c_size; i++) {
		for(int j = 0; j < c_size; j++) {
		result[size/2 + i][j] = C21[i][j];
		}
	}
	for(int i =0; i < c_size; i++) {
		for(int j = 0; j < c_size; j++) {
		result[size/2+i][size/2+j] = C22[i][j];
		}
	}
	
	return result;
	}
}



