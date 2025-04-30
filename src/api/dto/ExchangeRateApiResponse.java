package api.dto;

import java.util.Map;

public record ExchangeRateApiResponse(String base_code, Map<String, Double> conversion_rates) {}
