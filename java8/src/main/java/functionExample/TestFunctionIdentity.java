package functionExample;

import java.util.function.Function;

public class TestFunctionIdentity {
    public static void main(String args[])
    {

        // Function which takes in a number and
        // returns it
        Function i = Function.identity();

        System.out.println(i);
    }
}
