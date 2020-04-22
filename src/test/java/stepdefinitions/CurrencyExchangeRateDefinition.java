package stepdefinitions;

import com.api.clients.RestAssuredForeignExchangeClient;
import com.api.model.CurrencyExchangeResponse;
import com.api.model.MetaDataForCurrencyExchange;
import com.api.model.RealtimeCurrencyExchangeRate;
import com.api.model.RealtimeCurrencyExchangeResponse;
import com.framework.libraries.LogUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rupak Mansingh this is execution class currency exchange API, valid
 * cucumber glue codes for the feature file
 */
public class CurrencyExchangeRateDefinition {

    Response response;
    RequestSpecification request;
    RealtimeCurrencyExchangeRate realtimeCurrencyExchangeRate;
    CurrencyExchangeResponse currencyExchangeResponse;
    MetaDataForCurrencyExchange metaDataForCurrencyExchange;
    RealtimeCurrencyExchangeResponse realtimeCurrencyResponse;
    RestAssuredForeignExchangeClient restAssuredForeignExchangeClient = new RestAssuredForeignExchangeClient();
    private static final LogUtils LOGGER = new LogUtils(CurrencyExchangeRateDefinition.class);

    @Given("^set base url and add query param \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" for real currency exchange$")
    public void set_base_url_and_add_query_param_for_real_currency_exchange(String function, String fromCurrency, String toCurrency) {
        LOGGER.info("********** Get real currency exchange test started ***********\n");
        request = restAssuredForeignExchangeClient.setBaseUrlAndParam(function, fromCurrency, toCurrency);
    }

    @When("^user retrieves the real time currency exchange rate$")
    public void user_retrieves_the_real_time_currency_exchange_rate() {
        response = restAssuredForeignExchangeClient.getForeignExchange(request);
    }

    @Then("^validate status code is (\\d+) for real time currency exchange rate$")
    public void validate_status_code_is_for_real_time_currency_exchange_rate(int statusCode) {
        realtimeCurrencyResponse = restAssuredForeignExchangeClient.getRealTimeForeignExchangeAsExtractedResponse(response, statusCode);
    }

    @Then("^response includes the following \"([^\"]*)\", \"([^\"]*)\" for real time exchange rate$")
    public void response_includes_the_following_for_real_time_exchange_rate(String fromCurrency, String toCurrency) {
        realtimeCurrencyExchangeRate = realtimeCurrencyResponse.getRealtimeCurrencyExchangeRate();

        assertThat("From currency code didn't show correctly", realtimeCurrencyExchangeRate.getFromCurrencyCode(), is(fromCurrency));
        assertThat("To currency code didn't show correctly", realtimeCurrencyExchangeRate.getToCurrencyCode(), is(toCurrency));
        assertThat("Exchange rate is null", realtimeCurrencyExchangeRate.getExchangeRate(), is(notNullValue()));
        assertThat("Last refreshed is null", realtimeCurrencyExchangeRate.getLastRefreshed(), is(notNullValue()));
        assertThat("Time zone didn't show correctly", realtimeCurrencyExchangeRate.getTimeZone(), is("UTC"));
        assertThat("Bid price is null", realtimeCurrencyExchangeRate.getBidPrice(), is(notNullValue()));
        assertThat("Ask price is null", realtimeCurrencyExchangeRate.getAskPrice(), is(notNullValue()));
        LOGGER.info("validate the response successfully\n");
    }

    @Given("^set base url and add query param \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" for currency exchange$")
    public void set_base_url_and_add_query_param_for_currency_exchange(String function, String fromSymbol, String toSymbol, String interval) {
        LOGGER.info("********** Get currency exchange rate for 5 minute interval test started ***********\n");
        request = restAssuredForeignExchangeClient
                .setBaseUrlAndParamForCurrencySymbol(function, fromSymbol, toSymbol, interval);
    }

    @When("^user retrieves the real time currency exchange rate by every five minutes$")
    public void user_retrieves_the_real_time_currency_exchange_rate_by_every_five_minutes() {
        response = restAssuredForeignExchangeClient.getForeignExchange(request);
    }

    @Given("^set base url and add query param \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" for currency exchange$")
    public void set_base_url_and_add_query_param_for_currency_exchange(String function, String fromSymbol, String toSymbol) {
        LOGGER.info("********** Get currency exchange rate for daily and weekly interval test started ***********\n");
        request = restAssuredForeignExchangeClient.setBaseUrlAndParamForCurrencySymbol(function, fromSymbol, toSymbol);
    }

    @When("^user retrieves the currency exchange rate by daily and weekly$")
    public void user_retrieves_the_currency_exchange_rate_by_daily_and_weekly() {
        response = restAssuredForeignExchangeClient.getForeignExchange(request);
    }

    @Then("^validate status code is (\\d+) for currency exchange rate$")
    public void validate_status_code_is_for_currency_exchange_rate(int statusCode) {
        currencyExchangeResponse = restAssuredForeignExchangeClient.getForeignExchangeAsExtractedResponse(response, statusCode);
    }

    @Then("^response includes the following \"([^\"]*)\", \"([^\"]*)\" for exchange rate$")
    public void response_includes_the_following_for_exchange_rate(String fromSymbol, String toSymbol) {
        metaDataForCurrencyExchange = currencyExchangeResponse.getMetaDataForCurrencyExchange();

        assertThat("Information is null", metaDataForCurrencyExchange.getInformation(), is(notNullValue()));
        assertThat("From symbol didn't show correctly", metaDataForCurrencyExchange.getFromSymbol(), is(fromSymbol));
        assertThat("To symbol didn't show correctly", metaDataForCurrencyExchange.getToSymbol(), is(toSymbol));
        LOGGER.info("validate the response successfully\n");
    }
}
