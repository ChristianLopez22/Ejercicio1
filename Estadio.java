import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Estadio {
    private String nombre;
    private int capacidadTotal;
    private Localidad[] localidades;
    private Comprador[] compradores;
    private int indiceLocalidades;
    private int indiceCompradores;
    private EstadoCaja estadoCaja;
    private int contadorBoletos;
    
    public Estadio(String nombre, int capacidadTotal) {
        this.nombre = nombre;
        this.capacidadTotal = capacidadTotal;
        this.localidades = new Localidad[3]; // Suponiendo un máximo de 3 localidades
        this.compradores = new Comprador[100]; // Suponiendo un máximo de 100 compradores
        this.indiceLocalidades = 0;
        this.indiceCompradores = 0;
        this.estadoCaja = new EstadoCaja();
        this.contadorBoletos = 1;
        configurarLocalidades();
    }
    
    public void configurarLocalidades() {
        agregarLocalidad(new Localidad("Localidad 1", 40000, 250));
        agregarLocalidad(new Localidad("Localidad 5", 30000, 575));
        agregarLocalidad(new Localidad("Localidad 10", 10000, 1720));
    }
    
    private void agregarLocalidad(Localidad localidad) {
        if (indiceLocalidades < localidades.length) {
            localidades[indiceLocalidades++] = localidad;
        } else {
            System.out.println("No se pueden agregar más localidades.");
        }
    }
    
    public void agregarComprador(Comprador comprador) {
        if (indiceCompradores < compradores.length) {
            compradores[indiceCompradores++] = comprador;
            System.out.println("Comprador agregado: " + comprador.getNombre());
        } else {
            System.out.println("No se pueden agregar más compradores.");
        }
    }
    
    public Comprador buscarComprador(String nombre) {
        for (int i = 0; i < indiceCompradores; i++) {
            if (compradores[i].getNombre().equals(nombre)) {
                return compradores[i];
            }
        }
        return null;
    }
    
    public Localidad buscarLocalidad(String nombre) {
        for (int i = 0; i < indiceLocalidades; i++) {
            if (localidades[i].getNombre().equals(nombre)) {
                return localidades[i];
            }
        }
        return null;
    }
    
    public void consultarDisponibilidadTotal() {
        System.out.println("=== Disponibilidad Total de Boletos ===");
        for (int i = 0; i < indiceLocalidades; i++) {
            System.out.println(localidades[i].getNombre() + ": " + localidades[i].getCapacidadDisponible() + " boletos disponibles.");
        }
    }
    
    public void consultarDisponibilidadIndividual(String nombreLocalidad) {
        Localidad localidad = buscarLocalidad(nombreLocalidad);
        if (localidad != null) {
            System.out.println(localidad.getNombre() + ": " + localidad.getCapacidadDisponible() + " boletos disponibles.");
        } else {
            System.out.println("Localidad no encontrada.");
        }
    }
    
    public void generarReporteCaja() {
        estadoCaja.generarReporte(localidades, indiceLocalidades);
    }

    public int incrementarContadorBoletos() {
        return contadorBoletos++;
    }

    public void guardarInformacionCSV() {
        try (FileWriter writer = new FileWriter("informacion.csv")) {
            writer.append("Nombre,Teléfono,Cantidad de Boletos,Presupuesto,Localidad,Precio del Boleto,Fecha de Compra,Numero de Ticket\n");
            for (int i = 0; i < indiceCompradores; i++) {
                Comprador comprador = compradores[i];
                for (Ticket ticket : comprador.getTickets()) {
                    writer.append(comprador.getNombre()).append(",");
                    writer.append(comprador.getTelefono()).append(",");
                    writer.append(String.valueOf(comprador.getCantidadBoletos())).append(",");
                    writer.append(String.valueOf(comprador.getPresupuesto())).append(",");
                    writer.append(ticket.getLocalidad().getNombre()).append(",");
                    writer.append(String.valueOf(ticket.getLocalidad().getPrecio())).append(",");
                    writer.append(new SimpleDateFormat("dd/MM/yyyy").format(ticket.getFechaCompra())).append(",");
                    writer.append(ticket.getNumeroTicket()).append("\n");
                }
            }
            System.out.println("Información guardada en informacion.csv");
        } catch (IOException e) {
            System.out.println("Error al guardar la información en CSV: " + e.getMessage());
        }
    }
}
