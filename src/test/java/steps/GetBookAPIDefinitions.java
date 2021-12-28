package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class GetBookAPIDefinitions {
    public static final String ID = "'id'";
    public static final String TITLE = "'title'";
    public static final String DESP = "'description'";
    public static final String PAGECOUNT = "'pageCount'";
    public static final String EXCERPT = "'excerpt'";

    @Steps
    GetBookAPTSteps getBookAPTSteps;

    @When("I look up a book by id {word}")
    public void lookUpABook(String id) {
        getBookAPTSteps.getBookByID(id);
    }

    @Then("the searching executed successfully")
    public void isTheResponseCode200OK(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @And("the resulting should be id {} and title {} and matches JsonSchema")
    public void theResultingBodyShouldBe(Integer id, String title) {
        restAssuredThat(response -> response.body(ID, equalTo(id)));
        restAssuredThat(response -> response.body(TITLE, equalTo(title)));
        restAssuredThat(response -> response.body(matchesJsonSchemaInClasspath("jsonschema.json")));
    }

    @When("I look up a list books")
    public void iLookUpAListBooks() {
        getBookAPTSteps.getListBook();
    }

    @And("the resulting for getting list of books should match expectations")
    public void theResultingShouldMatchExpected() {
        restAssuredThat(response -> response.assertThat().body(TITLE, hasItems("Book 198","Book 200")));
        restAssuredThat(response -> response.assertThat().body(TITLE, not(hasItems("Book 500"))));
        restAssuredThat(response -> response.body(TITLE,hasSize(200)));
    }

    @When("I add a book")
    public void iAddABook() {
        getBookAPTSteps.addaBook(10,"string title","string despc",100,"string excerpt");
    }

    @And("the resulting of adding new book should match expectations")
    public void theResultingOfAddingNewBookShouldMatchExpected() {
        restAssuredThat(response -> response.body(ID, equalTo(10)));
        restAssuredThat(response -> response.body(TITLE, equalTo("string title")));
        restAssuredThat(response -> response.body(DESP, equalTo("string despc")));
        restAssuredThat(response -> response.body(EXCERPT, equalTo("string excerpt")));
        restAssuredThat(response -> response.body(PAGECOUNT, equalTo(100)));
        restAssuredThat(response -> response.body(matchesJsonSchemaInClasspath("jsonschema.json")));
    }
}
