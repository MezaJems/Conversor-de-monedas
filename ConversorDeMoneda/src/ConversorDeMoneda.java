import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class ConversorDeMoneda {

    private static final String URL = "https://v6.exchangerate-api.com/v6/"
            + "59b99c480f3863e9bc7f0b76" +"/latest/";

    private String monedaOrigen;
    private String monedaDestino;
    private double monto;

    // Constructor
    public ConversorDeMoneda(String monedaOrigen, String monedaDestino, double monto) {
        this.monedaOrigen = monedaOrigen.toUpperCase();
        this.monedaDestino = monedaDestino.toUpperCase();
        this.monto = monto;
    }

    // metodo para otener tasa de cambio
    private double obtenerTasaCambio() {
        try {
            URL url = new URL(URL + monedaOrigen);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }
            reader.close();

            JsonObject json = JsonParser.parseString(String.valueOf(respuesta)).getAsJsonObject();
            return json.getAsJsonObject("conversion_rates").get(monedaDestino).getAsDouble();

        } catch (Exception e) {
            System.out.println("Error al obtener datos de la API: " + e.getMessage());
            return 0.0;
        }
    }

    // realizar la conversión
    public void convertirMoneda() {
        double tasaCambio = obtenerTasaCambio();
        if (tasaCambio > 0) {
            double montoConvertido = monto * tasaCambio;
            System.out.println("El monto convertido es: " + montoConvertido + " " + monedaDestino);
        } else {
            System.out.println("No se pudo obtener la tasa de cambio.");
        }
    }
}

