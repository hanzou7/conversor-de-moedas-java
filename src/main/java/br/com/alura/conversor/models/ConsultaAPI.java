package br.com.alura.conversor.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {

    private final String baseCode;
    private final String targetCode;
    private final double amount;

    public ConsultaAPI(ConversorDados dados) {
        this.baseCode = dados.base_code();
        this.targetCode = dados.target_code();
        this.amount = dados.conversion_result();
    }

    public String apiCall() {
        String apiKey = "YOUR-API-KEY";
        String address = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + baseCode + "/" + targetCode + "/" + amount;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();
        HttpResponse<String> response = null;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }

        String jsonFromApi = response.body();

        Gson gson = new GsonBuilder().create();
        var apiRequest = gson.fromJson(jsonFromApi, ConversorDados.class);

        return "O valor " + amount + " " +  apiRequest.base_code() + " corresponde ao valor de " + apiRequest.conversion_result() + " " +  apiRequest.target_code();
    }
}
