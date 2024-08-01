import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Comprador {
    private String nombre;
    private String telefono;
    private int cantidadBoletos;
    private double presupuesto;
    private ArrayList<Ticket> tickets;

    public Comprador(String nombre, String telefono, int cantidadBoletos, double presupuesto) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.cantidadBoletos = cantidadBoletos;
        this.presupuesto = presupuesto;
        this.tickets = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public boolean validarCompra(Localidad localidad) {
        double precioTotal = cantidadBoletos * localidad.getPrecio();
        if (cantidadBoletos > 100) {
            System.out.println("No puede comprar más de 100 boletos.");
            return false;
        }
        if (precioTotal > presupuesto) {
            System.out.println("Presupuesto insuficiente.");
            return false;
        }
        if (!localidad.validarDisponibilidad(cantidadBoletos)) {
            System.out.println("No hay suficientes boletos disponibles.");
            return false;
        }
        return true;
    }

    public void realizarCompra(Localidad localidad) {
        if (validarCompra(localidad)) {
            localidad.venderBoletos(cantidadBoletos);
            for (int i = 0; i < cantidadBoletos; i++) {
                String numeroTicket = generarNumeroTicket(new Date(), localidad.incrementarCorrelativo());
                tickets.add(new Ticket(localidad, new Date(), numeroTicket));
                System.out.println("Ticket generado: " + numeroTicket);
            }
            System.out.println("Compra realizada exitosamente.");
        }
    }

    private String generarNumeroTicket(Date fechaCompra, int correlativo) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
        String fechaFormateada = formatter.format(fechaCompra);
        return fechaFormateada + String.format("%04d", correlativo);
    }
}

