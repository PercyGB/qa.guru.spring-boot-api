package guru.qa.restbackend.tests;


import guru.qa.restbackend.specs.Specs;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.*;

public class BookControllerTests {
    @Test
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
