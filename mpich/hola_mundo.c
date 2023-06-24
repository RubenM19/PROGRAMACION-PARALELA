#include <stdio.h>
#include <mpi.h>

int main(int argc, char* argv[]){
	int rank, size;
	char message[13] = "Hola, mundo!";
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);

	printf("Proceso %d de %d: %s\n", rank, size, message);

	MPI_Finalize();

	return 0;
}
