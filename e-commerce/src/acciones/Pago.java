package acciones;

import java.util.List;

/**
 *
 * @author lokci
 */
public class Pago {
    private double total;
    private String metodoPago;
    private List<Observador> observadores;

    public Pago(double total, String metodoPago, List<Observador> observadores) {
        this.total = total;
        this.metodoPago = metodoPago;
        this.observadores = observadores;
    }

    public void calcularTotal(double subtotal, double impuestos) {
        this.total = subtotal + impuestos;
        notificarObservadores("El total ha sido calculado: " + this.total);
    }

    public void verificarMetodoPago() {
        if (this.metodoPago.equals("tarjeta") || this.metodoPago.equals("efectivo")) {
            notificarObservadores("El método de pago " + this.metodoPago + " ha sido verificado");
        } else {
            notificarObservadores("El método de pago " + this.metodoPago + " no es válido");
        }
    }

    public double getTotal() {
        return this.total;
    }

    public String getMetodoPago() {
        return this.metodoPago;
    }

    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar();
        }
    }
}