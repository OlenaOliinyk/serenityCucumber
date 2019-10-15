package serenityAutomationMentoring.steps.serenity;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.rest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static serenityAutomationMentoring.TestSessionVariables.*;
import static serenityAutomationMentoring.steps.GetOrderById.getOrderByIdRequest;
import static serenityAutomationMentoring.steps.GetOrderById.getOrderByIdRequestSimple;

public class EndUserSteps {


  //  EnvironmentPropertyLoader properties = new EnvironmentPropertyLoader();

    private static final String POST_BODY = "{ \"id\": 35, \"petId\": 1, \"quantity\": 1, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    // private static final String POST_BODY = "{ \"id\": %s, \"petId\": %s, \"quantity\": %s, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    public static final String ACTUAL_RESPONSE_STATUS_CODE = "actual.response.status.code";

    @Step
    // common Step for rest
    public void comonRest() {

        int status = given()
                .accept(ContentType.JSON)
                .when()
                //.get("https://petstore.swagger.io/v2/store/order/2")
                .get()
                .then().extract().statusCode();
        Assert.assertEquals("StatusCode",200,status);

        System.out.println("--------------------------");
    }

    @Step
    public void  givenGet(){
         given()
                .accept(ContentType.JSON);
    }

    @Step
    //step for simple when url send in feature file
    public void whenGet(final String sendGetResponse){

                when()
                .get(sendGetResponse).then().log().body();
        System.out.println(sendGetResponse+" get response" );

    }
    @Step
    public void sendGetRequest() {
        Response response = getOrderByIdRequest();
       // Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
       // Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());
        System.out.println("8888h---------------");
    }
    // Steps for post
    @Step
    public void givenPost() {
        System.out.println("post inventory started");
        given()
                .contentType("application/json")
                .baseUri("https://petstore.swagger.io/v2/store")
                .basePath("/order")
                .body(POST_BODY)
                .log().body()
                .when().post()
                .then() .body("id",equalTo(35) );

        System.out.println("post inventory finished");

    }


    @Step
    public void postOrder(final int id, final int petId, final int quantity) {


        System.out.println(rest()
                .accept(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory")
                .then()
                .extract().response());
        System.out.println(rest()
                .accept(ContentType.JSON)
                .body(String.format(POST_BODY, id, petId, quantity))
                .when()
                .post("https://petstore.swagger.io/v2/store/order")
                .then()
                .extract().response());

    }


    @Step
    public void andUserSaveResponce(final String parameters) {
        Response response = getOrderByIdRequestSimple(parameters);
        Serenity.setSessionVariable(ACTUAL_RESPONSE_STATUS_CODE).to(String.valueOf(response.getStatusCode()));
         Serenity.setSessionVariable(ACTUAL_RESPONSE_JSON).to(response.body().asString());
        System.out.println(ACTUAL_RESPONSE_STATUS_CODE+" actualt status");
    }
    @Step
    public void thenGet(final String statusCode) {
        Assert.assertThat(
                "Wrong status code in response.",
                Serenity.sessionVariableCalled(ACTUAL_RESPONSE_STATUS_CODE).toString(),
                is(statusCode));
        System.out.println(statusCode+" then get");
    }
}