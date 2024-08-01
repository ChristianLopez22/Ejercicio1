public class Localidad {
    private String nombre;
    private int capacidad;
    private int capacidadDisponible;
    private double precio;
    private int correlativo;

    public Localidad(String nombre, int capacidad, double precio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.capacidadDisponible = capacidad;
        this.precio = precio;
        this.correlativo = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getCapacidadDisponible() {
        return capacidadDisponible;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean validarDisponibilidad(int cantidad) {
        return capacidadDisponible >= cantidad;
    }

    public void venderBoletos(int cantidad) {
        if (validarDisponibilidad(cantidad)) {
            capacidadDisponible -= cantidad;
        }
    }

    public int incrementarCorrelativo() {
        return correlativo++;
    }
}
