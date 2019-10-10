package serenityAutomationMentoring.steps.serenity;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import jnr.ffi.mapper.ToNativeConverter;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.SerenityReports;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.hamcrest.Matchers;
import org.jruby.RubyThread;
import org.jsoup.helper.StringUtil;
import org.junit.Assert;

import serenityAutomationMentoring.EnvironmentPropertyLoader;
import serenityAutomationMentoring.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.serenitybdd.rest.SerenityRest;

import java.io.IOException;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.rest;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class EndUserSteps {


    EnvironmentPropertyLoader properties = new EnvironmentPropertyLoader();
    //DictionaryPage dictionaryPage;
    private Response response;
    private static final String POST_BODY = "{ \"id\": 35, \"petId\": 1, \"quantity\": 1, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    // private static final String POST_BODY = "{ \"id\": %s, \"petId\": %s, \"quantity\": %s, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";

    @Step
    public void givenRest() {
        System.out.println("get inventory started");

                given()
                        .accept(ContentType.JSON)
                        .when().get("https://petstore.swagger.io/v2/store/order/2")
                        .then().statusCode(200).body("id", equalTo(2)).log().all();

        System.out.println("get inventory finished ");
        System.out.println("--------------------------");

    }

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
    public void retrieveEnvValue() {
        properties.getProperty("env1");
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

//    @Step
//    public void giv() {
//
//        rest()
//                .accept(ContentType.JSON)
//                .body(POST_BODY)
//                .when()
//                .post("/order")
//                .then().statusCode(200)
//                .log().body();

        // .log().all();

//        System.out.println(getStatusCode());
//    }











    @Step
    public void shouldGetInResponseStatusCode() {

       ResponseSpecification actualResult = (ResponseSpecification) response.then().statusCode(300);

        Assert.assertEquals(200,actualResult);

    }


}