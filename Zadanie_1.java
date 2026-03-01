import java.util.Arrays;
import java.util.List;

public class Zadanie_1 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(15, 8, 23, 4, 42, 16, 11, 30, 7, 19, 2, 35, 28, 13, 6);

        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .sorted()
                .toList();

        System.out.println(result);
    }
}
