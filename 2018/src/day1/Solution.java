package day1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import common.Tools;

public class Solution {

    private static final String PATH = "2018/src/day1/input.txt";

    public static void main(String[] args) {
        System.out.println("Total sum: " + getSumOfIntStream(Tools.getInputSupplier(PATH).get().mapToInt(Integer::parseInt)));
        System.out.println("First wrap at " + getFirstWrap(Tools.getInputSupplier(PATH).get().mapToInt(Integer::parseInt)));
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
