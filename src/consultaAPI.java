import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;

public class consultaAPI {

    public static void conversorMoneda(String primeraMoneda, String monedaFinal, double cantidad) {
        try {
            String direccion = "https://v6.exchangerate-api.com/v6/6849243e7081b1f20c5ee92f/latest/" + primeraMoneda;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            String jsonResponse = response.body();

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(jsonResponse, JsonObject.class);
            JsonObject rates = json.getAsJsonObject("conversion_rates");

            if (!rates.has(monedaFinal)) {
                throw new IllegalArgumentException("La moneda de a la que desea convertir no es válida.");
            }

            double tasaMonedaDestino = rates.get(monedaFinal).getAsDouble();
            double resultado = cantidad * tasaMonedaDestino;


            DecimalFormat df = new DecimalFormat("#.###");
            String resultadoFormateado = df.format(resultado);
            System.out.println(cantidad + " " + primeraMoneda + " es igual a " + resultadoFormateado + " " + monedaFinal);


        } catch (IOException | InterruptedException e) {
            System.out.println("Error en la conversión: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
