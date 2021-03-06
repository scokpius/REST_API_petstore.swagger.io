import com.google.gson.Gson;
import data_classes.ApiResponse;
import data_classes.Inventory;
import data_classes.Order;
import io.qameta.allure.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import specification.Specification;

import static org.junit.jupiter.api.Assertions.*;


class ReturnsPetInventoriesByStatusTests {
    private Specification spec = new Specification();


//    @BeforeEach
//    void cleanOrders() {
//        spec.deletePurchaseOrderByID(1);
//        spec.deletePurchaseOrderByID(5);
//        spec.deletePurchaseOrderByID(8);
//        spec.deletePurchaseOrderByID(25);
//    }

    @Test
    @Link(value = "1. Returns pet inventories by status",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=2:2")
    void returnsPetInventoriesByStatus() {
        Inventory inventoryMap = Inventory.builder()
                .inventory(spec.getStringIntegerMap())
                .build();
        assertTrue(inventoryMap.getInventory().size() > 0);
    }

    @Test
    @Link(value = "2. Place a correct order for a pet",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=3:3")
    void placeAnCorrectOrderForAPet() {
        Order actualOrder = Orders.ORDER_WITH_ID_1;
        Order expectedOrder = spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_1)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    @Link(value = "3. Place an no body order for a pet",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=4:4")
    void placeAnNoBodyOrderForAPet() {
        Order expectedOrder = Orders.ORDER_NO_BODY;
        Order actualOrder = spec.placeAnOrderForAPet(Orders.ORDER_NO_BODY)
                .extract()
                .body()
                .as(Order.class);
        assertNotEquals(expectedOrder, actualOrder);
    }

    @Test
    @Link(value = "4. Place a non-correct order for a pet",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=5:5")
    void placeAnNonCorrectOrderForAPet() {
        Order expectedOrder = Orders.ORDER_WITH_ID_MINUS_50;
        Order actualOrder = spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_MINUS_50)
                .extract()
                .body()
                .as(Order.class);
        assertNotEquals(expectedOrder.getId(), actualOrder.getId());
    }

    @Test
    @Link(value = "5. Find existent purchase order by ID",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=6:6")
    void findExistentPurchaseOrderByID() {
        Order expectedOrder = spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_25)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        Order actualOrder = spec.findPurchaseOrderByID(25)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    @Link(value = "6. Find a purchase order with an invalid ID",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=7:7")
    void findExistentPurchaseOrderByID400() {
        Order expectedOrder = spec.placeAnOrderForAPet(Orders.ORDER_NO_BODY1)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        Order actualOrder = spec.findPurchaseOrderByID(expectedOrder.getId())
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    @Link(value = "7. Find a non-existent purchase order by ID",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=8:8")
    void findANonExistentPurchaseOrderByID() {
        spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_MINUS_1)
                .statusCode(200);
        ApiResponse expectedResponse = ApiResponses.API_RESPONSE_FIND_NON_EXISTENT_ORDER;
        ApiResponse actualResponse = spec.findPurchaseOrderByID(-1)
                .statusCode(404)
                .extract()
                .body()
                .as(ApiResponse.class);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @Link(value = "Delete existent purchase order by ID",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=9:9")
    void deleteExistentPurchaseOrderByID() {
        spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_5)
                .statusCode(200);
        spec.findPurchaseOrderByID(5)
                .statusCode(200);
        ApiResponse expectedResponse = ApiResponses.API_RESPONSE_DELETE_EXISTENT_ORDER;
        ApiResponse actualResponse = spec.deletePurchaseOrderByID(5)
                .statusCode(200)
                .extract()
                .body()
                .as(ApiResponse.class);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @Link(value = "Delete existent purchase order with an invalid  ID",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=10:10")
    void deleteExistentPurchaseOrderByID400() {
        spec.placeAnOrderForAPet(Orders.ORDER_NO_BODY1)
                .statusCode(200);

        ApiResponse expectedResponse = ApiResponses.API_RESPONSE_DELETE_EXISTENT_ORDER;
        ApiResponse actualResponse = spec.deletePurchaseOrderByID(8)
                .statusCode(400)
                .extract()
                .body()
                .as(ApiResponse.class);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @Link(value = "Delete a non existent purchase order by ID",
            url = "https://docs.google.com/spreadsheets/d/1_EVO_524iv8viqxOp0VXCZ5iwA_35QMgZwdZ_4CDf44/edit#gid=924667843&range=11:11")
    void deleteANonExistentPurchaseOrderByID() {
        ApiResponse expectedResponse = ApiResponses.API_RESPONSE_DELETE_NON_EXISTENT_ORDER;

        ApiResponse actualResponse = spec.deletePurchaseOrderByID(3)
                .statusCode(404)
                .extract()
                .body()
                .as(ApiResponse.class);
        assertEquals(expectedResponse, actualResponse);
    }
}
