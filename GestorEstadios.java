import java.util.*;

public class GestorEstadios {
    private ArbolAVL<Estadio> estadiosPorNombre = new ArbolAVL<>();
    private Scanner scanner = new Scanner(System.in);

    public void registrarEstadio() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("País: ");
        String pais = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Año de inauguración: ");
        int anio = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        
        Estadio estadio = new Estadio(nombre, ciudad, estado, pais, capacidad, anio);  
        // Insertar estadio en el árbol AVL
        estadiosPorNombre.insertar(estadio);
        System.out.println("Estadio registrado exitosamente.");
    }

    public void removerEstadio() {
        System.out.print("Ingrese el nombre del estadio a eliminar: ");
        String nombre = scanner.nextLine().toUpperCase();
        
        // Crear un estadio temporal con el nombre para eliminarlo
        Estadio estadio = new Estadio(nombre, "", "", "", 0, 0);
        
        // Eliminar estadio del árbol AVL
        estadiosPorNombre.eliminar(estadio);
        System.out.println("Estadio eliminado si existía en el registro.");
    }

    public void consultarEstadio() {
        System.out.print("Ingrese el nombre del estadio a consultar: ");
        String nombre = scanner.nextLine().toUpperCase();
        
        
        // Buscar el estadio en el árbol AVL
        List<Estadio> resultado = estadiosPorNombre.obtenerElementosOrdenados();
        boolean encontrado = resultado.stream().anyMatch(e -> e.nombre.equals(nombre));
        
        if (encontrado) {
            resultado.stream()
                    .filter(e -> e.nombre.equals(nombre))
                    .forEach(System.out::println);
        } else {
            System.out.println("Estadio no encontrado.");
        }
    }

    public void listarEstadiosPorNombre() {
        List<Estadio> ordenados = estadiosPorNombre.obtenerElementosOrdenados();
        ordenados.forEach(System.out::println);
    }

    public void listarEstadiosPorPais() {
        Map<String, List<Estadio>> estadiosPorPais = new HashMap<>();
        
        // Recorremos los estadios en el árbol y agrupamos por país
        List<Estadio> estadios = estadiosPorNombre.obtenerElementosOrdenados();
        for (Estadio estadio : estadios) {
            estadiosPorPais.computeIfAbsent(estadio.pais, k -> new ArrayList<>()).add(estadio);
        }
        
        estadiosPorPais.forEach((pais, lista) -> {
            System.out.println("\n" + pais + ":");
            lista.forEach(System.out::println);
        });
    }

    public void mostrarEstadioMasReciente() {
        List<Estadio> estadios = estadiosPorNombre.obtenerElementosOrdenados();
        estadios.stream()
                .max(Comparator.comparingInt(e -> e.anioInauguracion))
                .ifPresent(estadio -> System.out.println("Estadio más reciente: " + estadio));
    }

    public void listarEstadiosPorCapacidad() {
        List<Estadio> estadios = estadiosPorNombre.obtenerElementosOrdenados();
        estadios.stream()
                .sorted(Comparator.comparingInt(e -> -e.capacidad))
                .forEach(estadio -> System.out.println(estadio.nombre + " - " + estadio.ciudad + ", " + estadio.pais
                        + " | Capacidad: " + estadio.capacidad));
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Registrar estadio");
            System.out.println("2. Remover estadio");
            System.out.println("3. Consultar estadio");
            System.out.println("4. Listar estadios por nombre");
            System.out.println("5. Listar estadios por pais");
            System.out.println("6. Mostrar estadio mas reciente");
            System.out.println("7. Listar estadios por capacidad");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente
            switch (opcion) {
                case 1 -> registrarEstadio();
                case 2 -> removerEstadio();
                case 3 -> consultarEstadio();
                case 4 -> listarEstadiosPorNombre();
                case 5 -> listarEstadiosPorPais();
                case 6 -> mostrarEstadioMasReciente();
                case 7 -> listarEstadiosPorCapacidad();
                case 8 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 8);
    }

    public static void main(String[] args) {
        GestorEstadios gestor = new GestorEstadios();
        gestor.menu();
    }
}

