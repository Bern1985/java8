package consumerExample;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestConsumer {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Larry", "Steve", "James");

        //use lambda expression
        System.out.println("Consumer test with lambda expression");
        Consumer<String> printConsumer = s -> System.out.println(s);
        names.forEach(printConsumer);

        // use method reference
        System.out.println("Consumer test with method reference");
        names.forEach(System.out::println);
    }
}
