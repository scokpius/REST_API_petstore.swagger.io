import data_classes.ApiResponse;

public class ApiResponses {

    public static final ApiResponse API_RESPONSE_DELETE_NON_EXISTENT_ORDER = ApiResponse.builder()
            .code(404)
            .type("unknown")
            .message("null for uri: http://petstore.swagger.io/v2/store/order5")
            .build();

    public static final ApiResponse API_RESPONSE_FIND_NON_EXISTENT_ORDER = ApiResponse.builder()
            .code(1)
            .type("error")
            .message("Order Not Found")
            .build();

   public static final ApiResponse API_RESPONSE_DELETE_EXISTENT_ORDER = ApiResponse.builder()
            .code(200)
            .type("unknown")
            .message("5")
            .build();


}
