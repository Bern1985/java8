package streamExample;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;


public class TransactionList {
    public static List<Integer> getTransactionIDTraditional(ArrayList<Transaction> transactions) {
        //extract transactions of type matching parameter type
        List<Transaction> groceryTransactions = new ArrayList<>();
        for(Transaction t: transactions){
            if(t.getType() == Transaction.GROCERY){
                groceryTransactions.add(t);
            }
        }

        //sort by value descending
        Collections.sort(groceryTransactions, new Comparator<Transaction>(){
            public int compare(Transaction t1, Transaction t2){
                return t2.getValue().compareTo(t1.getValue());
            }
        });

        //get transaction IDs
        List<Integer> transactionIds = new ArrayList<>();
        for(Transaction t: groceryTransactions){
            transactionIds.add(t.getId());
        }
        return transactionIds;
    }

    public static List<Integer> getTransactionIDStream(ArrayList<Transaction> transactions) {
        List<Integer> transactionsIds = transactions.stream() //user transactions ArrayList as source
                .filter(t -> t.getType().equals(Transaction.GROCERY)) //filter on type
                .sorted(comparing(Transaction::getValue).reversed()) //sort using value, but reversed
                .map(Transaction::getId) //extract IDs
                .collect(toList()); //save in List
        return transactionsIds;
    }

    public static List<Integer> getTransactionIDByParallelStream(ArrayList<Transaction> transactions) {
        List<Integer> transactionsIds = transactions.parallelStream() //parallelizing the code
                .filter(t -> t.getType().equals(Transaction.GROCERY))
                .sorted(comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(toList());
        return transactionsIds;
    }

    public static void main(String[] args) {
        //create transaction list and add some transactions of vaying types and value
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(1,"Fuel",100.00));
        transactions.add(new Transaction(3,"Groceries",80.00));
        transactions.add(new Transaction(6,"Groceries",120.00));
        transactions.add(new Transaction(7,"Beer",40.00));
        transactions.add(new Transaction(10,"Groceries",50.00));
        //extract transactions of Groceries, sort by value spent, and get transaction IDs
        System.out.println("Traditional method: " + getTransactionIDTraditional(transactions));
        System.out.println("Stream method: " + getTransactionIDStream(transactions));
    }

}
