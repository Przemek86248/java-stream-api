import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Zadanie_2 {

    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("Laptop Dell", "Elektronika", 3500.00),
                new Product("Mysz Logitech", "Elektronika", 150.00),
                new Product("Monitor Samsung", "Elektronika", 1200.00),
                new Product("Klawiatura Razer", "Elektronika", 450.00),
                new Product("Smartfon iPhone", "Elektronika", 4500.00),
                new Product("Krzesło biurowe", "Meble", 800.00),
                new Product("Słuchawki Sony", "Elektronika", 350.00),
                new Product("Tablet Samsung", "Elektronika", 2200.00),
                new Product("Biurko", "Meble", 1500.00),
                new Product("Drukarka HP", "Elektronika", 900.00)
        );

        List<String> result = products.stream()
                .filter(p -> "Elektronika".equals(p.getCategory()))
                .filter(p -> p.getPrice() > 1000)
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .map(Product::getName)
                .toList();

        System.out.println(result);
    }
}

class Product {

    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}
