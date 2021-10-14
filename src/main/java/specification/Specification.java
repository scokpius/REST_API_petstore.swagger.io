package specification;

import data_classes.Order;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.mapper.ObjectMapperType.GSON;

public class Specification {

    private static final String BASE_URL = "https://petstore.swagger.io/";
    private static final String BASE_PATH = "/v2/store";

    private static final RestAssuredConfig CONFIG = RestAssuredConfig.config()
            .decoderConfig(new DecoderConfig("UTF-8"))
            .encoderConfig(new EncoderConfig("UTF-8", "UTF-8"))
            .objectMapperConfig(new ObjectMapperConfig(GSON));

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setBasePath(BASE_PATH)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .setConfig(CONFIG)
    //        .log(LogDetail.ALL)
            .build();


    public ValidatableResponse returnsPetInventoriesByStatus() {
        return given().spec(requestSpecification)
                .when()
                .get("inventory")
                .then()
                .log().all();

    }

    public ValidatableResponse placeAnOrderForAPet(Order order) {
        return given().spec(requestSpecification)
                .body(order)
                .when()
                .post("order")
                .then()
                .log().all();

    }

    public ValidatableResponse findPurchaseOrderByID(int orderID) {
        return given().spec(requestSpecification)
                .body(orderID)
                .when()
                .get("order/" + orderID)
                .then()
                .log().all();
    }

    public ValidatableResponse deletePurchaseOrderByID(String orderID) {
        return given().spec(requestSpecification)
                .body(orderID)
                .when()
                .delete("order" + orderID)
                .then()
                .log().all();
    }
}
