package serenityAutomationMentoring.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.rest;

public class GetOrderById {
    private static final String API_REQUEST_URL = "%s/%s?%s";
    private static final String RESOURCE = "weather";
    public static Response getOrderByIdRequest(final String parameters) {

        System.out.println(parameters+" parametrs is get url");
        return rest()
                .accept(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/order/2")
                .then()
                .extract().response();

    }

}
