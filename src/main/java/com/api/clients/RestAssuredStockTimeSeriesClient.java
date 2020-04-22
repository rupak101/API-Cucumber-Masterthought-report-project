package com.api.clients;

import com.api.model.QuoteStockTimeResponse;
import com.api.model.StockTimeResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.api.RestAssuredHelper.statusMatcherFor;
import static io.restassured.RestAssured.given;

public class RestAssuredStockTimeSeriesClient extends RestAssuredClient {

    private static final String GET_STOCK_TIME_SERIES_ERROR_MESSAGE = "Stock time series error message";

    public RequestSpecification setBaseUrlAndParam(String function, String symbol, String interval) {
        return given()
                .spec(getBaseSpec())
                .queryParam("function", function)
                .queryParam("symbol", symbol)
                .queryParam("interval", interval)
                .queryParam("apikey", apiKey2);
    }

    public RequestSpecification setBaseUrlAndParam(String function, String symbol) {
        return given()
                .spec(getBaseSpec())
                .queryParam("function", function)
                .queryParam("symbol", symbol)
                .queryParam("apikey", apiKey2);
    }

    public Response getStock(RequestSpecification request) {
        return request
                .when()
                .get();
    }

    public StockTimeResponse getStockAsExtractedResponse(Response response, int statusCode) {
        return response
                .then()
                .assertThat()
                .statusCode(
                        statusMatcherFor(statusCode, GET_STOCK_TIME_SERIES_ERROR_MESSAGE)
                )
                .extract()
                .as(StockTimeResponse.class);
    }

    public QuoteStockTimeResponse getStockQuoteAsExtractedResponse(Response response, int statusCode) {
        return response
                .then()
                .assertThat()
                .statusCode(
                        statusMatcherFor(statusCode, GET_STOCK_TIME_SERIES_ERROR_MESSAGE)
                )
                .extract()
                .as(QuoteStockTimeResponse.class);
    }
}
