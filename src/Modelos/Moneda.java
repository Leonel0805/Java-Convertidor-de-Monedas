package Modelos;

import java.util.Map;

public class Moneda {

    private String name;
    private Double precio;

    public Moneda(MonedaRecord monedarecord, String name) {
        Map<String, Double> conversionRates = monedarecord.conversion_rates();
        this.name = name;
        System.out.println(this.name);
        this.precio = conversionRates.get(this.name);
    }

    public String getName() {
        return name;
    }

    public Double getPrecio() {
        return precio;
    }

}
