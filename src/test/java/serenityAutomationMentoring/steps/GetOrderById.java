package serenityAutomationMentoring.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.rest;
import static serenityAutomationMentoring.EnvironmentPropertyLoader.getProperty;

public class GetOrderById {
    private static final String POST_BODY = "{ \"id\": %s, \"petId\": %s, \"quantity\": %s, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    public static Response getOrderPositiveRequest() {

        System.out.println(" define what a property to use");
        return
                rest()
                        .accept(ContentType.JSON)
                        .when()
                        .get(getProperty("open.get.inventory.endpoint"))
                        .then()
                        .extract().response();

    }
    public static Response postOrderPositiveRequest(final int id, final int petId, final int quantity) {


        rest()
                .accept(ContentType.JSON)
                .when()
                .get(getProperty("open.get.inventory.endpoint"))
                .then()
                .extract().response();
        System.out.println("sent request s");
        return
                (rest()
                        .accept(ContentType.JSON)
                        .body(String.format(POST_BODY, id, petId, quantity))
                        .when()
                        .post()
                        .then()
                        .extract().response());

    }

}
