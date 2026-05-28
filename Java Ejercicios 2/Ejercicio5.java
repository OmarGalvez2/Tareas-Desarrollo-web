import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int[][] matrizA = new int[3][3];
        int[][] matrizB = new int[3][3];
        int[][] suma = new int[3][3];
        int[][] producto = new int[3][3];

        int numero;

        System.out.println("Ingrese los valores de la matriz A:");

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                System.out.print("A[" + fila + "][" + columna + "]: ");
                matrizA[fila][columna] = entrada.nextInt();
            }
        }

        System.out.println("Ingrese los valores de la matriz B:");

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                System.out.print("B[" + fila + "][" + columna + "]: ");
                matrizB[fila][columna] = entrada.nextInt();
            }
        }

        System.out.print("Ingrese el numero para multiplicar las matrices: ");
        numero = entrada.nextInt();

        System.out.println("Matriz A multiplicada por " + numero + ":");

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                System.out.print((matrizA[fila][columna] * numero) + " ");
            }
            System.out.println();
        }

        System.out.println("Matriz B multiplicada por " + numero + ":");

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                System.out.print((matrizB[fila][columna] * numero) + " ");
            }
            System.out.println();
        }

        System.out.println("Suma de la matriz A y la matriz B:");

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                suma[fila][columna] = matrizA[fila][columna] + matrizB[fila][columna];
                System.out.print(suma[fila][columna] + " ");
            }
            System.out.println();
        }

        System.out.println("Producto de la matriz A por la matriz B:");

        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                producto[fila][columna] = 0;

                for (int k = 0; k < 3; k++) {
                    producto[fila][columna] = producto[fila][columna] + matrizA[fila][k] * matrizB[k][columna];
                }

                System.out.print(producto[fila][columna] + " ");
            }
            System.out.println();
        }
    }
}