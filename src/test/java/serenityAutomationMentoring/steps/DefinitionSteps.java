package serenityAutomationMentoring.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenityAutomationMentoring.steps.serenity.EndUserSteps;

public class DefinitionSteps {


    @Steps
    EndUserSteps endUserSteps;


    @Given("the user has contentType")
    public void givenGetMethod() {
         //endUserSteps.comonRest();
       endUserSteps.givenGet();

    }

    @Given("the user has baseURL")
    public void givenPost() {
        endUserSteps.givenPost();
    }


    @When("the user send request '(.*)'")
    public void whenGetMethod(final String sendGetResponse) {
        endUserSteps.whenGet(sendGetResponse);

    }


    @And("the user save response")
    public void saveResponse() {
        endUserSteps.andUserSaveResponce("order id 2");
    }


    @When("the user posts an order with id '(.*)' petId '(.*)' quantity '(.*)'")
    public void placeOrder(final int id, final int petId, final int quantity) {
        endUserSteps.postOrder(id, petId, quantity);

    }

    @Then("the order has status '(.*)'")
    public void thenGetMethod(final String statusCode) {
        //принимает на вход значение из фиче файла

        endUserSteps.thenGet(statusCode);
    }


}
