import java.util.*;
import java.util.stream.Collectors;

public class Zadanie_4 {

    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
                new Order("ORD001", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Laptop Dell", "Elektronika", 1, 3500),
                        new OrderItem("Mysz Logitech", "Elektronika", 2, 150),
                        new OrderItem("Biurko Ikea", "Meble", 1, 800)
                )),
                new Order("ORD002", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Laptop Dell", "Elektronika", 1, 3500),
                        new OrderItem("Monitor Samsung", "Elektronika", 1, 1200),
                        new OrderItem("Krzesło Herman Miller", "Meble", 1, 2500)
                )),
                new Order("ORD003", "W TRAKCIE", Arrays.asList(
                        new OrderItem("Smartfon iPhone", "Elektronika", 1, 4500)
                )),
                new Order("ORD004", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Monitor Samsung", "Elektronika", 2, 1200),
                        new OrderItem("Klawiatura Razer", "Elektronika", 1, 450),
                        new OrderItem("Lampa LED", "Oświetlenie", 3, 120)
                )),
                new Order("ORD005", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Laptop Dell", "Elektronika", 1, 3500),
                        new OrderItem("Biurko Ikea", "Meble", 1, 800),
                        new OrderItem("Lampa LED", "Oświetlenie", 2, 120)
                )),
                new Order("ORD006", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Mysz Logitech", "Elektronika", 1, 150),
                        new OrderItem("Klawiatura Razer", "Elektronika", 1, 450),
                        new OrderItem("Krzesło Herman Miller", "Meble", 1, 2500)
                )),
                new Order("ORD007", "ANULOWANE", Arrays.asList(
                        new OrderItem("Tablet Samsung", "Elektronika", 1, 2200)
                )),
                new Order("ORD008", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Monitor Samsung", "Elektronika", 1, 1200),
                        new OrderItem("Lampa LED", "Oświetlenie", 1, 120),
                        new OrderItem("Dywan", "Dekoracje", 1, 350)
                )),
                new Order("ORD009", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Mysz Logitech", "Elektronika", 3, 150),
                        new OrderItem("Biurko Ikea", "Meble", 1, 800)
                )),
                new Order("ORD010", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Klawiatura Razer", "Elektronika", 1, 450),
                        new OrderItem("Krzesło Herman Miller", "Meble", 1, 2500),
                        new OrderItem("Dywan", "Dekoracje", 1, 350)
                ))
        );

        Map<String, List<ProductStats>> result = orders.stream()
                .filter(o -> "ZREALIZOWANE".equals(o.getStatus()))
                .flatMap(o -> o.getItems().stream()
                        .map(i -> new AbstractMap.SimpleEntry<>(o.getOrderId(), i)))
                .collect(Collectors.groupingBy(
                        e -> e.getValue().getCategory(),
                        Collectors.mapping(
                                e -> new AbstractMap.SimpleEntry<>(
                                        e.getValue().getProductName(),
                                        e.getKey()
                                ),
                                Collectors.toList()
                        )
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .collect(Collectors.groupingBy(
                                        Map.Entry::getKey,
                                        Collectors.mapping(
                                                Map.Entry::getValue,
                                                Collectors.collectingAndThen(
                                                        Collectors.toSet(),
                                                        s -> (long) s.size()
                                                )
                                        )
                                ))
                                .entrySet()
                                .stream()
                                .map(e -> new ProductStats(e.getKey(), e.getValue()))
                                .sorted(Comparator.comparingLong(ProductStats::getOrderCount).reversed())
                                .limit(3)
                                .toList()
                ));

        System.out.println(result);
    }
}

class OrderItem {

    private String productName;
    private String category;
    private int quantity;
    private double price;

    public OrderItem(String productName, String category, int quantity, double price) {
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }
}

class Order {

    private String orderId;
    private String status;
    private List<OrderItem> items;

    public Order(String orderId, String status, List<OrderItem> items) {
        this.orderId = orderId;
        this.status = status;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}

class ProductStats {

    private String productName;
    private long orderCount;

    public ProductStats(String productName, long orderCount) {
        this.productName = productName;
        this.orderCount = orderCount;
    }

    public long getOrderCount() {
        return orderCount;
    }

    public String getProductName() {
        return productName;
    }

    public String toString() {
        return productName + " : " + orderCount;
    }
}
