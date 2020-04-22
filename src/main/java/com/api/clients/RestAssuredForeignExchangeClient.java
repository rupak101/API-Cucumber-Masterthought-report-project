package com.api.clients;

import com.api.model.CurrencyExchangeResponse;
import com.api.model.RealtimeCurrencyExchangeResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.api.RestAssuredHelper.statusMatcherFor;
import static io.restassured.RestAssured.given;

public class RestAssuredForeignExchangeClient extends RestAssuredClient {

    private final String GET_FOREIGN_EXCHANGE_ERROR_MESSAGE = "Foreign exchange error message";

    public RequestSpecification setBaseUrlAndParam(String function, String fromCurrency, String toCurrency) {
        return given()
                .spec(getBaseSpec())
                .queryParam("function", function)
                .queryParam("from_currency", fromCurrency)
                .queryParam("to_currency", toCurrency)
                .queryParam("apikey", apiKey1);
    }

    public RequestSpecification setBaseUrlAndParamForCurrencySymbol(String function, String fromSymbol, String toSymbol, String interval) {
        return given()
                .spec(getBaseSpec())
                .queryParam("function", function)
                .queryParam("from_symbol", fromSymbol)
                .queryParam("to_symbol", toSymbol)
                .queryParam("interval", interval)
                .queryParam("apikey", apiKey1);
    }

    public RequestSpecification setBaseUrlAndParamForCurrencySymbol(String function, String fromSymbol, String toSymbol) {
        return given()
                .spec(getBaseSpec())
                .queryParam("function", function)
                .queryParam("from_symbol", fromSymbol)
                .queryParam("to_symbol", toSymbol)
                .queryParam("apikey", apiKey1);
    }

    public Response getForeignExchange(RequestSpecification request) {
        return request
                .when()
                .get();
    }

    public RealtimeCurrencyExchangeResponse getRealTimeForeignExchangeAsExtractedResponse(Response response, int statusCode) {
        return response
                .then()
                .assertThat()
                .statusCode(
                        statusMatcherFor(statusCode, GET_FOREIGN_EXCHANGE_ERROR_MESSAGE)
                )
                .extract()
                .as(RealtimeCurrencyExchangeResponse.class);
    }

    public CurrencyExchangeResponse getForeignExchangeAsExtractedResponse(Response response, int statusCode) {
        return response
                .then()
                .assertThat()
                .statusCode(
                        statusMatcherFor(statusCode, GET_FOREIGN_EXCHANGE_ERROR_MESSAGE)
                )
                .extract()
                .as(CurrencyExchangeResponse.class);
    }
}
