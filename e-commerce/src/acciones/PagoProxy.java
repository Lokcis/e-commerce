package acciones;

/**
 *
 * @author lokci
 */
public class PagoProxy {
    private Pago pagoReal;
    private Cliente cliente;

    public PagoProxy(Pago pagoReal, Cliente cliente) {
        this.pagoReal = pagoReal;
        this.cliente = cliente;
    }

    public void calcularTotal(double subtotal, double impuestos) {
        pagoReal.calcularTotal(subtotal, impuestos);
    }

    public void verificarMetodoPago() {
        if (pagoReal.getMetodoPago().equals("tarjeta") || pagoReal.getMetodoPago().equals("efectivo")) {
            if (cliente.getSaldo() >= pagoReal.getTotal()) {
                pagoReal.verificarMetodoPago();
            } else {
                throw new IllegalArgumentException("El cliente " + cliente.getNombre() + " no tiene suficiente saldo para realizar el pago");
            }
        } else {
            throw new IllegalArgumentException("El método de pago " + pagoReal.getMetodoPago() + " no es válido");
        }
    }

    public double getTotal() {
        return pagoReal.getTotal();
    }

    public String getMetodoPago() {
        return pagoReal.getMetodoPago();
    }
}