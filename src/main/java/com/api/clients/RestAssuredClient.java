package com.api.clients;

import com.framework.libraries.FileReaderLibrary;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.framework.Constants.ApiKey.API_KEY1;
import static com.framework.Constants.ApiKey.API_KEY2;
import static io.restassured.http.ContentType.JSON;

public class RestAssuredClient {

    protected String baseUrl = FileReaderLibrary.getInstance().getConfigReader().getBaseUrl();
    protected String apiKey1 = FileReaderLibrary.getInstance().getConfigReader().getApiKey(API_KEY1);
    protected String apiKey2 = FileReaderLibrary.getInstance().getConfigReader().getApiKey(API_KEY2);

    /**
     * @return RequestSpecification containing
     * base URL,
     * JSON content type,
     */
    protected RequestSpecification getBaseSpec() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(baseUrl);
        return specBuilder
                .build();
    }
}