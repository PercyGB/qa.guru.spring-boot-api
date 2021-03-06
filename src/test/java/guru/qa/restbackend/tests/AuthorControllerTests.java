package guru.qa.restbackend.tests;

import guru.qa.restbackend.domain.Author;
import guru.qa.restbackend.specs.Specs;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class AuthorControllerTests {
    @Test
    @DisplayName("Get all authors")
    @Tag("Author Controller")
    @Story("Get Author")
    void getAllAuthors() {
        // @formatter:off
        Specs.authorsRequestSpec
                .given()
                .when()
                    .get("/")
                .then()
                    .statusCode(200)
                    .log().body();
        // @formatter:on
    }

    @Test
    @DisplayName("Get book by existing author")
    @Tag("Author Controller")
    @Story("Get Author")
//    @Deprecated
    void getBookByExistingAuthorById() {
        // @formatter:off
        Specs.authorsRequestSpec
                .given()
                .when()
                .get("/id/2")
                    .then()
                    .statusCode(200)
                    .body("id[0]", is(2));
        // @formatter:on
    }

    @Test
    @DisplayName("Get book by not existing author")
    @Tag("Author Controller")
    @Story("Get Author")
    void getBookByNotExistingAuthorId() {
        // @formatter:off
        Specs.authorsRequestSpec
                .given()
                .when()
                    .get("/id/8")
                .then()
                    .statusCode(404);
        // @formatter:on
    }

    @Test
    @DisplayName("Add new author")
    @Tag("Author Controller")
    @Story("Add Author")
 //   @Deprecated
    void addNewAuthor() {
        Author author = new Author(4, "Eliyahu", "Goldratt");

        // @formatter:off
        Specs.authorsRequestSpec
                .given()
                    .contentType(ContentType.JSON)
                    .body(author)
                    .log().body()
                .when()
                    .post("/add")
                .then()
                    .statusCode(200)
                    .body("id", is(4),
                            "first_name", is("Eliyahu"),
                            "last_name", is("Goldratt"));
        // @formatter:on
    }
}
