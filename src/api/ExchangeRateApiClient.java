package api;

import api.dto.ExchangeRateApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateApiClient {
    public ExchangeRateApiResponse request(String baseCurrency) throws IOException, InterruptedException {
        String response = this.privateRequest(baseCurrency);
        Gson gson = new GsonBuilder().create();

        return gson.fromJson(response, ExchangeRateApiResponse.class);
    }

    private String privateRequest(String baseCurrency) throws IOException, InterruptedException {
        String url_str = "https://v6.exchangerate-api.com/v6/edf5b6c3d6d1db529e38cfd1/latest/" + baseCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url_str))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
