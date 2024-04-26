package Main;

import Modelos.Convertidor;
import Modelos.Moneda;
import Modelos.MonedaRecord;
import Modelos.requestAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

//    Constantes
    private final static int DOLAR_ARG = 1;
    private final static int ARG_DOLAR = 2;
    private final static int DOLAR_BRL = 3;
    private final static int BRL_DOLAR = 4;
    private final static int DOLAR_COL = 5;
    private final static int COL_DOLAR = 6;
    private final static int SALIR = 0;

//    Main
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        requestAPI api = new requestAPI();
        ArrayList<Moneda> listamonedas_creadas = crear_monedas(api);

        Convertidor convertir = new Convertidor();

        int opc = -1;
        while (opc != SALIR) {
            menu();
            System.out.println("Elija una opción: ");
            opc = input.nextInt();

//          OPCIONES VALIDAS
            if (opc == DOLAR_ARG) {
                Double cantidad = pedir_cantidad();
                Moneda moneda = monedabuscar(listamonedas_creadas, "ARS");
                double resultado = convertir.convertir_a_Dolar(moneda, cantidad);
                System.out.println(resultado);

            } else if (opc == ARG_DOLAR) {
                Double cantidad = pedir_cantidad();
                Moneda moneda = monedabuscar(listamonedas_creadas, "ARS");
                double resultado = convertir.dolar_convertir_moneda(moneda, cantidad);
                System.out.println(resultado);

            } else if (opc == DOLAR_BRL) {
                Double cantidad = pedir_cantidad();
                Moneda moneda = monedabuscar(listamonedas_creadas, "BRL");
                double resultado = convertir.convertir_a_Dolar(moneda, cantidad);
                System.out.println(resultado);

            } else if (opc == BRL_DOLAR) {
                Double cantidad = pedir_cantidad();
                Moneda moneda = monedabuscar(listamonedas_creadas, "BRL");
                double resultado = convertir.dolar_convertir_moneda(moneda, cantidad);
                System.out.println(resultado);

            } else if (opc == DOLAR_COL) {
                Double cantidad = pedir_cantidad();
                Moneda moneda = monedabuscar(listamonedas_creadas, "COP");
                double resultado = convertir.convertir_a_Dolar(moneda, cantidad);
                System.out.println(resultado);

            } else if (opc == COL_DOLAR) {
                Double cantidad = pedir_cantidad();
                Moneda moneda = monedabuscar(listamonedas_creadas, "COP");
                double resultado = convertir.dolar_convertir_moneda(moneda, cantidad);
                System.out.println(resultado);

            } else if (opc == SALIR){
                System.out.println("Gracias por usar el programa, nos vemos");
            }
            else {
                System.out.println("Error al ingresar opción");
            }


        }
    }


//    Menu
    public static void menu() {
        System.out.println("**************************");
        System.out.println("          Menú            ");
        System.out.printf("""
                %d) Dólar >>> Peso argentino
                %d) Peso argentino >>> Dólar
                %d) Dólar >>> Real brasilero 
                %d) Real Brasilero >>> Dólar
                %d) Dólar >>> Peso colombiano
                %d) Peso colombiano >>> Dólar
                %d) Salir
                
                **************************
                """, DOLAR_ARG, ARG_DOLAR, DOLAR_BRL, BRL_DOLAR, DOLAR_COL, COL_DOLAR, SALIR);
    }

//    CREAMSO MONEDAS
    public static ArrayList<Moneda> crear_monedas(requestAPI api){

        ArrayList<Moneda> listaMonedas = new ArrayList<>();

        String response = null;
        try {
            response = api.request();

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(e);
        }

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        MonedaRecord monedaRecord = gson.fromJson(response, MonedaRecord.class);

//        Creamos monedas uan por una
        Moneda monedaARS = new Moneda(monedaRecord, "ARS");
        Moneda monedaBRL = new Moneda(monedaRecord, "BRL");
        Moneda monedaCOL = new Moneda(monedaRecord, "COP");

        listaMonedas.add(monedaARS);
        listaMonedas.add(monedaBRL);
        listaMonedas.add(monedaCOL);

        return listaMonedas;
    }

//    BUSCAR MONEDA
    public static Moneda monedabuscar(ArrayList<Moneda> listamonedas_creadas, String name) {

        Moneda monedaBuscada = null;
//        hacemos un for a la lista y buscamos el mismo nombre
        for (Moneda moneda : listamonedas_creadas) {
            if (moneda.getName().equals(name)) {
                monedaBuscada = moneda;
                break;
            }
        }
        return monedaBuscada;
    }

//    CANTIDAD
    public static double pedir_cantidad() {
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese cantidad a convertir: ");
        double cantidad = input.nextDouble();
        return cantidad;
    }
}