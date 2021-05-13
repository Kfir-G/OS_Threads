#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h> // for Dynamic Memory
#include <windows.h>
#define M 3 //Rows in A and C
#define K 2 //Columns in A; Rows in B
#define N 3 //Columns in B and C
// Source Matrix A
int A[M][K] = { { 1, 4 }, { 2, 5 }, { 3, 6 } };

//Source Matrix B 
int B[K][N] = { { 8, 7, 6 }, { 5, 4, 3 } };

//Destination Matrix, should contain the Matrix //Multiplication Result: C = A * B
int C[M][N];

typedef struct ThrPrm
{
	int row;
	int col;
}ThrPrm;

void printMatrix(int[][N], int rows, int cols);
DWORD WINAPI calcMatrixElement(PVOID Param);

void main(char *argv[]) {
/*
Param pointer - allocate dynamically a param for each Thread - initialise param and send it to Thread function.
 Remember - Thread function should free the memory when finishes with it!
*/
	ThrPrm *param; //pointer to structure
	HANDLE threadsArr[M * N]; // Array of Threads!
	DWORD ThreadId;


	int threadIndex = 0;
	for (int i = 0; i < M; i++)
		for (int j = 0; j < N; j++)
		{
			param = (ThrPrm *)malloc(sizeof(ThrPrm));
			param->row = i;
			param->col = j;

			//Create Thread to serve the multiplication 
			  //of: C[i][j] = A[row i] * B[column j]
			threadsArr[threadIndex] = CreateThread(NULL, 0, calcMatrixElement,param, 0, &ThreadId);

			//Need to ensure Thread was created 
			  //successfully, otherwise - Error Handling and 
			  //exit!
			if (threadsArr[threadIndex] == NULL)
			{
				printf("main::Unexpected Error in Thread %d Creation\n", threadIndex);
					return 1;
			}

			threadIndex++; //Don't forget to increment threadIndex to the next Thread in array
		}

			WaitForMultipleObjects(M * N, threadsArr, TRUE, INFINITE);
			printMatrix(C, M, N);
}

void printMatrix(int matrix[][N], int rows, int cols) {
	printf("All Threads are Completed!\n");
	for (int i = 0; i < rows; i++)
	{
		for (int j = 0; j < cols; j++)
		{
			printf("%d	", matrix[i][j]);
		}
		printf("\n");
	}

}
DWORD WINAPI calcMatrixElement(PVOID Param) {
		ThrPrm prm = *(ThrPrm *)Param;
	
		for (int i = 0; i < K; i++)
		{
			C[prm.row][prm.col] += A[prm.row][i] * B[i][prm.col];
		}
		return 0;

}