import api.ExchangeRateApiClient;
import api.dto.ExchangeRateApiResponse;
import model.Currency;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hasLoop = 0;

        do {
            executarConversao(scanner);
            hasLoop = askIfContinues(scanner);
        } while (hasLoop == 0);

        System.out.println("Programa encerrado.");
        scanner.close();
    }

    private static void executarConversao(Scanner scanner) {
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
            System.out.println("Erro ao realizar a conversão: " + e.getMessage());
        }
    }

    private static int askIfContinues(Scanner scanner) {
        System.out.println("Digite 0 para continuar e 1 para sair");
        return scanner.nextInt();
    }
}