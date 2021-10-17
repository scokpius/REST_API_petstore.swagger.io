import com.google.gson.Gson;
import data_classes.ApiResponse;
import data_classes.Inventory;
import data_classes.Order;
import org.junit.jupiter.api.Test;
import specification.Specification;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ReturnsPetInventoriesByStatus {
    private Specification spec = new Specification();

    @Test
    void returnsPetInventoriesByStatus() {
        Gson gson = new Gson();
        Map map = new HashMap<>();
        map =  gson.fromJson(
                spec.returnsPetInventoriesByStatus()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString(),
                map.getClass());
        Inventory inventoryMap = Inventory.builder()
                .inventory(map)
                .build();
        assertTrue(inventoryMap.getInventory().size() > 0);
    }


    @Test
    void placeAnCorrectOrderForAPet() {
        Order actualOrder = Orders.ORDER_WITH_ID_1;
        Order extendOrder = spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_1)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        assertEquals(extendOrder, actualOrder);
    }

    @Test
    void placeAnNonCorrectOrderForAPet() {
        Order extendOrder = Orders.ORDER_WITH_ID_MINUS_50;
        Order actualOrder = spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_MINUS_50)
                .extract()
                .body()
                .as(Order.class);
        assertTrue(extendOrder.getId() != actualOrder.getId());
    }

    @Test
    void findExistentPurchaseOrderByID() {
        Order extendOrder = spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_25)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        Order actualOrder = spec.findPurchaseOrderByID(25)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        assertEquals(extendOrder, actualOrder);
    }

    @Test
    void findANonExistentPurchaseOrderByID() {
        spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_MINUS_1)
                .statusCode(200);
        ApiResponse extendsResponse = ApiResponses.API_RESPONSE_FIND_NON_EXISTENT_ORDER;
        ApiResponse actualResponse = spec.findPurchaseOrderByID(-1)
                .statusCode(404)
                .extract()
                .body()
                .as(ApiResponse.class);
        assertEquals(extendsResponse, actualResponse);
    }

    @Test
    void deleteExistentPurchaseOrderByID() {
        spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_5)
                .statusCode(200);
        spec.findPurchaseOrderByID(5)
                .statusCode(200);
        ApiResponse extendsResponse = ApiResponses.API_RESPONSE_DELETE_EXISTENT_ORDER;
        ApiResponse actualResponse = spec.deletePurchaseOrderByID(5)
                .statusCode(200)
                .extract()
                .body()
                .as(ApiResponse.class);
        assertEquals(extendsResponse, actualResponse);
    }

    @Test
    void deleteANonExistentPurchaseOrderByID() {
        ApiResponse extendsResponse = ApiResponses.API_RESPONSE_DELETE_NON_EXISTENT_ORDER;

        ApiResponse actualResponse = spec.deletePurchaseOrderByID(3)
                .statusCode(404)
                .extract()
                .body()
                .as(ApiResponse.class);
        assertEquals(extendsResponse, actualResponse);
    }
}
