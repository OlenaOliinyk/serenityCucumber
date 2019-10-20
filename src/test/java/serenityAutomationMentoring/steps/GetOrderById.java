package serenityAutomationMentoring.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.rest;
import static serenityAutomationMentoring.EnvironmentPropertyLoader.getProperty;

public class GetOrderById {

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

}
