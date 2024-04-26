package Modelos;

public class Convertidor {

//    metodos
    public double convertir_a_Dolar(Moneda moneda, double cantidad) {
        Double precio = moneda.getPrecio();
        return precio * cantidad;
    }

    public double dolar_convertir_moneda(Moneda moneda, double cantidad) {
        Double precio = moneda.getPrecio();
        return cantidad / precio;

    }

}
