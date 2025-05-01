package api;

import api.dto.ExchangeRateApiResponse;
import api.exceptions.InvalidCurrencyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateApiClient {
    private final String baseUrl;
    private final String apiKey;

    public ExchangeRateApiClient() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        this.baseUrl = dotenv.get("EXCHANGE_RATE_API_BASE_URL");
        this.apiKey = dotenv.get("EXCHANGE_RATE_API_KEY");

        if (baseUrl == null | apiKey == null) {
            throw new IllegalStateException(
                    "Variáveis de ambiente obrigatórias não encontradas: " +
                    "EXCHANGE_RATE_API_BASE_URL e/ou EXCHANGE_RATE_API_KEY. " +
                    "Confira a configuração em: https://github.com/maayconslv/currency-converter"
            );
        }
    }

    public ExchangeRateApiResponse request(String baseCurrency) throws IOException, InterruptedException {
        String response = this.privateRequest(baseCurrency);
        Gson gson = new GsonBuilder().create();

        ExchangeRateApiResponse result = gson.fromJson(response, ExchangeRateApiResponse.class);

        if(result.conversion_rates() == null) {
            throw new InvalidCurrencyException("Não há taxas disponiveis para a moeda base");
        }

        return result;
    }

    private String privateRequest(String baseCurrency) throws IOException, InterruptedException {
        String urlString = this.baseUrl + "/" + this.apiKey + "/latest/" + baseCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
