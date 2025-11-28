package lad.com.alura.conversormoneda;

import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {

        //Creamos el Scanner
        Scanner lector = new Scanner(System.in);
        //Creamos la consulta
        ConsultaApi consulta = new ConsultaApi();

        System.out.println("********************************\n\n");
        System.out.println("Bienvenido al mejor conversor de monedas");
        System.out.println("Ingrese el valor a convertir:");
        double valor = lector.nextDouble();

        System.out.println("Seleccione la moneda de origen:");
        System.out.println("1 - ARS");
        System.out.println("2 - USD");
        System.out.println("3 - BRL");
        int origenNum = lector.nextInt();

        System.out.println("Seleccione la moneda destino:");
        System.out.println("1 - ARS");
        System.out.println("2 - USD");
        System.out.println("3 - BRL");
        int destinoNum = lector.nextInt();

        // Convertimos números a códigos de moneda
        String origen = convertirCodigo(origenNum);
        String destino = convertirCodigo(destinoNum);

        // Llamamos a la API
        RespuestaApi respuesta = consulta.obtenerTasa(origen, destino);

        // Calculamos el monto convertido
        if (respuesta != null) {
            double tasa = respuesta.conversion_rate();
            double convertido = valor * tasa;

            System.out.println("\nTasa: " + tasa);
            System.out.println("Resultado: " + convertido + " " + destino);
        }
    }


    private static String convertirCodigo(int opcion) {
        return switch (opcion) {
            case 1 -> "ARS";
            case 2 -> "USD";
            case 3 -> "BRL";
            default -> "USD";
        };
    }
}
