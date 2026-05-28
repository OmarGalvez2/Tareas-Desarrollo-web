import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int[] numeros = new int[10];
        int buscar;
        boolean encontrado = false;

        for (int i = 0; i < 10; i++) {
            System.out.print("Ingrese el numero " + (i + 1) + ": ");
            numeros[i] = entrada.nextInt();
        }

        System.out.print("Ingrese el numero que desea buscar: ");
        buscar = entrada.nextInt();

        for (int i = 0; i < 10; i++) {
            if (numeros[i] == buscar) {
                System.out.println("El numero esta en la posicion: " + (i + 1));
                encontrado = true;
            }
        }

        if (encontrado == false) {
            System.out.println("El numero no fue encontrado.");
        }
    }
}