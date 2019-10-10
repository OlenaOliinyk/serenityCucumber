package serenityAutomationMentoring.steps;

import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;
import serenityAutomationMentoring.steps.serenity.EndUserSteps;

public class DefinitionSteps {


    @Steps
    EndUserSteps endUserSteps;

    @Given("the user has contentType")
    public void givenGetMethod() {
        endUserSteps.givenRest();


    }
    @Given("the user has baseURL")
    public void givenPost() {
        endUserSteps.givenPost();
    }
//    @When("the user send get response '(.*)'")
////    public void whenGetMethod(final String getUrl) {
////        endUserSteps.we();
////    }
////    @When("the user post order")
////    public void whenPost() {
////        endUserSteps.wen();
////    }
////    @When("the user posts an order with id '(.*)' petId '(.*)' quantity '(.*)'")
////    public void placeOrder(final int id, final int petId, final int quantity) {
////        //endUserSteps.postOrder(id, petId, quantity);
////
////    }
////
////    @Then("the order has status '(.*)'")
////    public void verifyStatusCode(int statusCode) {
////        //принимает на вход значение из фиче файла
////        //endUserSteps.getStatusCode();
////        System.out.println(statusCode+" from feature");
////       // endUserSteps.getResponseLongitude();
////        endUserSteps.th();
////
////    }
////    @Then("the order is placed")
////    public void thenPost(){
////        endUserSteps.the();
////        endUserSteps.shouldGetInResponseStatusCode();
////
////    }
}
