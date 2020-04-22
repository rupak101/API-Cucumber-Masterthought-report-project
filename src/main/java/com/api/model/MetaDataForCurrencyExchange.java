package com.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDataForCurrencyExchange {

    @JsonProperty("1. Information")
    private String information;

    @JsonProperty("2. From Symbol")
    private String fromSymbol;

    @JsonProperty("3. To Symbol")
    private String toSymbol;

    @JsonProperty("4. Last Refreshed")
    private String lastRefreshed;

    @JsonProperty("5. Interval")
    private String interval;

    @JsonProperty("6. Output Size")
    private String outputSize;

    @JsonProperty("7. Time Zone")
    private String timeZone;
}
