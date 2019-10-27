package serenityAutomationMentoring.steps;

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
    @When("the user sent request '(.*)'")
    public void whenSendGetRequestWithParam(final int orderId) {
        endUserSteps.sendGetRequestWithParamAction(orderId);
    }

    @When("the user missed parameter")
    public void whenSendPostRequest() {
        endUserSteps.sendPostRequestAction();
    }
    @When("the user type wrong parameter")
    public void whenSendPostWrongParam() {
        endUserSteps.sendPostRequestWithWrongParamAction();
    }
    @When("the user posts id '(.*)' petId '(.*)' quantity '(.*)'")
    public void whenSendPostRequestWithParameters(final int id, final int petId, final int quantity) {
        endUserSteps.sendPostRequestWithParametrsAction(id,petId,quantity);
    }


    @Then("the order has status '(.*)'")
    public void thenVerifyStatusCode(final String statusCode) {
        endUserSteps.verifyStatusCodeAction(statusCode);
    }

    @Then("the order has id '(.*)'")
    public void andThenVerifyId(final int id) {
        endUserSteps.verifyIdAction(id);
    }
    @Then("the error type is '(.*)'")
    public void andThenVerifyErrorType(final String errorType) {
        endUserSteps.verifyTypeAction(errorType);
    }
    @Then("the message '(.*)' is displayed in body")
    public void andThenVerifyMessage(final String message) {
        endUserSteps.verifyMessageAction(message);
    }
}
