import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanTest {

    @Test
    @DisplayName("Проверка заголовков и URL (GET)")
    public void testGetRequest() {
        RestAssured.baseURI = "https://postman-echo.com";

        given()
                .header("User-Agent", "PostmanRuntime/7.43.2")
                .header("Accept", "*/*")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("headers.host", equalTo("postman-echo.com"))
                .body("url", equalTo("https://postman-echo.com/get"));
    }

    @Test
    @DisplayName("Проверка тела ответа и заголовков (POST)")
    public void testPostRequest() {
        RestAssured.baseURI = "https://postman-echo.com";

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .header("Content-Type", "text/plain")
                .header("User-Agent", "PostmanRuntime/7.43.2")
                .header("Accept", "*/*")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("headers.content-type", containsString("text/plain"))
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    @DisplayName("Проверка переданных параметров (POST)")
    public void testPostFormRequest() {
        RestAssured.baseURI = "https://postman-echo.com";

        given()
                .header("User-Agent", "PostmanRuntime/7.43.2")
                .header("Accept", "*/*")
                .contentType("multipart/form-data")
                .multiPart("foo1", "bar1")
                .multiPart("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    @DisplayName("проверка тела ответа и заголовков (PUT)")
    public void testPutRequest() {
        RestAssured.baseURI = "https://postman-echo.com";

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .header("Content-Type", "text/plain")
                .header("User-Agent", "PostmanRuntime/7.43.2")
                .header("Accept", "*/*")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("headers.content-type", containsString("text/plain"))
                .body("url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    @DisplayName("Проверка тела ответа и заголовков (PATCH)")
    public void testPatchRequest() {
        RestAssured.baseURI = "https://postman-echo.com";

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .header("Content-Type", "text/plain")
                .header("User-Agent", "PostmanRuntime/7.43.2")
                .header("Accept", "*/*")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("headers.content-type", containsString("text/plain"))
                .body("url", equalTo("https://postman-echo.com/patch"));
    }

    @Test
    @DisplayName("Проверка тела ответа и заголовков (DELETE)")
    public void testDeleteRequest() {
        RestAssured.baseURI = "https://postman-echo.com";

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .header("Content-Type", "text/plain")
                .header("User-Agent", "PostmanRuntime/7.43.2")
                .header("Accept", "*/*")
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("headers.content-type", containsString("text/plain"))
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}
