import data_classes.ApiResponse;
import data_classes.Inventory;
import data_classes.Order;
import org.junit.jupiter.api.Test;
import specification.Specification;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReturnsPetInventoriesByStatus {
    Specification spec = new Specification();

    @Test
    public void returnsPetInventoriesByStatus(){
        Inventory inventoryMap = spec.returnsPetInventoriesByStatus().statusCode(200).extract().body().as(Inventory.class);
        System.out.println(inventoryMap.getInventory().size());
        assertTrue(inventoryMap.getInventory().size()>0);
    }


    @Test
    public void placeAnOrderForAPet200(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
//
////        String text = localDate.format(DateTimeFormatter.ISO_DATE_TIME);
////        System.out.println(text);
//        spec.placeAnOrderForAPet(Orders.ORDER1).statusCode(200);

      Order extendOrder = spec.placeAnOrderForAPet(Orders.ORDER1)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        assertEquals(extendOrder, Orders.ORDER1);
    }

    @Test
    public void placeAnOrderForAPet400(){
      Order extendOrder = spec.placeAnOrderForAPet(Orders.ORDER2)
              //  .statusCode(400)
                .extract()
                .body()
                .as(Order.class);
        assertEquals(extendOrder, Orders.ORDER2);
    }

    @Test
    public void findPurchaseOrderByID200(){
        spec.placeAnOrderForAPet(Orders.ORDER3)
                .statusCode(200);

        spec.findPurchaseOrderByID(25)
                .statusCode(200);

    }

    @Test
    public void findPurchaseOrderByID404(){
        spec.placeAnOrderForAPet(Orders.ORDER4)
                .statusCode(200);
        ApiResponse extendsResponse = ApiResponse.builder()
                .code(1)
                .type().build();
        ApiResponse actualResponse = spec.findPurchaseOrderByID(-1)
                .statusCode(404)
                .extract()
                .body()
                .as(ApiResponse.class);

    }
}
