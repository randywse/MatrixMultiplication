/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixmultiplication;

/**
 * Class Description: MatrixGenerator generates random numbers to the matrix
 */
public class MatrixGenerator {
	public static int[][] MatrixGenerator(int size) {
	int[][] A = new int [size][size];
	for(int i=0; i<size; i++) {
		for(int k=0; k<size; k++) {
			A[i][k] = (int)(Math.random()*10);
		}
	}
	return A;
	}

}
