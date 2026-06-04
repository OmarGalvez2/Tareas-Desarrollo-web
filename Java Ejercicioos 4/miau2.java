import java.util.Scanner;

abstract class Mamiferos {
    String nombre;
    String raza;
    String tipoAnimal;
    String fechaNacimiento;
    float peso;

    public Mamiferos(String nombre, String raza, String tipoAnimal, String fechaNacimiento, float peso) {
        this.nombre = nombre;
        this.raza = raza;
        this.tipoAnimal = tipoAnimal;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
    }

    public void comer() {
        System.out.println(nombre + " esta comiendo.");
    }

    public void mostrarTipoAnimal() {
        System.out.println("Tipo de animal: " + tipoAnimal);
    }

    public abstract void comunicarse();
}

class Perro extends Mamiferos {
    String lugarEntrenamiento;

    public Perro(String nombre, String raza, String tipoAnimal, String fechaNacimiento, float peso, String lugarEntrenamiento) {
        super(nombre, raza, tipoAnimal, fechaNacimiento, peso);
        this.lugarEntrenamiento = lugarEntrenamiento;
    }

    public void comunicarse() {
        System.out.println(nombre + " dice: guau guau");
    }
}

class Gato extends Mamiferos {
    double alturaSalto;

    public Gato(String nombre, String raza, String tipoAnimal, String fechaNacimiento, float peso, double alturaSalto) {
        super(nombre, raza, tipoAnimal, fechaNacimiento, peso);
        this.alturaSalto = alturaSalto;
    }

    public void comunicarse() {
        System.out.println(nombre + " dice: miau miau");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("DATOS DEL PERRO");
        System.out.print("Nombre: ");
        String nombrePerro = entrada.nextLine();

        System.out.print("Raza: ");
        String razaPerro = entrada.nextLine();

        System.out.print("Tipo de animal: ");
        String tipoPerro = entrada.nextLine();

        System.out.print("Fecha de nacimiento: ");
        String fechaPerro = entrada.nextLine();

        System.out.print("Peso: ");
        float pesoPerro = entrada.nextFloat();
        entrada.nextLine();

        System.out.print("Lugar de entrenamiento: ");
        String lugarEntrenamiento = entrada.nextLine();

        Perro perro = new Perro(nombrePerro, razaPerro, tipoPerro, fechaPerro, pesoPerro, lugarEntrenamiento);

        System.out.println();

        System.out.println("DATOS DEL GATO");
        System.out.print("Nombre: ");
        String nombreGato = entrada.nextLine();

        System.out.print("Raza: ");
        String razaGato = entrada.nextLine();

        System.out.print("Tipo de animal: ");
        String tipoGato = entrada.nextLine();

        System.out.print("Fecha de nacimiento: ");
        String fechaGato = entrada.nextLine();

        System.out.print("Peso: ");
        float pesoGato = entrada.nextFloat();

        System.out.print("Altura de salto: ");
        double alturaSalto = entrada.nextDouble();

        Gato gato = new Gato(nombreGato, razaGato, tipoGato, fechaGato, pesoGato, alturaSalto);

        System.out.println();
        System.out.println("RESULTADOS");

        perro.comer();
        perro.mostrarTipoAnimal();
        perro.comunicarse();

        System.out.println();

        gato.comer();
        gato.mostrarTipoAnimal();
        gato.comunicarse();

        entrada.close();
    }
}