package streamExample;

public class Transaction implements Comparable<Transaction>{
    public static final String GROCERY = "Groceries";
    public static final String BEER = "Beer";
    public static final String FUEL = "Fuel";

    protected Integer id;
    protected String type;
    protected Double  value;
    private String city;

    public Transaction(Integer id, String type, double value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public Transaction(Integer id, String type, double value, String city) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Double getValue(){
        return this.value;
    }

    public String getCity() {
        return city;
    }

    public int compareTo(Transaction t1) {
        return (int)Math.signum(value-t1.getValue());
    }
    public String toString(){
        return "{" + this.id + ", " +
                "year: "+this.type+", " +
                "value:" + this.value +"}";
    }
}
