package functionExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class TestFunction {
    public static void main(String[] args) {
        List<Integer> result = map(Arrays.asList("lambdas","in","action"),
                (String s) -> s.length()
        );
        System.out.println(result);
    }
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }
}
