package stream;

@FunctionalInterface
public interface FutureInt {
    <T> T call(T object);
}
