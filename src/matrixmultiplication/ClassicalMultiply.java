/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixmultiplication;

/**
 * Class Description: ClassicalMultiply
 * using three loops
 */
public class ClassicalMultiply implements MatrixMultiplication 
{

	public int [][]  multiplication(int A [][], int B[][],int size)
	{
		int[][] result = new int[size][size];
		for (int i = 0; i<size; i++) {
			for (int j=0; j<size; j++) {
					for (int k=0; k<size; k++) {
							result[i][j] += A[i][k] * B [k][j];
				}
			}
		}
		return result;
	}

}