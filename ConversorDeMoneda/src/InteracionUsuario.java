import java.util.Scanner;

public class InteracionUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la moneda de un pais (Ejemplo: USD): ");
        String monedaOrigen = scanner.next();

        System.out.print("Ingrese la moneda de pais a convertir (Ejemplo: COP): ");
        String monedaDestino = scanner.next();

        System.out.print("Ingrese la cantidad a convertir: ");
        double monto = scanner.nextDouble();

        // instancia de Conversor De Moneda
        ConversorDeMoneda conversor = new ConversorDeMoneda(monedaOrigen, monedaDestino, monto);
        conversor.convertirMoneda(); // Ejecutar la conversión

        scanner.close();
    }
}