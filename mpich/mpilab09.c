/* Exercise:
 * - Compile and run, comparing output to source code.
 * - Uncomment the 'commented out' call to printArray.
 * - Save, recompile, rerun, comparing output to source code.
 * - Explain behavior of MPI_Reduce() in terms of
 *     srcArr and destArr.
*/

#include <mpi.h>
#include <stdio.h>

#define N 5

void imprime(int pro, char* tit, int*vec, int tam){
        printf("Proceso %d [%s] = [", pro, tit);
        for(int i = 0; i<tam; i++){
                printf("%3d", vec[i]);
        }
        printf("]\n");
}

int main(int argc, char** argv) {
    int proceso;
    int fuente[] = {0};
    int destino[] = {0};

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &proceso);

    if (proceso == 0) {
        printf("\nAntes de la reduccion: ");
        imprime(proceso, "Arreglo destino", destino, N);
    }

    for (int i = 0; i < N; i++) {
        fuente[i] = proceso * i;
    }

   imprime(proceso, "Arreglo fuente", fuente, N);

    MPI_Reduce(&fuente, &destino, N, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    if (proceso == 0) {
        printf("\nDespues de la reduccion:  ");
        imprime(proceso, "destino", destino, N);
        printf("\n");
    }


        MPI_Barrier(MPI_COMM_WORLD);
        imprime(proceso, "fuente", destino, N);
    MPI_Finalize();

    return 0;
}
