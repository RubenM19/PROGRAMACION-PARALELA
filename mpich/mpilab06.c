#include "mpi.h"
#include "stdlib.h"
#include "stdio.h"
#include "assert.h"

int main(int argc, char **argv){
    int respuesta = 0;
    int proceso = 0, procesos = 0;

    MPI_Init(&argc,&argv);
    MPI_Comm_size(MPI_COMM_WORLD, &procesos);
    MPI_Comm_rank(MPI_COMM_WORLD, &proceso);

    if (proceso == 0){
        FILE *file = fopen("mpilab05.txt","r");
        assert(file != NULL);
        fscanf(file, "%d", &respuesta);
        fclose(file);
    }

    printf("Antes del Broadcast proceso %d respuesta %d\n", proceso, respuesta);
    MPI_Bcast(&respuesta, 1, MPI_INT, 0, MPI_COMM_WORLD);
    MPI_Barrier(MPI_COMM_WORLD);
    printf("Después del Broadcast proceso %d respuesta %d\n", proceso, respuesta);
    MPI_Finalize();
}
