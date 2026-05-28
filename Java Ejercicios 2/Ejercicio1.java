import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int numero;
        int suma = 0;
        double promedio;

        for (int i = 1; i <= 10; i++) {
            System.out.print("Ingrese el numero " + i + ": ");
            numero = entrada.nextInt();

            suma = suma + numero;
        }

        promedio = suma / 10.0;

        System.out.println("La suma es: " + suma);
        System.out.println("El promedio es: " + promedio);
    }
} 