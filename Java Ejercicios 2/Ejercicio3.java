import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int divisor;
        int numero;

        System.out.print("Ingrese el divisor: ");
        divisor = entrada.nextInt();

        if (divisor == 0) {
            System.out.println("No se puede usar 0 como divisor.");
        } else {
            for (int i = 1; i <= 10; i++) {
                System.out.print("Ingrese el numero " + i + ": ");
                numero = entrada.nextInt();

                if (numero % divisor == 0) {
                    System.out.println(numero + " es multiplo de " + divisor);
                } else {
                    System.out.println(numero + " no es multiplo de " + divisor);
                }
            }
        }
    }
}