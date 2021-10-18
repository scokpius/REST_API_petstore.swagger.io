import data_classes.Order;
import data_classes.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Orders {

    static final Order ORDER_WITH_ID_1 = Order.builder()
            .id(1)
            .petId(15)
            .quantity(3)
            .shipDate(getShipDate())
            .status(OrderStatus.approved)
            .complete(true)
            .build();

    static final Order ORDER_NO_BODY = Order.builder()
            .build();
    static final Order ORDER_NO_BODY1 = Order.builder()
            .id(8)
            .build();
    static final Order ORDER_WITH_ID_5 = Order.builder()
            .id(5)
            .petId(258)
            .quantity(5464)
            .shipDate(getShipDate())
            .status(OrderStatus.delivered)
            .complete(true)
            .build();

    static final Order ORDER_WITH_ID_MINUS_50 = Order.builder()
            .id(-50)
            .petId(123456789)
            .quantity(4)
            .shipDate(getShipDate())
            .status(OrderStatus.delivered)
            .complete(false)
            .build();

    static final Order ORDER_WITH_ID_25 = Order.builder()
            .id(25)
            .petId(20)
            .quantity(2)
            .shipDate(getShipDate())
            .status(OrderStatus.placed)
            .complete(true)
            .build();

    static final Order ORDER_WITH_ID_MINUS_1 = Order.builder()
            .id(-1)
            .petId(20)
            .quantity(2)
            .shipDate(getShipDate())
            .status(OrderStatus.placed)
            .complete(true)
            .build();

    static String getShipDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).substring(0,23) + "+0000";
    }
}
