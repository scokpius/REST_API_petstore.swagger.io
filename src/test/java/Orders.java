import data_classes.Order;
import data_classes.OrderStatus;

public class Orders {

    public static final Order ORDER1 = Order.builder()
            .id(1)
            .petId(15)
            .quantity(3)
            .shipDate("20 november 2021 15:10")
            .status(OrderStatus.approved)
            .complete(true)
            .build();

    public static final Order ORDER2 = Order.builder()
            .id(15)
            .petId(3)
            .quantity(1)
            .shipDate("1 november 2021 15:10")
            .status(OrderStatus.delivered)
            .complete(false)
            .build();
    public static final Order ORDER3 = Order.builder()
            .id(5)
            .petId(20)
            .quantity(2)
            .shipDate("10 november 2021 15:10")
            .status(OrderStatus.placed)
            .complete(true)
            .build();
}
