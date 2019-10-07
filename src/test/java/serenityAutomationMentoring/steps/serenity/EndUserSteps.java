package serenityAutomationMentoring.steps.serenity;

import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import serenityAutomationMentoring.EnvironmentPropertyLoader;
import serenityAutomationMentoring.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.io.IOException;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    DictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }


    EnvironmentPropertyLoader properties = new EnvironmentPropertyLoader();

    @Step
    public void retrieveEnvValue() {
        properties.getProperty("env1");
    }

    @Step
    public void postOrder(int id, int petId, int quantity) {
   //sendRequest(id,petId, quantity);
        given().contentType("application/json")

                .when().post("/order");

    }
    @Step
    public void should_get_in_response_statusCode(int statusCode) {
      //  assertThat(actualStatusCode,expectedStatusCode);

        ResponseSpecification actualResult = given().then().statusCode(200);
        Assert.assertEquals(200,actualResult);
    }
}