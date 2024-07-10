import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Principal {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("*** Conversor de monedas ***");
            System.out.println("ARS - Peso argentino");
            System.out.println("BOB - Peso boliviano");
            System.out.println("BRL - Real brasileño");
            System.out.println("CLP - Peso chileno");
            System.out.println("COP - Peso colombiano");
            System.out.println("USD - Dólar estadounidense");
            System.out.println("MXN - Peso mexicano");
            System.out.println("EUR - Euro");
            System.out.println("Salir - Finalizar programa");
            try {
                System.out.print("Ingrese la moneda de origen: ");
                String primeraMoneda = sc.nextLine().toUpperCase();
                if (primeraMoneda.equals("SALIR")) {
                    continuar = false;
                    break;
                }

                System.out.print("Ingrese la moneda de salida: ");
                String monedaFinal = sc.nextLine().toUpperCase();
                if (monedaFinal.equals("SALIR")) {
                    continuar = false;
                    break;
                }

                if (primeraMoneda.isEmpty() || monedaFinal.isEmpty() || primeraMoneda.length() != 3 || monedaFinal.length() != 3) {
                    throw new IllegalArgumentException("Código de moneda no válido. Debe ser de 3 caracteres.");
                }

                System.out.print("Ingrese la cantidad que deseas cambiar: ");
                double cantidad = sc.nextDouble();
                sc.nextLine();

                if (cantidad <= 0) {
                    throw new IllegalArgumentException("La cantidad debe ser mayor o igual a cero.");
                }

                consultaAPI.conversorMoneda(primeraMoneda, monedaFinal, cantidad);
                System.out.print("\n¿Deseas hacer otra conversión? (si/no): ");
                String respuesta = sc.nextLine().toLowerCase();
                if (!respuesta.equals("si")) {
                    continuar = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingresa un valor mostrado en el menú.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
}
