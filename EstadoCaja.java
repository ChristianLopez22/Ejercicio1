public class EstadoCaja {
    public void generarReporte(Localidad[] localidades, int indiceLocalidades) {
        double total = 0;
        for (int i = 0; i < indiceLocalidades; i++) {
            int vendidos = localidades[i].getCapacidad() - localidades[i].getCapacidadDisponible();
            double ingresos = vendidos * localidades[i].getPrecio();
            total += ingresos;
            System.out.println(localidades[i].getNombre() + ": " + vendidos + " boletos vendidos, Ingresos: $" + ingresos);
        }
        System.out.println("Total Ingresos: $" + total);
    }
}
