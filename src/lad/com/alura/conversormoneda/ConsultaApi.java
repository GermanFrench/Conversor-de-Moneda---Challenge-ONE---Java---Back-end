
package lad.com.alura.conversormoneda;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {

    //Declaro dos variables para la API_KEY y la URL_BASE por cuestiones de seguridad y escalabilidad

    private final String API_KEY = "0f362cdd1b983e19c0e4ebb7";
    private final String URL_BASE = "https://v6.exchangerate-api.com/v6/";

    //Hacemos la consulta a la API

    public RespuestaApi obtenerTasa(String origen, String destino) {
        String url = URL_BASE + API_KEY + "/pair/" + origen + "/" + destino;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Convertimos JSON -> Objeto Java
            return new Gson().fromJson(response.body(), RespuestaApi.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consultar la API: " + e.getMessage());
        }
    }
}
