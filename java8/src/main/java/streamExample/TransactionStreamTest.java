package streamExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class TransactionStreamTest {
    public static void main(String[] args) {
        //create transaction list and add some transactions of vaying types and value
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(1,"Fuel",100.00));
        transactions.add(new Transaction(3,"Groceries",80.00));
        transactions.add(new Transaction(6,"Groceries",120.00));
        transactions.add(new Transaction(7,"Beer",40.00));
        transactions.add(new Transaction(10,"Groceries",50.00));

        System.out.println(testAllMatch(transactions));
        testFindAny(transactions).ifPresent(System.out::println);
        testFindAnyAndIfPresent(transactions);
    }

    private static boolean testAllMatch(List<Transaction> transactions){
        boolean expensive =
                transactions.stream()
                        .allMatch(t -> t.getValue() > 100);
        return expensive;
    }

    private static Optional<Transaction> testFindAny(List<Transaction> transactions){
        Optional<Transaction> any = transactions.stream()
                .filter(t -> t.getType() == Transaction.GROCERY)
                .findAny();
        return any;
    }

    private static void testFindAnyAndIfPresent(List<Transaction> transactions){
        transactions.stream()
                .filter(t -> t.getType() == Transaction.GROCERY)
                .findAny()
                .ifPresent(System.out::println);
    }

    private static double testTransactionValueSum(List<Transaction> transactions){
        double statement =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(0.0, Double::sum);
        return statement;
    }

    private static double testTransactionValueSum2(List<Transaction> transactions){
        double statementSum =
                transactions.stream()
                        .mapToDouble(Transaction::getValue)
                        .sum(); // works!
        return statementSum;
    }
    private static Stream<Double> testTransactionBoxed(List<Transaction> transactions){
        DoubleStream doubleStream = transactions.stream()
                .mapToDouble(Transaction::getValue);
        Stream<Double> stream  = doubleStream.boxed();
        return stream ;
    }


}
