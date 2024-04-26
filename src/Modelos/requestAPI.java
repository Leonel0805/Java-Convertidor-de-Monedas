package Modelos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class requestAPI {

//    peticion
    public String request() throws IOException, InterruptedException {
        String url = ("https://v6.exchangerate-api.com/v6/9a7d2332b181ddc531448a5e/latest/USD");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        retornamos el json
        return response.body();
    }
}
