package day_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    private static final String PATH = "2018/src/day_1/input.txt";

    public static void main(String[] args) {
        Supplier<IntStream> supplier = () -> {
            try {
                return new BufferedReader(new FileReader(new File(PATH))).lines().mapToInt(Integer::parseInt);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        };

        System.out.println("Total sum: " + getSumOfIntStream(supplier.get()));
        System.out.println("First wrap at " + getFirstWrap(supplier.get()));
    }

    private static int getSumOfIntStream(IntStream stream) {
        return stream.sum();
    }

    private static int getFirstWrap(IntStream stream) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> vals = stream.boxed().collect(Collectors.toList());

        int tmp = 0;

        while (true) {
            for (Integer i : vals) {
                seen.add(tmp);
                tmp += i;
                if (seen.contains(tmp)) {
                    return tmp;
                }
            }
        }
    }
}
