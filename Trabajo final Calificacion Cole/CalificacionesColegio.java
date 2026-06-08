import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CalificacionesColegio {
    private static final Path ARCHIVO_DATOS = Paths.get("calificaciones.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                System.out.println("\nPrograma finalizado.")));

        while (!salir) {
            try {
                mostrarMenu();
                String opcion = leerLinea(scanner);

                if (esSalida(opcion)) {
                    salir = true;
                } else if (opcion.equals("1")) {
                    registrarCalificaciones(scanner);
                } else if (opcion.equals("2")) {
                    generarReporte(scanner);
                } else if (opcion.equals("3")) {
                    salir = true;
                } else {
                    System.out.println("Opcion no valida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error, pero el programa sigue funcionando.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("COLEGIO DIOS ES BUENO");
        System.out.println("SISTEMA DE CALIFICACIONES");
        System.out.println("====================================");
        System.out.println("1- Registro de calificaciones");
        System.out.println("2- Reporte calificaciones por mes");
        System.out.println("3- Presione <ESC> para salir");
        System.out.println("====================================");
        System.out.print("Elija la opcion deseada y pulse <ENTER>: ");
    }

    private static void registrarCalificaciones(Scanner scanner) {
        System.out.println();
        System.out.println("REGISTRO DE CALIFICACIONES");

        String mes = leerTextoObligatorio(scanner, "Mes: ");
        String curso = leerTextoObligatorio(scanner, "Curso: ");
        boolean continuar = true;

        while (continuar) {
            System.out.println();
            System.out.println("Datos del estudiante");
            String nombre = leerTextoObligatorio(scanner, "Nombre: ");
            String apellido = leerTextoObligatorio(scanner, "Apellido: ");
            int matematica = leerNota(scanner, "Matematica: ");
            int lengua = leerNota(scanner, "Lengua: ");
            int naturales = leerNota(scanner, "Naturales: ");
            int sociales = leerNota(scanner, "Sociales: ");

            Calificacion calificacion = new Calificacion(
                    mes, curso, nombre, apellido, matematica, lengua, naturales, sociales);

            guardarCalificacion(calificacion);
            System.out.println("Calificacion guardada correctamente.");

            System.out.print("Desea registrar otro estudiante? (S/N): ");
            String respuesta = leerLinea(scanner);
            continuar = respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("SI");
        }
    }

    private static void generarReporte(Scanner scanner) {
        System.out.println();
        System.out.println("REPORTE DE CALIFICACIONES");

        String mes = leerTextoObligatorio(scanner, "Mes del reporte: ");
        String curso = leerTextoObligatorio(scanner, "Curso del reporte: ");
        List<Calificacion> calificaciones = cargarCalificaciones();
        List<Calificacion> reporte = new ArrayList<>();

        for (Calificacion calificacion : calificaciones) {
            if (calificacion.mes.equalsIgnoreCase(mes)
                    && calificacion.curso.equalsIgnoreCase(curso)) {
                reporte.add(calificacion);
            }
        }

        reporte.sort(Comparator
                .comparing((Calificacion c) -> c.apellido.toLowerCase(Locale.ROOT))
                .thenComparing(c -> c.nombre.toLowerCase(Locale.ROOT)));

        imprimirReporte(mes, curso, reporte);
    }

    private static void imprimirReporte(String mes, String curso, List<Calificacion> reporte) {
        System.out.println();
        System.out.println("Colegio Dios es bueno. Reporte de Calificaciones de " + mes);
        System.out.println("Curso: " + curso);
        System.out.println("================================================================================");
        System.out.printf("%-12s %-12s %10s %8s %10s %10s %9s %8s%n",
                "Nombre", "Apellido", "Matematica", "Lengua", "Naturales",
                "Sociales", "Promedio", "Literal");
        System.out.println("================================================================================");

        if (reporte.isEmpty()) {
            System.out.println("No hay calificaciones registradas para ese mes y curso.");
        } else {
            for (Calificacion calificacion : reporte) {
                double promedio = calcularPromedio(calificacion.sumaNotas(), 4);
                String literal = obtenerLiteral(promedio);

                System.out.printf("%-12s %-12s %10d %8d %10d %10d %9s %8s%n",
                        calificacion.nombre,
                        calificacion.apellido,
                        calificacion.matematica,
                        calificacion.lengua,
                        calificacion.naturales,
                        calificacion.sociales,
                        formatoPromedio(promedio),
                        literal);
            }
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Total de estudiantes: " + reporte.size());
    }

    private static void guardarCalificacion(Calificacion calificacion) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                ARCHIVO_DATOS,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.write(calificacion.aLineaTexto());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("No se pudo guardar en el archivo TXT.");
        }
    }

    private static List<Calificacion> cargarCalificaciones() {
        List<Calificacion> calificaciones = new ArrayList<>();

        if (!Files.exists(ARCHIVO_DATOS)) {
            return calificaciones;
        }

        try (BufferedReader reader = Files.newBufferedReader(ARCHIVO_DATOS)) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Calificacion calificacion = Calificacion.desdeLineaTexto(linea);
                if (calificacion != null) {
                    calificaciones.add(calificacion);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo TXT.");
        }

        return calificaciones;
    }

    private static String leerTextoObligatorio(Scanner scanner, String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = limpiarTexto(leerLinea(scanner));

            if (texto.isEmpty()) {
                System.out.println("Este dato es obligatorio.");
            }
        } while (texto.isEmpty());

        return texto;
    }

    private static int leerNota(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String texto = leerLinea(scanner);
                int nota = Integer.parseInt(texto);

                if (nota >= 0 && nota <= 100) {
                    return nota;
                }

                System.out.println("La nota debe estar entre 0 y 100.");
            } catch (NumberFormatException e) {
                System.out.println("Digite un numero valido.");
            }
        }
    }

    private static String leerLinea(Scanner scanner) {
        try {
            if (!scanner.hasNextLine()) {
                return "ESC";
            }
            return scanner.nextLine().trim();
        } catch (Exception e) {
            return "";
        }
    }

    private static boolean esSalida(String texto) {
        return texto.equalsIgnoreCase("ESC") || texto.indexOf(27) >= 0;
    }

    private static double calcularPromedio(int suma, int cantidad) {
        try {
            if (cantidad == 0) {
                throw new ArithmeticException("Division por cero");
            }
            return (double) suma / cantidad;
        } catch (ArithmeticException e) {
            System.out.println("No se puede dividir entre cero al calcular el promedio.");
            return 0;
        }
    }

    private static String obtenerLiteral(double promedio) {
        if (promedio >= 90 && promedio <= 100) {
            return "A";
        } else if (promedio >= 80 && promedio < 90) {
            return "B";
        } else if (promedio >= 70 && promedio < 80) {
            return "C";
        } else {
            return "F";
        }
    }

    private static String formatoPromedio(double promedio) {
        if (promedio == Math.rint(promedio)) {
            return String.format(Locale.US, "%.0f", promedio);
        }
        return String.format(Locale.US, "%.2f", promedio);
    }

    private static String limpiarTexto(String texto) {
        return texto.replace("|", " ").trim();
    }

    private static class Calificacion {
        String mes;
        String curso;
        String nombre;
        String apellido;
        int matematica;
        int lengua;
        int naturales;
        int sociales;

        Calificacion(String mes, String curso, String nombre, String apellido,
                     int matematica, int lengua, int naturales, int sociales) {
            this.mes = limpiarTexto(mes);
            this.curso = limpiarTexto(curso);
            this.nombre = limpiarTexto(nombre);
            this.apellido = limpiarTexto(apellido);
            this.matematica = matematica;
            this.lengua = lengua;
            this.naturales = naturales;
            this.sociales = sociales;
        }

        int sumaNotas() {
            return matematica + lengua + naturales + sociales;
        }

        String aLineaTexto() {
            return mes + "|"
                    + curso + "|"
                    + nombre + "|"
                    + apellido + "|"
                    + matematica + "|"
                    + lengua + "|"
                    + naturales + "|"
                    + sociales;
        }

        static Calificacion desdeLineaTexto(String linea) {
            try {
                String[] partes = linea.split("\\|");
                if (partes.length != 8) {
                    return null;
                }

                return new Calificacion(
                        partes[0],
                        partes[1],
                        partes[2],
                        partes[3],
                        Integer.parseInt(partes[4]),
                        Integer.parseInt(partes[5]),
                        Integer.parseInt(partes[6]),
                        Integer.parseInt(partes[7]));
            } catch (Exception e) {
                return null;
            }
        }
    }
}
