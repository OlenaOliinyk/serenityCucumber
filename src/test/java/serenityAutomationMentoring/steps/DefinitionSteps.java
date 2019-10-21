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
    public void given() {
        endUserSteps.givenAction();
    }

    @When("the user GET request")
    public void whenSendGetRequest() {
        endUserSteps.sendGetRequestAction();
    }

    @When("the user posts an order")
    public void whenSendPostRequest() {
        endUserSteps.sendPostRequestAction();
    }

    //skipped now
    @When("the user posts an order with id '(.*)' petId '(.*)' quantity '(.*)'")
    public void whenSendPostRequestWithParameters(final int id, final int petId, final int quantity) {
        //add action with parameters
        endUserSteps.sendPostRequestAction();
    }


    @And("the user save response from property")
    public void andSaveResponseForGet() {
        endUserSteps.saveResponseForGetAction();
        System.out.println("and step works for get");
    }

    @And("the user save response for post")
    public void andSaveResponseForPost() {
        endUserSteps.saveResponseForPostAction();
        System.out.println("and step works for post");
    }

    @Then("the order has status '(.*)'")
    public void thenVerifyStatusCode(final String statusCode) {
        //принимает на вход значение из фиче файла
        endUserSteps.verifyStatusCodeAction(statusCode);
    }

    @Then("the order has id '(.*)'")
    public void andThenVerifyId(final int id) {
        endUserSteps.verifyIdAction(id);
    }

}
