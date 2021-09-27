package guru.qa.restbackend.specs;

import guru.qa.restbackend.config.ApiConfig;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;

public class Specs {
    public static ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class);

    public static RequestSpecification booksRequestSpec = with()
            .baseUri(apiConfig.baseUrl())
            .basePath("/books");

    public static RequestSpecification authorsRequestSpec = with()
            .baseUri(apiConfig.baseUrl())
            .basePath("/authors");
}
