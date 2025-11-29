package lad.com.alura.conversormoneda;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {

        boolean finalizar = true;
        DecimalFormat df = new DecimalFormat("#.######");
        Scanner lector = new Scanner(System.in);


        while (finalizar) {
            System.out.println("\n********************************");
            System.out.println("Bienvenido al mejor conversor de monedas!");
            System.out.println("********************************\n");

        System.out.println("********************************");
        System.out.println("Bienvenido al mejor conversor de monedas");
        System.out.println("Ingrese el valor a convertir:");
        double valor = lector.nextDouble();


            double valor = pedirValor(lector);

            int origenNum = pedirMoneda(lector, "origen");
            int destinoNum = pedirMoneda(lector, "destino");

            String origen = convertirCodigo(origenNum);
            String destino = convertirCodigo(destinoNum);

            ConsultaApi consulta = new ConsultaApi();
            RespuestaApi respuesta = consulta.obtenerTasa(origen, destino);

            if (respuesta != null) {
                double tasa = respuesta.conversion_rate();
                double convertido = valor * tasa;

                System.out.println("\nLa tasa es de: " + df.format(tasa));
                System.out.println("Resultado de la conversión: " + df.format(convertido) + " " + destino);
            }

            System.out.println("\n¿Desea realizar otra conversión?");
            System.out.println("4 - Sí");
            System.out.println("0 - No");

            int otra = lector.nextInt();

            finalizar = (otra == 4);
        }

        System.out.println("Gracias por utilizar nuestro sistema. ¡Hasta pronto!");
    }


    // VALIDACIÓN DE VALOR A CONVERTIR

    private static double pedirValor(Scanner lector) {
        double valor = -1;

        while (valor < 0) {
            System.out.println("Ingrese el valor a convertir (solo números, no negativos):");

            try {
                valor = lector.nextDouble();

                if (valor < 0) {
                    System.out.println("❌ Error: el número no puede ser negativo.");
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Error: debe ingresar un número válido.");
                lector.next(); // limpia la entrada inválida
            }
        }

        return valor;
    }


    // VALIDACIÓN DE OPCIÓN DE MONEDA

    private static int pedirMoneda(Scanner lector, String tipo) {
        int opcion = -1;

        while (opcion < 1 || opcion > 3) {
            System.out.println("\nSeleccione la moneda de " + tipo + ":");
            System.out.println("1 - ARS");
            System.out.println("2 - USD");
            System.out.println("3 - BRL");

            try {
                opcion = lector.nextInt();
                if (opcion < 1 || opcion > 3) {
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: debe ingresar un número (1, 2 o 3).");
                lector.next();
            }
        }

        return opcion;
    }


    // CONVERSIÓN DE NÚMERO → CÓDIGO DE MONEDA

    private static String convertirCodigo(int opcion) {
        return switch (opcion) {
            case 1 -> "ARS";
            case 2 -> "USD";
            case 3 -> "BRL";
            default -> "";
        };
    }
}
