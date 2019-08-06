package streamExample;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TestStringStreams {
    public static void main(String[] args) {
        System.out.println(testStreamMap());
    }

    private static List<Integer> testStreamMap(){
        List<String> words = Arrays.asList("Oracle", "Java", "Magazine");
        List<Integer> wordLengths =
                words.stream()
                        .map(String::length)
                        .collect(toList());
        return wordLengths;
    }
}
