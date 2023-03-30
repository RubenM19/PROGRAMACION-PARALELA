//Creamos un programa que llene un vector de 1000000 de elementos y por medio de método secuencial y paralela comparar los tiempos que se tardan
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

#define DIM 1000000
double vector[DIM];

void llenar (double *v){
    for(int i = 0; i < DIM; i++){
        v[i] = (double)rand()/(double)RAND_MAX;
    }
}

double sumar (double *v, int ini, int fin){
    double s = 0.0;
    for(int i = ini; i < fin; i++){
        s += v[i];
    }
    return s;
}

void imprimir (double *v, int n){
    for (int i = 0; i < n; i++){
        printf("v[%i] = %f\n", i, v[i]);
    }
}

int main(int argc, char *argv[]){
    
    double inicio, duracion;
    
    llenar(vector);
    imprimir(vector,5);
    inicio = omp_get_wtime();
    double total = sumar(vector,0,DIM);
    duracion = omp_get_wtime()-inicio;
    
    printf("Suma secuencial = %f\n", total);
    printf("La duracion es = %lf segundos\n", duracion);
    
    int bloque = DIM/5; //Usaremos 5 bloques o hilos
    
    double sumas [5] = {0,0,0,0,0};
    
    inicio = omp_get_wtime();
    #pragma omp parallel num_threads(5)
    {
        //printf("Hola tarea numero %i \n", omp_get_thread_num());
        int hilo = omp_get_thread_num();
        sumas[hilo] = sumar(vector, hilo*bloque, (hilo+1)*bloque);
    }
    
    total = 0.0;
    for(int i = 0; i<5; i++){
        total += sumas[i];
    }
    
    duracion = omp_get_wtime()-inicio;
    printf("Suma paralela = %f\n", total);
    printf("La duracion es = %lf segundos\n", duracion);
}

/*Se Llega a conclusión de que secuencial en ejemplos sencillos es mucho más rápido que paralela.
Sin embargo si el ejercico es complejo y el tiempo de operar es mayor entonces la paralela sería 
mucho mpás rápido porque el tiempo de preparar los hilos sería despreciable*/