package serenityAutomationMentoring.steps.serenity;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.*;

import static net.serenitybdd.rest.SerenityRest.then;

import static org.hamcrest.core.Is.is;
import static serenityAutomationMentoring.steps.GetOrderById.*;


public class EndUserSteps {

    public static final String ACTUAL_RESPONSE_STATUS_CODE = "actual.response.status.code";
    public static final String ACTUAL_RESPONSE_JSON = "actual.response.json";


    @Step
    public void givenAction() {
        given()
                .accept(ContentType.JSON);
    }

    @Step
    public void sendGetRequestAction() {

        Response response = getOrderNegativeRequest();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());
    }
    @Step
    public void sendGetRequestWithParamAction(final int orderId) {

        Response response = getOrderPositiveRequestWithParam(orderId);
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());
    }
    @Step
    public void sendPostRequestAction() {

        Response response = postOrderNegativeRequestWithMissedParam();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());

    }
    @Step
    public void sendPostRequestWithWrongParamAction() {

        Response response = postOrderNegativeRequestWithWrongParam();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
       Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());

    }
    @Step
    public void sendPostRequestWithParametrsAction(final int id, final int petId, final int quantity) {

        Response response = postOrderPositiveRequestWithParametrs(id,petId,quantity);
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());

    }


    @Step
    public void verifyStatusCodeAction(final String statusCode) {
        Assert.assertThat(
                "Wrong status code in response.",
                Serenity.sessionVariableCalled(ACTUAL_RESPONSE_STATUS_CODE).toString(),
                is(statusCode));
        System.out.println(statusCode + ": status code is expected");
    }


    @Step
    public void verifyIdAction(final int id) {
        then().body("id",is(id));
        System.out.println(id+" id is expected");
    }
    @Step
    public void verifyTypeAction(final String errorType) {
        then().body("type",is(errorType));
        System.out.println(errorType +" type of order in response ");
    }
    @Step
    public void verifyMessageAction(final String message) {
        then().body("message",is(message));
        System.out.println(message +" message order in response");
        System.out.println("test is completed -------------------");
    }
}