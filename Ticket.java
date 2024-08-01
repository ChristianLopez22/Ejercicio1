import java.util.Date;

public class Ticket {
    private Localidad localidad;
    private Date fechaCompra;
    private String numeroTicket;

    public Ticket(Localidad localidad, Date fechaCompra, String numeroTicket) {
        this.localidad = localidad;
        this.fechaCompra = fechaCompra;
        this.numeroTicket = numeroTicket;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public String getNumeroTicket() {
        return numeroTicket;
    }
}

