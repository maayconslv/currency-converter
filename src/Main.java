import api.ExchangeRateApiClient;
import api.dto.ExchangeRateApiResponse;
import model.Currency;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int hasLoop = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Qual moeda você quer usar como base? Use o formato BRL, USD...");
            String baseCurrency = scanner.next();

            System.out.println("Qual o valor você deseja converter?");
            double valueToConvert = scanner.nextDouble();

            System.out.println("Para qual moeda você quer converter? Use o formato BRL, USD...");
            String currencyToConvert = scanner.next();

            try {
                ExchangeRateApiClient api = new ExchangeRateApiClient();
                ExchangeRateApiResponse response = api.request(baseCurrency);


                Currency currency = new Currency();
                currency.converter(valueToConvert, response.conversion_rates().get(currencyToConvert), baseCurrency);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Digite 0 para continuar e 1 para sair");
            hasLoop = scanner.nextInt();
        } while (hasLoop == 0);

        System.out.println("Programa encerrado.");
        scanner.close();

    }
}