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
import static serenityAutomationMentoring.TestSessionVariables.*;

import static serenityAutomationMentoring.steps.GetOrderById.getOrderPositiveRequest;
import static serenityAutomationMentoring.steps.GetOrderById.postOrderPositiveRequest;


public class EndUserSteps {

    private static final String POST_BODY = "{ \"id\": 35, \"petId\": 1, \"quantity\": 1, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    // private static final String POST_BODY = "{ \"id\": %s, \"petId\": %s, \"quantity\": %s, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    public static final String ACTUAL_RESPONSE_STATUS_CODE = "actual.response.status.code";


    @Step
    public void  givenGet(){
         given()
                .accept(ContentType.JSON);
    }


    @Step
    public void whenSendGetPositiveRequest() {
        System.out.println("send request is started");
        Response response = getOrderPositiveRequest();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());

    }

    @Step
    public void whenSendPostPositiveRequest(final int id, final int petId, final int quantity) {
        System.out.println("send request is started");
        Response response = postOrderPositiveRequest(id,petId,quantity);
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());

    }

    @Step
    public void andUserSaveResponceForInventory() {
        Response response = getOrderPositiveRequest();
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
        Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());
    }

    @Step
    public void thenGet(final String statusCode) {
        Assert.assertThat(
                "Wrong status code in response.",
                Serenity.sessionVariableCalled(ACTUAL_RESPONSE_STATUS_CODE).toString(),
                is(statusCode));
        System.out.println(statusCode+": status code is expected");
        System.out.println("--------------Test is completed");
    }
}