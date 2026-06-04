interface IMamiferos {
    void comunicarse();
}

class Mamiferos implements IMamiferos {
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

    public void comunicarse() {
        System.out.println("El mamifero se comunica.");
    }
}

class Perro extends Mamiferos {
    String lugarEntrenamiento;

    public Perro(String nombre, String raza, String tipoAnimal, String fechaNacimiento, float peso,
            String lugarEntrenamiento) {

        super(nombre, raza, tipoAnimal, fechaNacimiento, peso);
        this.lugarEntrenamiento = lugarEntrenamiento;
    }

    @Override
    public void comunicarse() {
        System.out.println(nombre + " dice: guau guau");
    }
}

class Gato extends Mamiferos {
    double alturaSalto;

    public Gato(String nombre, String raza, String tipoAnimal, String fechaNacimiento, float peso,
            double alturaSalto) {

        super(nombre, raza, tipoAnimal, fechaNacimiento, peso);
        this.alturaSalto = alturaSalto;
    }

    @Override
    public void comunicarse() {
        System.out.println(nombre + " dice: miau miau");
    }
}

public class Main {
    public static void main(String[] args) {

        Perro perro = new Perro(
                "Rocky",
                "Pastor Aleman",
                "Perro",
                "12/05/2020",
                25.5f,
                "Centro Canino");

        Gato gato = new Gato(
                "Misu",
                "Persa",
                "Gato",
                "10/03/2021",
                6.2f,
                1.5);

        System.out.println("DATOS DEL PERRO");
        System.out.println("Nombre: " + perro.nombre);
        System.out.println("Raza: " + perro.raza);
        System.out.println("Peso: " + perro.peso);
        System.out.println("Lugar de entrenamiento: " + perro.lugarEntrenamiento);
        perro.comer();
        perro.comunicarse();

        System.out.println();

        System.out.println("DATOS DEL GATO");
        System.out.println("Nombre: " + gato.nombre);
        System.out.println("Raza: " + gato.raza);
        System.out.println("Peso: " + gato.peso);
        System.out.println("Altura de salto: " + gato.alturaSalto);
        gato.comer();
        gato.comunicarse();
    }
}