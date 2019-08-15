package streamExample;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class TestMultilevelGrouping {

    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0,"London"),
            new Transaction(Currency.USD, 2300.0,"Hong Kong"),
            new Transaction(Currency.GBP, 9900.0,"San Francisco"),
            new Transaction(Currency.EUR, 1100.0,"San Francisco"),
            new Transaction(Currency.JPY, 7800.0,"Hong Kong"),
            new Transaction(Currency.CHF, 6700.0,"Hong Kong"),
            new Transaction(Currency.EUR, 5600.0,"London"),
            new Transaction(Currency.USD, 4500.0,"London"),
            new Transaction(Currency.CHF, 3400.0,"London"),
            new Transaction(Currency.GBP, 3200.0,"Hong Kong"),
            new Transaction(Currency.USD, 4600.0,"Hong Kong"),
            new Transaction(Currency.JPY, 5700.0,"San Francisco"),
            new Transaction(Currency.EUR, 6800.0,"San Francisco") );
    public static void main(String[] args) {

        testcityByCurrencyToAverageByMultilevelGrouping(transactions);
    }

    private static void testcityByCurrencyToAverageByMultilevelGrouping(List<Transaction> transactions) {
        Map<String, Map<Currency, Double>> cityByCurrencyToAverage =
                transactions.stream().collect(groupingBy(Transaction::getCity,
                        groupingBy(Transaction::getCurrency,
                                averagingDouble(Transaction::getValue))));
        System.out.println(cityByCurrencyToAverage);
    }

    public static class Transaction {
        private final Currency currency;
        private final double value;
        private String city;

        public Transaction(Currency currency, double value, String city) {
            this.currency = currency;
            this.value = value;
            this.city = city;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        public String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "currency=" + currency +
                    ", value=" + value +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }
}
