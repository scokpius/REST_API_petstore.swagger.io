import data_classes.ApiResponse;

class ApiResponses {

    static final ApiResponse API_RESPONSE_DELETE_NON_EXISTENT_ORDER = ApiResponse.builder()
            .code(404)
            .type("unknown")
            .message("Order Not Found")
            .build();

    static final ApiResponse API_RESPONSE_FIND_NON_EXISTENT_ORDER = ApiResponse.builder()
            .code(1)
            .type("error")
            .message("Order not found")
            .build();

    static final ApiResponse API_RESPONSE_DELETE_EXISTENT_ORDER = ApiResponse.builder()
            .code(200)
            .type("unknown")
            .message("5")
            .build();
}
