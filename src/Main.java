import api.ExchangeRateApiClient;
import api.dto.ExchangeRateApiResponse;
import model.CurrencyConverter;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hasLoop = 0;

        do {
            execConversion(scanner);
            hasLoop = askIfContinues(scanner);
        } while (hasLoop == 0);

        System.out.println("Programa encerrado.");
        scanner.close();
    }

    private static void execConversion(Scanner scanner) {
        System.out.println("Qual moeda você quer usar como base? Use o formato BRL, USD...");
        String baseCurrencyCode = scanner.next();

        System.out.println("Qual o valor você deseja converter?");
        double amountToConvert = scanner.nextDouble();

        System.out.println("Para qual moeda você quer converter? Use o formato BRL, USD...");
        String targetCurrencyCode = scanner.next();

        try {
            ExchangeRateApiClient api = new ExchangeRateApiClient();
            ExchangeRateApiResponse response = api.request(baseCurrencyCode);

            if (!validateConversionRates(response, targetCurrencyCode)) {
                return;
            }

            double exchangeRate = response.conversion_rates().get(targetCurrencyCode);
            CurrencyConverter currency = new CurrencyConverter();

            currency.printConvertion(amountToConvert, exchangeRate, baseCurrencyCode, targetCurrencyCode);
        } catch (Exception e) {
            System.out.println("Erro ao realizar a conversão: " + e.getMessage());
        }
    }

    private static int askIfContinues(Scanner scanner) {
        System.out.println("Digite 0 para continuar e 1 para sair");
        return scanner.nextInt();
    }

    private static boolean validateConversionRates(ExchangeRateApiResponse response, String targetCurrencyCode) {
        Map<String, Double> rates = response.conversion_rates();

        if (!rates.containsKey(targetCurrencyCode)) {
            System.out.println("Moeda de destino é inválida: " + targetCurrencyCode);
            return false;
        }

        return true;
    }
}