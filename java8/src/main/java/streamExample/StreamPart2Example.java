package streamExample;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.groupingBy;

public class StreamPart2Example {
    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(1,"Fuel",100.00,"Cambridge"));
        transactions.add(new Transaction(3,"Groceries",80.00,"Milan"));
        transactions.add(new Transaction(6,"Groceries",120.00,"Milan"));
        transactions.add(new Transaction(7,"Beer",40.00,"Milan"));
        transactions.add(new Transaction(8,"Beer",110.00,"Cambridge"));
        transactions.add(new Transaction(10,"Groceries",50.00,"Cambridge"));

        /*System.out.println(testReduceTransactionValueGt100(transactions));
        System.out.println(testCountWordFromStream());*/
//        testFindUniqueWordsFromFile();
//        testFindUniqueWordsFromFile2();
//        testFindUniqueWordsFromFileWithFlatMap();
//        System.out.println(testAveragingTransactionsValueByAveragingDouble(transactions));
//        testhighestTransactionValueByComparator(transactions).ifPresent(System.out::println);
        System.out.println(testGroupingByTransactionsByCityAndValueWithMaxBy(transactions));
    }

    private static Map<String, Optional<Transaction>> testGroupingByTransactionsByCityAndValueWithMaxBy(List<Transaction> transactions){
        Map<String, Optional<Transaction>> cityToHighestTransaction =
                transactions.stream().collect(groupingBy(
                        Transaction::getCity, maxBy(comparing(Transaction::getValue))));
        return cityToHighestTransaction;
    }

    private static Map<String, Double> testGroupingByTransactionsByCityAndValue(List<Transaction> transactions){
        Map<String, Double> cityToSum =
                transactions.stream().collect(groupingBy(
                        Transaction::getCity, summingDouble(Transaction::getValue)));
        return cityToSum;
    }

    private static Map<Boolean, List<Transaction>> testPartitionedTransactionsBypartitioningBy(List<Transaction> transactions){
        Map<Boolean, List<Transaction>> partitionedTransactions =
                transactions.stream().collect(partitioningBy(
                        t -> t.getValue() > 100));
        return partitionedTransactions;
    }

    private static double testSumTransactionValueByReducing(List<Transaction> transactions){
        double totalValue = transactions.stream().collect(reducing(
                0.0, Transaction::getValue, Double::sum));
        return totalValue;
    }

    private static Optional<Transaction> testhighestTransactionValueByComparator(List<Transaction> transactions){
        Optional<Transaction> highestTransaction =
                transactions.stream()
                        .collect(maxBy(comparing(Transaction::getValue)));
        return highestTransaction;
    }

    private static double testAveragingTransactionsValueByAveragingDouble(List<Transaction> transactions){
        double average = transactions.stream().collect(
                averagingDouble(Transaction::getValue));
        return average;
    }

    private static double testSummingTransactionsValueBySummingDouble(List<Transaction> transactions){
        double totalValue = transactions.stream().collect(
                summingDouble(Transaction::getValue));
        return totalValue;
    }

    private static long testCountingTransactions(List<Transaction> transactions){
        long howManyTransactions =
                transactions.stream().collect(counting());
        return howManyTransactions;
    }

    private static double testReduceTransactionValueGt100(List<Transaction> transactions){
        double sumExpensive =
                transactions.stream()
                        .filter(t -> t.getValue() > 100)
                        .map(Transaction::getValue)
                        .reduce(0.0, Double::sum);
        return sumExpensive;
    }

    private static Set<String> testCollectTransactionValueGt100ToSet(List<Transaction> transactions){
        Set<String> cities =
                transactions.stream()
                        .filter(t -> t.getValue() > 100)
                        .map(Transaction::getCity)
                        .collect(toSet());
        return cities;
    }

    private static Set<String> testCollectTransactionValueGt100ToHashSet(List<Transaction> transactions){
        Set<String> cities =
                transactions.stream()
                        .filter(t -> t.getValue() > 1000)
                        .map(Transaction::getCity)
                        .collect(toCollection(HashSet::new));
        return cities;
    }

    private static List<Integer> testCollectTransactionValueGt100(List<Transaction> transactions){
        List<Integer> expensiveTransactionsIds =
                transactions.stream()
                        .filter(t -> t.getValue() > 100)
                        .map(Transaction::getId)
                        .collect(toList());
        return expensiveTransactionsIds;
    }

    private static Map<String, Long> testCountWordFromStream(){
        Stream<String> words = Stream.of("Java", "Magazine", "is",
                "the", "best");

        Map<String, Long> letterToCount =
                words.map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .collect(groupingBy(identity(), counting()));
        return letterToCount;
    }

    private static void testFindUniqueWordsFromFile() {
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource("data.txt").toURI()))
                    .map(line -> line.split("\\s+")) // Stream<String[]>
                    .distinct() // Stream<String[]>
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void testFindUniqueWordsFromFile2() {
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource("data.txt").toURI()))
                    .map(line -> line.split("\\s+")) // Stream<String[]>
                    .map(Arrays::stream) // Stream<Stream<String>>
                    .distinct() // Stream<Stream<String>>
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void testFindUniqueWordsFromFileWithFlatMap() {
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource("data.txt").toURI()))
                    .map(line -> line.split("\\s+")) // Stream<String[]>
                    .flatMap(Arrays::stream) // Stream<String>
                    .distinct() // Stream<String>
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
