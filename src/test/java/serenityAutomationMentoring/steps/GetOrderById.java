package serenityAutomationMentoring.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.rest;

import static serenityAutomationMentoring.EnvironmentPropertyLoader.getProperty;
import static serenityAutomationMentoring.properties.PropertiesNames.OPEN_GET_INVENTORY_ENDPOINT;
import static serenityAutomationMentoring.properties.PropertiesNames.OPEN_POST_ORDER_ENDPOINT;

public class GetOrderById {
    private static final String POST_BODY = "{ \"id\": %s, \"petId\": %s, \"quantity\": %s, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    private static final String POST_BODY_MISSED_PARAM = "{ \"id\": 55, \"petId\": , \"quantity\": 1, \"shipDate\": \"2019-08-05T13:40:02.396Z\", \"status\": \"placed\", \"complete\": false}";
    private static final String POST_BODY_WRONG_REQUEST = "";

    private static final String RESOURCE = "/order";
    private static final String API_REQUEST_URL = "%s/%s";

    public static Response getOrderPositiveRequestWithParam(final int orderId) {

        return
                rest()
                        .accept(ContentType.JSON)
                        .when()
                        .get(String.format(API_REQUEST_URL, getProperty(OPEN_GET_INVENTORY_ENDPOINT), orderId))
                        .then()
                        .log().body()
                        .extract().response();

    }

    public static Response getOrderNegativeRequest() {

        return
                rest()
                        .accept(ContentType.JSON)
                        .when()
                        .get(getProperty(OPEN_GET_INVENTORY_ENDPOINT))
                        .then()
                        .log().body()
                        .extract().response();

    }

    public static Response postOrderPositiveRequestWithParametrs(final int id, final int petId, final int quantity) {

        return
                given()
                        .contentType("application/json")
                        .baseUri(getProperty(OPEN_POST_ORDER_ENDPOINT))
                        .basePath(RESOURCE)
                        .body(String.format(POST_BODY,id,petId,quantity))
                        .log().body()
                        .when().post()
                        .then().extract().response();

    }

    public static Response postOrderNegativeRequestWithMissedParam() {

        return
                given()
                        .contentType("application/json")
                        .baseUri(getProperty(OPEN_POST_ORDER_ENDPOINT))
                        .basePath(RESOURCE)
                        .body(POST_BODY_MISSED_PARAM)
                        .log().body()
                        .when().post()
                        .then().extract().response();

    }
    public static Response postOrderNegativeRequestWithWrongParam() {

        return
                given()
                        .contentType("application/json")
                        .baseUri(getProperty(OPEN_POST_ORDER_ENDPOINT))
                        .basePath(RESOURCE)
                        .body(POST_BODY_WRONG_REQUEST)
                        .log().body()
                        .when().post()
                        .then().extract().response();

    }

}