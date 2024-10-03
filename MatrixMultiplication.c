#include <stdio.h>
#include <stdlib.h>
 
#define R1 2 
#define C1 2 
#define R2 2 
#define C2 2
 
void multiplyMatrix(int m1[][C1], int m2[][C2])
{
    int result[R1][C2];
 
    printf("Resultant Matrix is:\n");
 
    for (int i = 0; i < R1; i++) {
        for (int j = 0; j < C2; j++) {
            result[i][j] = 0;
 
            for (int k = 0; k < R2; k++) {
                result[i][j] += m1[i][k] * m2[k][j];
            }
 
            printf("%d\t", result[i][j]);
        }
 
        printf("\n");
    }
}
 
int main()
{
    int m1[R1][C1] = { { 6, 3 }, { 6, 7 } };
 
    int m2[R2][C2] = { { 1, 8}, { 6, 9 } };
 
 
    multiplyMatrix(m1, m2);
 
    return 0;
}