import java.util.*;

public class Grafo {
    private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    public void agregarVertice(int estadio) {
        if (adjacencyList.containsKey(estadio)) {
            System.out.println("El vertice ya existe.");
        } else {
            adjacencyList.put(estadio, new ArrayList<>());
            System.out.println("vertice agregado.");
        }
    }

    public void eliminarVertice(int estadio) {
        if (!adjacencyList.containsKey(estadio)) {
            System.out.println("El vertice no existe.");
            return;
        }

        adjacencyList.remove(estadio);
     
        for (List<Integer> lista : adjacencyList.values()) {
            lista.remove(Integer.valueOf(estadio));
        }

        System.out.println("vertice eliminado.");
    }

    public void agregarArista(int desde, int hacia) {
        if (!adjacencyList.containsKey(desde) || !adjacencyList.containsKey(hacia)) {
            System.out.println("Uno o ambos vertices no existen.");
            return;
        }

        adjacencyList.get(desde).add(hacia);
        System.out.println("Arista agregada.");
    }

  
    public void eliminarArista(int desde, int hacia) {
        if (!adjacencyList.containsKey(desde)) {
            System.out.println("El vertice no existe.");
            return;
        }

        List<Integer> lista = adjacencyList.get(desde);
        if (lista.remove(Integer.valueOf(hacia))) {
            System.out.println("Arista eliminada.");
        } else {
            System.out.println("No existe arista entre los vertices dados.");
        }
    }


    public void mostrarvertices() {
        System.out.println("Lista de vertices:");
        for (int estadio : adjacencyList.keySet()) {
            System.out.println("- " + estadio);
        }
    }

    public void mostrarAdyacentes(int estadio) {
        if (!adjacencyList.containsKey(estadio)) {
            System.out.println("El vertice no existe.");
            return;
        }

        System.out.println("vertices conectados con " + estadio + ":");
        for (int vecino : adjacencyList.get(estadio)) {
            System.out.println("- " + vecino);
        }
    }

 
    public void existeArista(int desde, int hacia) {
        if (!adjacencyList.containsKey(desde)) {
            System.out.println("El vertice no existe.");
            return;
        }

        List<Integer> lista = adjacencyList.get(desde);
        if (lista.contains(hacia)) {
            System.out.println("Sí existe una arista.");
        } else {
            System.out.println("No existe una arista.");
        }
    }

    // Menú de opciones para interactuar con el grafo
    public static void menu() {
        Grafo g = new Grafo();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int a, b;

        do {
            System.out.println("\nMenu");
            System.out.println("1. Agregar vertice");
            System.out.println("2. Eliminar vertice");
            System.out.println("3. Agregar arista");
            System.out.println("4. Eliminar arista");
            System.out.println("5. Desplegar lista de vertices");
            System.out.println("6. Desplegar vertices adyacentes");
            System.out.println("7. Verificar arista");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el ID del vertice: ");
                    a = scanner.nextInt();
                    g.agregarVertice(a);
                    break;
                case 2:
                    System.out.print("Ingrese el ID del vertice a eliminar: ");
                    a = scanner.nextInt();
                    g.eliminarVertice(a);
                    break;
                case 3:
                    System.out.print("vertice origen: ");
                    a = scanner.nextInt();
                    System.out.print("vertice destino: ");
                    b = scanner.nextInt();
                    g.agregarArista(a, b);
                    break;
                case 4:
                    System.out.print("vertice origen: ");
                    a = scanner.nextInt();
                    System.out.print("vertice destino: ");
                    b = scanner.nextInt();
                    g.eliminarArista(a, b);
                    break;
                case 5:
                    g.mostrarvertices();
                    break;
                case 6:
                    System.out.print("vertice a consultar: ");
                    a = scanner.nextInt();
                    g.mostrarAdyacentes(a);
                    break;
                case 7:
                    System.out.print("vertice origen: ");
                    a = scanner.nextInt();
                    System.out.print("vertice destino: ");
                    b = scanner.nextInt();
                    g.existeArista(a, b);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public static void main(String[] args) {

        menu();
        // Ejemplo de uso del grafo 
    }
}