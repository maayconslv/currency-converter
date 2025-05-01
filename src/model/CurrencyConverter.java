package model;

public class CurrencyConverter {

    private double converter(double value, double rate) {
        return value * rate;
    }

    public void printConvertion(double value, double rate, String base, String destiny) {
        double result = this.converter(value, rate);
        System.out.printf("%.2f %s equivalem a %.2f %s\n", value, base, result, destiny);
    }
}
