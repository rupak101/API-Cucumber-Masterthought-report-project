package com.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyExchangeResponse {

    @JsonProperty("Meta Data")
    private MetaDataForCurrencyExchange metaDataForCurrencyExchange;
}
