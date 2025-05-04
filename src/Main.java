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
        ExchangeRateApiClient api = new ExchangeRateApiClient();
        ExchangeRateApiResponse response;

        String baseCurrencyCode;
        while(true) {
            System.out.println("Qual moeda você quer usar como base? Use o formato BRL, USD...");
            System.out.println("Digite 1 para listar todas as moedas disponíveis.");

            baseCurrencyCode = scanner.next().toUpperCase();

            if (baseCurrencyCode.equals("1")) {
                System.out.println("buscando a lista de moedas...");
                try {
                    response = api.request("USD");
                    System.out.println("Moedas disponíveis:");
                    for (String currency : response.conversion_rates().keySet()) {
                        System.out.print(currency + " ");
                    }
                    System.out.println("\n");
                    System.out.println("Agora, informe novamente a moeda base:");
                    baseCurrencyCode = scanner.next().toUpperCase();
                } catch (Exception e) {
                    System.out.println("Erro ao obter a lista de moedas: "  + e);
                    return;
                }
            }

            try {
                System.out.println("moeda base escolhida foi: " + baseCurrencyCode);
                break;
            } catch (Exception e) {
                System.out.println("Moeda inválida ou erro ao buscar dados. Tente novamente.");
            }
        }

        System.out.println("Qual o valor você deseja converter?");
        double amountToConvert = scanner.nextDouble();

        String targetCurrencyCode;
        while (true) {
            System.out.println("Para qual moeda você quer converter?");
            System.out.println("Digite 1 para listar todas as moedas disponíveis.");

            targetCurrencyCode = scanner.next().toUpperCase();
            if (targetCurrencyCode.equals("1")) {
                try {
                    response = api.request("USD");
                    System.out.println("Moedas disponíveis:");
                    for (String currency : response.conversion_rates().keySet()) {
                        System.out.print(currency + " ");
                    }
                    System.out.println("\n");
                    System.out.println("Agora, informe novamente a moeda para qual você quer converter:");
                    targetCurrencyCode = scanner.next().toUpperCase();
                } catch (Exception e) {
                    System.out.println("Erro ao obter a lista de moedas: "  + e);
                    return;
                }
            }

            try {
                response = api.request(baseCurrencyCode);

                if (!validateConversionRates(response, targetCurrencyCode)) {
                    return;
                }

                double exchangeRate = response.conversion_rates().get(targetCurrencyCode);
                CurrencyConverter currency = new CurrencyConverter();

                currency.printConvertion(amountToConvert, exchangeRate, baseCurrencyCode, targetCurrencyCode);
                break;
            } catch (Exception e) {
                System.out.println("Erro ao realizar a conversão: " + e.getMessage());
            }
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