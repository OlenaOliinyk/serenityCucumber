package serenityAutomationMentoring.steps.serenity;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static serenityAutomationMentoring.EnvironmentPropertyLoader.getProperty;
import static serenityAutomationMentoring.TestSessionVariables.*;

import static serenityAutomationMentoring.steps.GetOrderById.getOrderPositiveRequest;
import static serenityAutomationMentoring.steps.GetOrderById.postOrderPositiveRequest;


public class EndUserSteps {

    public static final String ACTUAL_RESPONSE_STATUS_CODE = "actual.response.status.code";

    @Step
    public void givenAction() {
        given()
                .accept(ContentType.JSON);
    }

    @Step
    public void sendGetRequestAction() {
        System.out.println("send request is started");
        Response response = getOrderPositiveRequest();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());
    }

    @Step
    public void sendPostRequestAction() {
        System.out.println("post request is started");
        Response response = postOrderPositiveRequest();
        System.out.println(response+" response in sendPostRequestAction");
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());

    }

    @Step
    public void saveResponseForGetAction() {
        Response response = getOrderPositiveRequest();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());
    }

    @Step
    public void saveResponseForPostAction() {
        Response response = postOrderPositiveRequest();
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
        System.out.println("--------------Test is completed");
    }

    @Step
    public void verifyIdAction(final int id) {
        Assert.assertThat(
                "Wrong status code in response.",
                Serenity.sessionVariableCalled(ACTUAL_RESPONSE_STATUS_CODE).toString(),
                is(id));
        System.out.println(id + ": status code is expected");
        System.out.println("--------------Test is completed");
    }
}