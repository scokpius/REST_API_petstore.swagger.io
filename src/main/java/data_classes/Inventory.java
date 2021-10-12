package data_classes;

import lombok.Builder;
import lombok.Data;


import java.util.Map;

@Data
@Builder
public class Inventory {

    private Map<String, Integer> inventory;
}
