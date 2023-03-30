#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main()
{
    #pragma omp parallel
    {
    printf("Hola tarea numero %i \n", omp_get_thread_num());
    }
}
