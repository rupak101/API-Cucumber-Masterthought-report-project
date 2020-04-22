package stepdefinitions;

import com.api.clients.RestAssuredStockTimeSeriesClient;
import com.api.model.GlobalQuote;
import com.api.model.MetaData;
import com.api.model.QuoteStockTimeResponse;
import com.api.model.StockTimeResponse;
import com.framework.libraries.LogUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.framework.Constants.Response.OUT_PUT_SIZE;
import static com.framework.Constants.Response.TIME_ZONE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * @author Rupak Mansingh this is execution class for stock time series API, valid
 * cucumber glue codes for the feature file
 */
public class StockTimeSeriesDefinition {

    Response response;
    RequestSpecification request;
    MetaData metaData;
    StockTimeResponse stockTimeResponse;
    QuoteStockTimeResponse quoteStockTimeResponse;
    GlobalQuote globalQuote;
    RestAssuredStockTimeSeriesClient restAssuredStockTimeSeriesClient = new RestAssuredStockTimeSeriesClient();
    private static final LogUtils LOGGER = new LogUtils(StockTimeSeriesDefinition.class);

    @Given("^set base url and add query param \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void set_base_url_and_add_query_param(String function, String symbol, String interval) {
        LOGGER.info("********** Stock time series for 5 minutes interval test started ***********\n");
        request = restAssuredStockTimeSeriesClient.setBaseUrlAndParam(function, symbol, interval);
    }

    @When("^user retrieves the stock details$")
    public void user_retrieves_the_stock_details() {
        response = restAssuredStockTimeSeriesClient.getStock(request);
    }

    @Then("^the status code is (\\d+)$")
    public void the_status_code_is(int statusCode) {
        stockTimeResponse = restAssuredStockTimeSeriesClient.getStockAsExtractedResponse(response, statusCode);
    }

    @Then("^validate response and \"([^\"]*)\", \"([^\"]*)\" for every five minutes stock details$")
    public void validate_response_and_for_every_five_minutes_stock_details(String symbol, String interval) throws Throwable {
        metaData = stockTimeResponse.getMetaData();

        assertThat("Information didn't show correctly", metaData.getInformation(), is(notNullValue()));
        assertThat("Symbol didn't show correctly", metaData.getSymbol(), is(symbol));
        assertThat("Last refresh is null", metaData.getLastRefreshed(), is(notNullValue()));
        assertThat("Interval didn't show correctly", metaData.getInterval(), is(interval));
        assertThat("Out put size didn't show correctly", metaData.getOutputSize(), is(OUT_PUT_SIZE));
        assertThat("Time zone didn't show correctly", metaData.getTimeZone(), is(TIME_ZONE));
        LOGGER.info("validate the response successfully\n");
    }

    @Given("^set base url and add query param \"([^\"]*)\",\"([^\"]*)\"$")
    public void set_base_url_and_add_query_param(String function, String symbol) {
        LOGGER.info("********** Stock time series for daily, weekly interval test started ***********\n");
        request = restAssuredStockTimeSeriesClient.setBaseUrlAndParam(function, symbol);
    }

    @Then("^validate response and \"([^\"]*)\" for daily and weekly stock details$")
    public void validate_response_and_for_daily_and_weekly_stock_details(String symbol) {
        metaData = stockTimeResponse.getMetaData();

        assertThat("Information didn't show correctly", metaData.getInformation(), is(notNullValue()));
        assertThat("Symbol didn't show correctly", metaData.getSymbol(), is(symbol));
        assertThat("Last refresh is null", metaData.getLastRefreshed(), is(notNullValue()));
        LOGGER.info("validate the response successfully\n");
    }

    @Then("^the status code is (\\d+) for quote end point$")
    public void the_status_code_is_for_quote_end_point(int statusCode) {
        quoteStockTimeResponse = restAssuredStockTimeSeriesClient.getStockQuoteAsExtractedResponse(response, statusCode);
    }

    @Then("^validate response and \"([^\"]*)\" for quote end point$")
    public void validate_response_and_for_quote_end_point(String symbol) {
        globalQuote = quoteStockTimeResponse.getGlobalQuote();

        assertThat("Symbol didn't show correctly", globalQuote.getSymbol(), is(symbol));
        assertThat("Last refresh is null", globalQuote.getOpen(), is(notNullValue()));
        assertThat("High quote value is null", globalQuote.getHigh(), is(notNullValue()));
        assertThat("High value is less than low value", globalQuote.getHigh(), greaterThan(globalQuote.getLow()));
        assertThat("Price value is null", globalQuote.getPrice(), is(notNullValue()));
        assertThat("Volume of information is null", globalQuote.getVolume(), is(notNullValue()));
        LOGGER.info("validate the response successfully\n");
    }
}
