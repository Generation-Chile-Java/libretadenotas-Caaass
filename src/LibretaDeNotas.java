
import java.util.*;

public class LibretaDeNotas {
    private HashMap<String, ArrayList<Double>> calificaciones;
    private double promedioCurso;

    public LibretaDeNotas() {
        calificaciones = new HashMap<>();
    }

    public void ingresarDatos() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();

        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int cantidadNotas = scanner.nextInt();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno " + (i + 1) + ": ");
            String nombre = scanner.next();

            ArrayList<Double> notas = new ArrayList<>();
            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la nota " + (j + 1) + " de " + nombre + ": ");
                double nota = scanner.nextDouble();
                notas.add(nota);
            }

            calificaciones.put(nombre, notas);
        }

        calcularPromedioCurso();
    }

    private void calcularPromedioCurso() {
        double sumaTotal = 0;
        int totalNotas = 0;

        for (ArrayList<Double> notas : calificaciones.values()) {
            for (double nota : notas) {
                sumaTotal += nota;
                totalNotas++;
            }
        }

        promedioCurso = sumaTotal / totalNotas;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante.");
            System.out.println("2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.");
            System.out.println("3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.");
            System.out.println("0. Salir del Menú.");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarPromedioPorEstudiante();
                    break;
                case 2:
                    verificarAprobacion();
                    break;
                case 3:
                    verificarNotaConPromedioCurso();
                    break;
                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void mostrarPromedioPorEstudiante() {
        for (Map.Entry<String, ArrayList<Double>> entry : calificaciones.entrySet()) {
            String nombre = entry.getKey();
            ArrayList<Double> notas = entry.getValue();

            double promedio = calcularPromedio(notas);
            System.out.println("El promedio de " + nombre + " es: " + promedio);
        }
    }

    private double calcularPromedio(ArrayList<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    private void verificarAprobacion() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.next();

        if (calificaciones.containsKey(nombre)) {
            System.out.print("Ingrese la nota a verificar: ");
            double nota = scanner.nextDouble();

            if (nota >= 4.0) {
                System.out.println("La nota es Aprobatoria.");
            } else {
                System.out.println("La nota es Reprobatoria.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private void verificarNotaConPromedioCurso() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.next();

        if (calificaciones.containsKey(nombre)) {
            System.out.print("Ingrese la nota a verificar: ");
            double nota = scanner.nextDouble();

            if (nota > promedioCurso) {
                System.out.println("La nota está por sobre el promedio del curso.");
            } else if (nota < promedioCurso) {
                System.out.println("La nota está por debajo del promedio del curso.");
            } else {
                System.out.println("La nota es igual al promedio del curso.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    public static void main(String[] args) {
        LibretaDeNotas libreta = new LibretaDeNotas();
        libreta.ingresarDatos();
        libreta.mostrarMenu();
    }
}


















