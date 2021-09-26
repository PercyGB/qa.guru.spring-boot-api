package guru.qa.restbackend.tests;

import guru.qa.restbackend.specs.Specs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class AuthorControllerTests {
    @Test

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
    @Deprecated
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
    @Deprecated
    void addNewAuthor() {
        // @formatter:off
        Specs.authorsRequestSpec
                .given()
                    .contentType(ContentType.JSON)
                    .body("{" +
                        "\"id\": \"4\",\n" +
                        "\"first_name\": \"Eliyahu\",\n" +
                        "\"last_name\": \"Goldratt\"" +
                        "}")
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
