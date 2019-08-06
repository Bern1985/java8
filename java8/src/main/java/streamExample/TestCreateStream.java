package streamExample;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestCreateStream {
    public static void main(String[] args) {
//        System.out.println(createStreamByFile());
//        createInfiniteStreamByIterate();
//        createInfiniteStreamByGenerate();
        createInfiniteStreamByGenerateWithIntSupplier();
    }


    private static void createStreamByStrings(){
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }
    private static void createStreamByArray(){
        Stream<Integer> numbersFromValues = Stream.of(1, 2, 3, 4);
        int[] numbers = {1, 2, 3, 4};
        IntStream numbersFromArray = Arrays.stream(numbers);
        numbersFromArray.forEach(System.out::println);
    }

    private static long createStreamByFile(){
        long uniqueWords = 0;
        try(Stream<String> lines =  Files.lines(Paths.get(ClassLoader.getSystemResource("data.txt").toURI()), Charset.defaultCharset())){//流会自动 关闭
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()//删除重复项
                    .count();//数一数有多少各不相同的单词

        }
        catch(IOException e){
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uniqueWords;
    }

    private static void createInfiniteStreamByIterate(){
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }
    private static void createInfiniteStreamByGenerate(){
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

    }
    private static void createInfiniteStreamByGenerateWithIntSupplier(){
        IntStream twos = IntStream.generate(new IntSupplier(){
            public int getAsInt()
            {
                return 2;
            }
        });
        twos.limit(10).forEach(System.out::println);
    }
}
