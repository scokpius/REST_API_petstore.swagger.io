import data_classes.Order;
import data_classes.OrderStatus;

public class Orders {

    public static final Order ORDER1 = Order.builder()
            .id(1)
            .petId(15)
            .quantity(3)
            .shipDate("2021-10-14T13:13:50.859+0000") // LocalDate.now().format(DateTimeFormatter.ISO_DATE_TIME)
            .status(OrderStatus.approved)
            .complete(true)
            .build();

    public static final Order ORDER2 = Order.builder()
            .id(-50) //922337203
            .petId(123456789)
            .quantity(4)
            .shipDate("2021-10-14T10:50:24.680+0000")
            .status(OrderStatus.approved)
            .complete(true)
            .build();
    public static final Order ORDER3 = Order.builder()
            .id(25)
            .petId(20)
            .quantity(2)
            .shipDate("2021-10-14T13:13:50.859+0000")
            .status(OrderStatus.placed)
            .complete(true)
            .build();
    public static final Order ORDER4 = Order.builder()
            .id(-1)
            .petId(20)
            .quantity(2)
            .shipDate("2021-10-14T13:13:50.859+0000")
            .status(OrderStatus.placed)
            .complete(true)
            .build();
}
