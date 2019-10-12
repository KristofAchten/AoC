package first;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day1 {
    public static void main(String[] args) {
        Supplier<IntStream> supplier = () -> {
            try {
                return new BufferedReader(new FileReader(new File("2018/src/first/input.txt"))).lines().mapToInt(Integer::parseInt);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        };

        HashSet<Integer> seen = new HashSet<>();

        System.err.println("Final count: " + supplier.get().sum());
        ArrayList<Integer> vals = Arrays.stream(supplier.get().toArray()).boxed().collect(Collectors.toCollection(ArrayList::new));

        int tmp = 0;
        seen.add(tmp);

        while (true) {
            for (Integer i : vals) {
                tmp += i;
                if (seen.contains(tmp)) {
                    System.err.println("First wrap: " + tmp);
                    return;
                } else {
                    seen.add(tmp);
                }
            }
        }

    }
}
