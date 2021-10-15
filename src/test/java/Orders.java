import data_classes.Order;
import data_classes.OrderStatus;

public class Orders {

    public static final Order ORDER_WITH_ID_1 = Order.builder()
            .id(1)
            .petId(15)
            .quantity(3)
            .shipDate("2021-10-14T10:50:24.680+0000") // LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).toString() // LocalDate.now().format(DateTimeFormatter.ISO_DATE_TIME)
            .status(OrderStatus.approved)
            .complete(true)
            .build();

    public static final Order ORDER_WITH_ID_5 = Order.builder()
            .id(5)
            .petId(65)
            .quantity(54645321)
            .shipDate("2021-10-14T10:50:24.680+0000")
            .status(OrderStatus.delivered)
            .complete(true)
            .build();

    public static final Order ORDER_WITH_ID_MINUS_50 = Order.builder()
            .id(-50) //922337203
            .petId(123456789)
            .quantity(4)
            .shipDate("2021-10-14T10:50:24.680+0000")
            .status(OrderStatus.delivered)
            .complete(false)
            .build();

    public static final Order ORDER_WITH_ID_25 = Order.builder()
            .id(25)
            .petId(20)
            .quantity(2)
            .shipDate("2021-10-14T13:13:50.859+0000")
            .status(OrderStatus.placed)
            .complete(true)
            .build();

    public static final Order ORDER_WITH_ID_MINUS_1 = Order.builder()
            .id(-1)
            .petId(20)
            .quantity(2)
            .shipDate("2021-10-14T13:13:50.859+0000")
            .status(OrderStatus.placed)
            .complete(true)
            .build();
}
