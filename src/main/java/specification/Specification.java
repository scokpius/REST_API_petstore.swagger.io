package specification;

import data_classes.Inventory;
import data_classes.Order;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Specification {

    private static final String BASE_URL = "https://petstore.swagger.io";
    private static final String BASE_PATH = "/v2/store";

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setBasePath(BASE_PATH)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
                .post("order")
                .then()
                .log().all();

    }

    public ValidatableResponse findPurchaseOrderByID(String orderID) {
        return given().spec(requestSpecification)
                .body(orderID)
                .when()
                .get("order" + orderID)
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

    public Inventory getInventories() {

        String inventoryMap = this.returnsPetInventoriesByStatus().extract().body().asString();
        String[] keyMap = inventoryMap.split(",");
        Map<String, Integer> inventorMap = new HashMap<>();
        for (String pair : keyMap) {
            String[] entry = pair.split(":");
            inventorMap.put(entry[0].trim(), Integer.parseInt(entry[1].trim()));
        }
        return Inventory.builder().inventory(inventorMap).build();
    }


}
