package stream;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Functions {
    public static void main(String[] args) {
        Func foo = () -> {
            System.out.println("Hello world");
        };
        // FutureInt future = (String obj) -> obj.substring(3);
        Consumer<String> consumer = s -> {

        };
        Function<String, Integer> function = s -> s.length();
        Predicate<String> predicate = s -> s.length() > 5;
        predicate.test("124124124");
        /*new Func() {
            @Override
            public void call() {
                System.out.println("Hello world");
            }
        };*/

        System.out.println(foo.getClass());
        foo.call();
    }
}
