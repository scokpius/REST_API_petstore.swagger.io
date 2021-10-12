package data_classes;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {

    private int code;
    private String type;
    private String message;
}
