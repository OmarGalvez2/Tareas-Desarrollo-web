import java.util.Scanner;

class Mamifero {
    String nombre;
    String raza;
    String fechaNacimiento;
    float peso;

    public Mamifero(String nombre, String raza, String fechaNacimiento, float peso) {
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
    }

    public void comer() {
        System.out.println(nombre + " esta comiendo");
    }

    public void comunicarse() {
        System.out.println(nombre + " se esta comunicando");
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Raza: " + raza);
        System.out.println("Fecha de nacimiento: " + fechaNacimiento);
        System.out.println("Peso: " + peso);
    }
}

class Perro extends Mamifero {
    String lugarEntrenamiento;

    public Perro(String nombre, String raza, String fechaNacimiento, float peso, String lugarEntrenamiento) {
        super(nombre, raza, fechaNacimiento, peso);
        this.lugarEntrenamiento = lugarEntrenamiento;
    }

    public void comunicarse() {
        System.out.println(nombre + " dice: guau guau");
    }

    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Lugar de entrenamiento: " + lugarEntrenamiento);
    }
}

class Gato extends Mamifero {
    double alturaSalto;

    public Gato(String nombre, String raza, String fechaNacimiento, float peso, double alturaSalto) {
        super(nombre, raza, fechaNacimiento, peso);
        this.alturaSalto = alturaSalto;
    }

    public void comunicarse() {
        System.out.println(nombre + " dice: miau miau");
    }

    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Altura de salto: " + alturaSalto);
    }
}

public class Ejercicio58 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Datos del perro");

        System.out.print("Nombre: ");
        String nombrePerro = entrada.nextLine();

        System.out.print("Raza: ");
        String razaPerro = entrada.nextLine();

        System.out.print("Fecha de nacimiento: ");
        String fechaPerro = entrada.nextLine();

        System.out.print("Peso: ");
        float pesoPerro = entrada.nextFloat();
        entrada.nextLine();

        System.out.print("Lugar de entrenamiento: ");
        String lugarEntrenamiento = entrada.nextLine();

        Perro perro = new Perro(nombrePerro, razaPerro, fechaPerro, pesoPerro, lugarEntrenamiento);

        System.out.println();

        System.out.println("Datos del gato");

        System.out.print("Nombre: ");
        String nombreGato = entrada.nextLine();

        System.out.print("Raza: ");
        String razaGato = entrada.nextLine();

        System.out.print("Fecha de nacimiento: ");
        String fechaGato = entrada.nextLine();

        System.out.print("Peso: ");
        float pesoGato = entrada.nextFloat();

        System.out.print("Altura de salto: ");
        double alturaSalto = entrada.nextDouble();

        Gato gato = new Gato(nombreGato, razaGato, fechaGato, pesoGato, alturaSalto);

        System.out.println();

        System.out.println("Informacion del perro");
        perro.mostrarDatos();
        perro.comer();
        perro.comunicarse();

        System.out.println();

        System.out.println("Informacion del gato");
        gato.mostrarDatos();
        gato.comer();
        gato.comunicarse();

        entrada.close();
    }
}