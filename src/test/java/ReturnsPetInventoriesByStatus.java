import data_classes.ApiResponseStatus;
import data_classes.Inventory;
import data_classes.Order;
import org.junit.jupiter.api.Test;
import specification.Specification;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReturnsPetInventoriesByStatus {
    Specification spec = new Specification();

    @Test
    public void returnsPetInventoriesByStatus(){

        Inventory inventoryMap = spec.returnsPetInventoriesByStatus().statusCode(200).extract().body().as(Inventory.class);
      //  inventoryMap.setInventory();


        System.out.println(inventoryMap.getInventory().size());
        assertTrue(inventoryMap.getInventory().size()>0);
    }


    @Test
    public void placeAnOrderForAPet200(){
        Order extendOrder = spec.placeAnOrderForAPet(Orders.ORDER1)
                .statusCode(200)
                .extract()
                .body()
                .as(Order.class);
        assertEquals(extendOrder, Orders.ORDER1);
    }

    @Test
    public void placeAnOrderForAPet(){
        spec.placeAnOrderForAPet(Orders.ORDER3)
                .body("type", equalTo("unknown"),
                        "message", equalTo("something bad happened"))
                .statusCode(500);

    }

    @Test
    public void placeAnOrderForAPet500(){
        spec.placeAnOrderForAPet(Orders.ORDER3)
                .body("type", equalTo(ApiResponseStatus.unknown),
                        "message", equalTo("something bad happened"))
                .statusCode(500);

    }
}
