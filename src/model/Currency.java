package model;

public class Currency {
    private int currency;
    private String baseCode;

    public void converter(double value, double multiplier, String currency) {
        double multipliedValue = value * multiplier;

        System.out.println("R$" + value + " convertido para " + currency +  "é " + multipliedValue);
    }
}
