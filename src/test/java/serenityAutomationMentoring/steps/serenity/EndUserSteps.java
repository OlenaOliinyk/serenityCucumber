package serenityAutomationMentoring.steps.serenity;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;

import static net.serenitybdd.rest.SerenityRest.then;

import static org.hamcrest.core.Is.is;
import static serenityAutomationMentoring.steps.GetOrderById.*;


public class EndUserSteps {
    private static Logger log = LoggerFactory.getLogger(EndUserSteps.class);
    private static final String ACTUAL_RESPONSE_STATUS_CODE = "actual.response.status.code";

    @Step
    public void givenAction() {
        given()
                .accept(ContentType.JSON);
    }

    @Step
    public void sendGetRequestAction() {

        Response response = getOrderNegativeRequest();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));

    }

    @Step
    public void sendGetRequestWithParamAction(final int orderId) {
        Response response = getOrderPositiveRequestWithParam(orderId);
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
    }

    @Step
    public void sendPostRequestAction() {

        Response response = postOrderNegativeRequestWithMissedParam();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));

    }

    @Step
    public void sendPostRequestWithWrongParamAction() {

        Response response = postOrderNegativeRequestWithWrongParam();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));

    }

    @Step
    public void sendPostRequestWithParametrsAction(final int id, final int petId, final int quantity) {

        Response response = postOrderPositiveRequestWithParametrs(id, petId, quantity);
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));

    }

    @Step
    public void verifyStatusCodeAction(final String statusCode) {
        Assert.assertThat(
                "Wrong status code in response.",
                Serenity.sessionVariableCalled(ACTUAL_RESPONSE_STATUS_CODE).toString(),
                is(statusCode));
        log.info(statusCode + ": status code is expected");
    }


    @Step
    public void verifyIdAction(final int id) {
        then().body("id", is(id));
        log.info(id + " id is expected");
    }

    @Step
    public void verifyTypeAction(final String errorType) {
        then().body("type", is(errorType));
        log.info(errorType + " type of order in response ");
    }

    @Step
    public void verifyMessageAction(final String message) {
        then().body("message", is(message));
        log.info("\'" + message + "\'" + " message in response for post order with wrong request");
    }
}