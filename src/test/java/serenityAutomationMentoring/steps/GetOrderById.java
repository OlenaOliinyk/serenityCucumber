package serenityAutomationMentoring.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.rest;
import static net.serenitybdd.rest.SerenityRest.post;
import static serenityAutomationMentoring.EnvironmentPropertyLoader.getProperty;

public class GetOrderById {
    private static final String POST_BODY = "{ \"id\": %s, \"petId\": %s, \"quantity\": %s, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    private static final String POST_FULLBODY = "{ \"id\": 35, \"petId\": 1, \"quantity\": 1, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    private static final String RESOURCE = "/order";

    public static Response getOrderPositiveRequest() {

        System.out.println(" define what a property to use for get");
        return
                rest()
                        .accept(ContentType.JSON)
                        .when()
                        .get(getProperty("open.get.inventory.endpoint"))
                        .then()
                        .log().body()
                        .extract().response();

    }

    public static Response postOrderPositiveRequest() {

        System.out.println("response class is started");

        return
                given()
                       .contentType("application/json")
                        .baseUri(getProperty("open.get.inventory.endpoint"))
                        .basePath(RESOURCE)
                        .body(POST_FULLBODY)
                         .log().body()
                        .when().post()
                        .then().extract().response();


    }

    public static Response postOrderPositiveRequestWithParametrs(final int id, final int petId, final int quantity) {

        return
                given()
                        .contentType("application/json")
                        .baseUri(getProperty("open.get.inventory.endpoint"))
                        .basePath(RESOURCE)
                        .body(String.format(POST_BODY,id,petId,quantity))
                        .log().body()
                        .when().post()
                        .then().extract().response();


    }
}