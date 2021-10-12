import data_classes.ApiResponseStatus;
import org.junit.jupiter.api.Test;
import specification.Specification;

import static org.hamcrest.Matchers.equalTo;

public class ReturnsPetInventoriesByStatus {
    Specification spec = new Specification();

    @Test
    public void returnsPetInventoriesByStatus(){
        spec.returnsPetInventoriesByStatus()
                .statusCode(200)
                .extract()
                .body().asString();
    }


    @Test
    public void placeAnOrderForAPet200(){
        spec.placeAnOrderForAPet(Orders.ORDER3)
                .statusCode(200)
                .extract().body().asString();

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
