package serenityAutomationMentoring.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.rest;
import static serenityAutomationMentoring.EnvironmentPropertyLoader.getProperty;

public class GetOrderById {


//    public static Response getOrderByIdRequestSimple(final String parameters) {
//
//        System.out.println(parameters+" --->>parametrs in GetOrderById class");
//        return rest()
//                .accept(ContentType.JSON)
//                .when()
//               .get("https://petstore.swagger.io/v2/store/order/3")
//                .then()
//                .extract().response();
//    }
    public static Response getOrderByIdRequest() {

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
