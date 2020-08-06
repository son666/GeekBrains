package stream;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPI {
    @SneakyThrows
    public static void main(String[] args) {
        Path path = Paths.get("./common/src/main/resources",
                "1.txt");
//        Arrays.stream(new int[]{1,2,3,4,5});
//        new ArrayList<>().stream();
//        new TreeSet<>().stream();
//        new TreeMap<>().entrySet().stream();
//        IntStream.range(1, 12)
//                .filter(x -> x % 2 == 0)
//                .forEach(arg -> System.out.print(arg + " "));
//        Files.lines(path)
//                .map(x -> x.split(" "))
//                .forEach(x -> Arrays.stream(x)
//                        .map(s -> s.replaceAll("\\.", ""))
//                        .forEach(System.out::println));
        // Integer.sum()
        int result = Files.lines(path)
                .flatMap(arg -> Stream.of(arg.split(" ")))
                //.map(s -> s.replaceAll("\\.", ""))
                .filter(arg -> !arg.contains("."))
                .reduce(0, (a, b) -> a + b.length(), Integer::sum);
        System.out.println(result);
        System.out.println(IntStream.rangeClosed(1, 10).reduce(0, Integer::sum));
        //.forEach(arg -> System.out.print(arg + " "));
        String resultString = Files.lines(path)
                .flatMap(arg -> Stream.of(arg.split(" ")))
                .filter(arg -> !arg.contains("."))
                .collect(Collectors.joining(", "));
        System.out.println(resultString);
        List<String> resultList = Files.lines(path)
                .flatMap(arg -> Stream.of(arg.split(" ")))
                .filter(arg -> !arg.contains("."))
                .collect(Collectors.toList());
        System.out.println(resultList);

        HashMap<String, Integer> m = Files.lines(path)
                .flatMap(arg -> Stream.of(arg.split(" ")))
                .filter(arg -> !arg.contains("."))
                .collect(HashMap::new, (map, s) -> map.put(s, map.getOrDefault(s, 0) + 1), HashMap::putAll);
        System.out.println(m);
    }
}
