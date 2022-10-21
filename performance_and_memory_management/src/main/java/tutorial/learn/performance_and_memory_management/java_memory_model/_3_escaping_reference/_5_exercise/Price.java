package tutorial.learn.performance_and_memory_management.java_memory_model._3_escaping_reference._5_exercise;

import java.util.HashMap;
import java.util.Map;

public class Price {

    private Map<String, Double> rates;

    private Double value;

    public Price(Double value) {
        this.value = value;
        rates = new HashMap<>();
        rates.put("USD", 1d);
        rates.put("GBP", 0.6);
        rates.put("EUR", 0.8);
    }

    public Double convert(String toCurrency) {

        if (!toCurrency.equals("USD")) {
            Double conversion = rates.get("USD") * rates.get(toCurrency);
            value = conversion * value;
        }
        return value;
    }

    public String toString() {
        return this.value.toString();
    }

    /**
     * preventing escaping reference
     */
    public Map<String, Double> getRates() {
        return Map.copyOf(rates);
    }
}
