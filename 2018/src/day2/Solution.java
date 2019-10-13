package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    private static final String PATH = "2018/src/day2/input.txt";

    public static void main(String[] args) {
        Supplier<Stream<String>> supplier = () -> {
            try {
                return new BufferedReader(new FileReader(new File(PATH))).lines();
            } catch (FileNotFoundException ignored) {}
            return null;
        };

        System.out.println("Checksum: " + calculateChecksum(supplier.get()));
        System.out.println("Differing letters between box IDs: " + calculateLetterDiff(supplier.get()));
    }

    private static String calculateLetterDiff(Stream<String> stringStream) {
        String[] strings = stringStream.toArray(String[]::new);

        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                int differs = 0;
                for (int k = 0; k < strings[i].toCharArray().length && differs <= 1; k++) {
                    if (strings[i].toCharArray()[k] != strings[j].toCharArray()[k]) {
                            differs++;
                    }
                }
                if (differs == 1) {
                    List<Character> chars1 = strings[i].chars().mapToObj(x -> (char) x).collect(Collectors.toCollection(ArrayList::new));
                    List<Character> chars2 = strings[j].chars().mapToObj(x -> (char) x).collect(Collectors.toCollection(ArrayList::new));
                    chars1.retainAll(chars2);
                    return chars1.stream().map(Object::toString).collect(Collectors.joining());
                }
            }
        }
        return "abcde";
    }

    private static int calculateChecksum(Stream<String> stream) {
        Iterable<String> strings = stream.collect(Collectors.toList());

        int twos = 0;
        int threes = 0;

        for (String s : strings) {
            HashMap<Character, Integer> counts = new HashMap<>();
            for (char c : s.toCharArray()) {
                if (counts.containsKey(c)) {
                    counts.put(c, counts.get(c) + 1);
                } else {
                    counts.put(c, 1);
                }
            }

            boolean foundTwos = false;
            boolean foundThrees = false;

            for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
                switch (entry.getValue()) {
                    case 2:
                        if (!foundTwos) {
                            twos++;
                            foundTwos = true;
                        }
                        break;
                    case 3:
                        if(!foundThrees){
                            threes++;
                            foundThrees = true;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return twos * threes;
    }
}
