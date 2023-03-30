#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main()
{
    #pragma omp parallel num_threads(5)
    {
    printf("Hello World\n");
    }
}
