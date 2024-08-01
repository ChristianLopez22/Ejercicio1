import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estadio estadio = new Estadio("Estadio Nacional", 80000);
        
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            procesarOpcion(opcion, estadio, scanner);
        } while (opcion != 7);
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("=== Sistema de Venta de Boletos ===");
        System.out.println("1. Agregar Comprador");
        System.out.println("2. Comprar Boletos");
        System.out.println("3. Consultar Disponibilidad Total");
        System.out.println("4. Consultar Disponibilidad Individual");
        System.out.println("5. Generar Reporte de Caja");
        System.out.println("6. Guardar Información en CSV");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static void procesarOpcion(int opcion, Estadio estadio, Scanner scanner) {
        switch (opcion) {
            case 1:
                // Agregar Comprador
                agregarComprador(estadio, scanner);
                break;
            case 2:
                // Comprar Boletos
                comprarBoletos(estadio, scanner);
                break;
            case 3:
                // Consultar Disponibilidad Total
                estadio.consultarDisponibilidadTotal();
                break;
            case 4:
                // Consultar Disponibilidad Individual
                System.out.print("Ingrese el nombre de la localidad: ");
                String nombreLocalidad = scanner.nextLine();
                estadio.consultarDisponibilidadIndividual(nombreLocalidad);
                break;
            case 5:
                // Generar Reporte de Caja
                estadio.generarReporteCaja();
                break;
            case 6:
                // Guardar Información en CSV
                estadio.guardarInformacionCSV();
                break;
            case 7:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
    
    private static void agregarComprador(Estadio estadio, Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese el presupuesto máximo: ");
        double presupuesto = scanner.nextDouble();
        System.out.print("Ingrese la cantidad de boletos a comprar: ");
        int cantidadBoletos = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        
        Comprador comprador = new Comprador(nombre, telefono, cantidadBoletos, presupuesto);
        estadio.agregarComprador(comprador);
    }
    
    private static void comprarBoletos(Estadio estadio, Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        Comprador comprador = estadio.buscarComprador(nombre);
        
        if (comprador != null) {
            System.out.print("Ingrese la localidad: ");
            String nombreLocalidad = scanner.nextLine();
            Localidad localidad = estadio.buscarLocalidad(nombreLocalidad);
            
            if (localidad != null) {
                comprador.realizarCompra(localidad);
            } else {
                System.out.println("Localidad no encontrada.");
            }
        } else {
            System.out.println("Comprador no encontrado.");
        }
    }
}
