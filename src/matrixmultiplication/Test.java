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
 * testing
 */

public class Test
{

	public static void main(String[] args) {
		
		MatrixMultiplication CM = new ClassicalMultiply();
		MatrixMultiplication DCM = new DCMultiply();
		MatrixMultiplication SM = new StrassenMultiply();
		int size = 4096;
		
		MatrixGenerator mGenerator= new MatrixGenerator();
	
		int A[][] = mGenerator.MatrixGenerator(size);
		int B[][] = mGenerator.MatrixGenerator(size);
		
			
		
		long startTime_1 = System.nanoTime();
		int [][] cm_result = CM.multiplication(A, B,size);
		long endTime_1 = System.nanoTime();
		long totalTime_1 =endTime_1 - startTime_1;
		System.out.format("For Size = "+size+", the time cost is %.11f seconds\n",totalTime_1*0.000000001);
		
		System.out.println("Matrix of "+size+"x"+size + "\nBy Divide-and-conquer:");
		long startTime_2 = System.nanoTime();
		int [][] dmc_result = DCM.multiplication(A, B,size);
		long endTime_2 = System.nanoTime();
		long totalTime_2 =endTime_2 - startTime_2;
		System.out.format("For Size = "+size+", the time cost is %.11f seconds\n",totalTime_2*0.000000001);
		
		if ( If_equal(cm_result, dmc_result))
		{
			System.out.println("DCM is correct");
		}
		
		System.out.println("Matrix of "+size+"x"+size+"\nBy Strassen:");
		long startTime_3 = System.nanoTime();
		int [][] sm_result = SM.multiplication(A, B,size);
		
		long endTime_3 = System.nanoTime();
		long totalTime_3 =endTime_3 - startTime_3;
		System.out.format("For Size = "+size+", the time cost is %.11f seconds\n",totalTime_3*0.000000001);
		
		if ( If_equal(cm_result, sm_result))
		{
			System.out.println("SM is correct");
		}
		
	}
	
	private static boolean If_equal(int [][]A, int [][] B)
	{
		
		for (int i=0; i< A.length; i++)
		{
			for ( int j=0; j < A.length;j++)
			{
				if ( A[i][j] != B[i][j]) return false;
			}
		}
		return true;
	}
}

