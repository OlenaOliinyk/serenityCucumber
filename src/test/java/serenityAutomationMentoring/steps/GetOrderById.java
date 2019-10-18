package serenityAutomationMentoring.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.rest;
import static serenityAutomationMentoring.EnvironmentPropertyLoader.getProperty;
import static serenityAutomationMentoring.properties.PropertiesNames.OPEN_GET_INVENTORY_ENDPOINT;

public class GetOrderById {

    private static final String API_REQUEST_URL = "https://petstore.swagger.io/v2/store/order/3";
    private static final String RESOURCE = "https://petstore.swagger.io/v2/store/order/3";
    public static Response getOrderByIdRequestSimple(final String parameters) {

        System.out.println(parameters+" --->>parametrs in GetOrderById class");
        return rest()
                .accept(ContentType.JSON)
                .when()
               .get("https://petstore.swagger.io/v2/store/order/3")
                .then()
                .extract().response();

    }
    public static Response getOrderByIdRequest() {

      System.out.println(" getOrderByIdRequest is started");
        return
                rest()
                .accept(ContentType.JSON)
                .when()
             // .get(String.format(API_REQUEST_URL, getProperty(OPEN_GET_INVENTORY_ENDPOINT), RESOURCE))
                .get(getProperty("open.get.inventory.endpoint"))
                .then()
                .extract().response();

    }

}
