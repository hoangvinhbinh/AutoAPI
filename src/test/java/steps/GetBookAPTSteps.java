package steps;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.Book;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GetBookAPTSteps {
    private static final String GET_BOOKS = "https://fakerestapi.azurewebsites.net/api/v1/Books/{id}";
    private static final String GET_LIST_BOOKS = "https://fakerestapi.azurewebsites.net/api/v1/Books/";
    RequestSpecification requestSpecification = SerenityRest.given();


    @Step("Get a book by ID")
    public void getBookByID(String id) {
     requestSpecification
                .pathParam("id", id)
                .when()
                .get(GET_BOOKS);
    }

    @Step("Get list books")
    public void getListBook() {
        requestSpecification
                .when()
                .get(GET_LIST_BOOKS);
    }

    @Step("Add a book")
    public void addaBook(int id, String title, String desp, int pageCount, String excerpt) {
        Book book = new Book(id,title,desp,pageCount,excerpt);
        requestSpecification
                .contentType(ContentType.JSON)
                .when()
                .body(book)
                .post(GET_LIST_BOOKS);
    }
}
