import data_classes.ApiResponse;
import data_classes.Inventory;
import data_classes.Order;
import org.junit.jupiter.api.Test;
import specification.Specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReturnsPetInventoriesByStatus {
    Specification spec = new Specification();

    @Test
    public void returnsPetInventoriesByStatus() {
        Inventory inventoryMap = spec.returnsPetInventoriesByStatus().statusCode(200).extract().body().as(Inventory.class);
        System.out.println(inventoryMap.getInventory().size());
        assertTrue(inventoryMap.getInventory().size() > 0);
    }


    @Test
    public void placeAnOrderForAPet200() {

        Order actualOrder = Orders.ORDER_WITH_ID_1;
        Order extendOrder = spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_1)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        assertEquals(extendOrder, actualOrder);
    }

    @Test
    public void placeAnOrderForAPet400() {
        Order extendOrder = spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_MINUS_50)
                //  .statusCode(400)
                .extract()
                .body()
                .as(Order.class);
        assertTrue(extendOrder.getId() != Orders.ORDER_WITH_ID_MINUS_50.getId());
    }

    @Test
    public void findPurchaseOrderByID200() {
        spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_25)
                .statusCode(200);

        spec.findPurchaseOrderByID(25)
                .statusCode(200); //

    }

    @Test
    public void findPurchaseOrderByID404() {
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
    public void deletePurchaseOrderByID200() {
        spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_5)
                .statusCode(200);
        spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_5)
                .statusCode(200);

        spec.deletePurchaseOrderByID(1)
                .statusCode(200);

//        ApiResponse extendsResponse = ApiResponses.API_RESPONSE_DELETE_EXISTENT_ORDER;
//        ApiResponse actualResponse = spec.deletePurchaseOrderByID(5)
//                .statusCode(200)
//                .extract()
//                .body()
//                .as(ApiResponse.class);
//        assertEquals(extendsResponse, actualResponse);
    }

    @Test
    public void deletePurchaseOrderByID404() {
        spec.placeAnOrderForAPet(Orders.ORDER_WITH_ID_1)
                .statusCode(200);
        ApiResponse extendsResponse = ApiResponses.API_RESPONSE_DELETE_NON_EXISTENT_ORDER;
        ApiResponse actualResponse = spec.deletePurchaseOrderByID(1)
                .statusCode(404)
                .extract()
                .body()
                .as(ApiResponse.class);
        //  assertEquals(extendsResponse, actualResponse);
    }





}
