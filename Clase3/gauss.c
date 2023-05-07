#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

#define n 5

void crearmatrix(float A[][n*2]){
    int x=n;
    for(int i=0; i<n; i++){
        for(int j=0; j<n*2; j++){
            if(j<n){
               A[i][j]=rand()%(21); 
            }
            else if ((i+n)==j){
                A[i][j]=1;
            }
            else{
                A[i][j]=0;
            }
        }
    }
}

void copiarmatrix(float A[][n*2],float B[][n*2]){
    for(int i=0; i<n; i++){
        for(int j=0; j<n*2; j++){
            B[i][j]=A[i][j];
        }
    }
}

void imprimir_matriz(float A[][n*2]) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < 2*n; j++) {
            printf("%.2f\t", A[i][j]);
        }
        printf("\n");
    }
    printf("\n");
}

void gauss_jordan_sec(float A[][n*2]) {
    int i, j, k;
    float pivote, tmp;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            if (j != i) {
                pivote = A[j][i] / A[i][i];
                for (k = i; k < 2*n; k++) {
                    A[j][k] -= pivote * A[i][k];
                }
            }
        }
            pivote = A[i][i];
            for (j = i; j < 2*n; j++) {
                A[i][j] /= pivote;
            }
    }
}

void gauss_jordan(float A[][n*2]) {
    int i, j, k;
    float pivote, tmp;
    
    #pragma omp parallel shared(A) private(i, j, k, pivote, tmp)
    {
        for (i = 0; i < n; i++) {
            #pragma omp for
            for (j = 0; j < n; j++) {
                if (j != i) {
                    pivote = A[j][i] / A[i][i];
                    for (k = i; k < 2*n; k++) {
                        A[j][k] -= pivote * A[i][k];
                    }
                }
            }
            #pragma omp single
            {
                pivote = A[i][i];
                for (j = i; j < 2*n; j++) {
                    A[i][j] /= pivote;
                }
            }
            #pragma omp barrier
        }
    }
}

int main() {
    double inicio, duracion, suma1=0, suma2=0;
    float A[n][n*2];
    float B[n][n*2];
    crearmatrix(A);
    copiarmatrix(A,B);
    
    printf("Matriz original:\n");
    imprimir_matriz(A);
    for(int i=0; i<5;i++){
        duracion = 0.0;
        inicio = omp_get_wtime();
        gauss_jordan_sec(A);
        duracion += (omp_get_wtime() - inicio);
        suma1 += duracion;
    }
    printf("Matriz inversa:\n");
    imprimir_matriz(A);
    printf("La duracion promedio es de %lf en secuencial\n\n", suma1/5);
    
    
    printf("Matriz original COPIA:\n");
    imprimir_matriz(B);
    for(int j=0; j<5;j++){
        duracion = 0.0;
        inicio = omp_get_wtime();
        gauss_jordan(B);
        duracion += (omp_get_wtime() - inicio);
        suma2 += duracion;
    }
    printf("Matriz inversa:\n");
    imprimir_matriz(B);
    printf("La duracion promedio es de %lf en paralelo\n\n", suma2/5);
    return 0;
}
