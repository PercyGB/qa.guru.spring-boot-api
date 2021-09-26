package guru.qa.restbackend.tests;


import guru.qa.restbackend.specs.Specs;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.*;

public class BookControllerTests {
    @Test
    @DisplayName("Get all books")
    @Tag("Book Controller")
    @Story("Get Book")
    void getAllBooks() {
        // @formatter:off
        Specs.booksRequestSpec
            .given()
            .when()
                .get("/")
            .then()
                .statusCode(200)
                .log().body();
        // @formatter:on
    }

    @Test
    @DisplayName("Get book by existing ISBN")
    @Tag("Book Controller")
    @Story("Get Book")
    void getBookByExistingIsbn() {
        // @formatter:off
        Specs.booksRequestSpec
                .given()
                .when()
                    .get("/isbn/0201006502")
                .then()
                    .statusCode(200)
                    .body("isbn[0]", is("0201006502"));
        // @formatter:on
    }

    @Test
    @DisplayName("Get book by not existing ISBN")
    @Tag("Book Controller")
    @Story("Get Book")
    void getBookByNotExistingIsbn() {
        // @formatter:off
        Specs.booksRequestSpec
                .given()
                .when()
                    .get("/isbn/0201006000")
                .then()
                    .statusCode(404);
        // @formatter:on
    }

    @Test
    @DisplayName("Add new book")
    @Tag("Book Controller")
    @Story("Add Book")
    void addNewBook() {
        // @formatter:off
        Specs.booksRequestSpec
                .given()
                    .contentType(ContentType.JSON)
                    .body("{" +
                            "\"isbn\": \"0932633439\",\n" +
                            "\"author_id\": \"3\",\n" +
                            "\"title\": \"Peopleware: Productive Projects and Teams\"" +
                            "}")
                    .log().body()
                .when()
                    .post("/add")
                .then()
                    .statusCode(200)
                    .body("isbn", is("0932633439"));
        // @formatter:on
    }

}
