package model;

import java.util.function.BinaryOperator;

public class CurrencyConverter {
    public void printConvertion(double value, double rate, String base, String destiny) {
        BinaryOperator<Double> calc = (x, y) -> x * y;
        double result = calc.apply(value, rate);

        System.out.printf("%.2f %s equivalem a %.2f %s\n", value, base, result, destiny);
    }
}
