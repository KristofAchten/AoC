package day3;

import common.Tools;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    private static final String PATH = "2018/src/day3/input.txt";
    private enum States{
        NEVER, ONCE, TWICE_OR_MORE
    }

    public static void main(String[] args) {
        final Map.Entry resultPair = calculateOverlap(Tools.getInputSupplier(PATH).get());

        System.out.println("Overlap (in^2): " + resultPair.getKey());
        System.out.println("Unique pattern: " + determineUniquePattern(Tools.getInputSupplier(PATH).get(),
                (States[][]) resultPair.getValue()));
    }

    private static String determineUniquePattern(Stream<String> input, States[][] fabric) {
        ArrayList<String> claims = (ArrayList<String>) input.collect(Collectors.toList());

        for (String c : claims) {
            boolean unique = true;
            int[] usefulValues = Arrays.stream(parseEntry(c)).mapToInt(Integer::parseInt).toArray();
            for (int x = usefulValues[0]; x < usefulValues[0] + usefulValues[2]; x++) {
                for (int y = usefulValues[1]; y < usefulValues[1] + usefulValues[3]; y++) {
                    if (fabric[x][y] == States.TWICE_OR_MORE) {
                        unique = false;
                        break;
                    }
                }
                if (!unique) {
                    break;
                }
            }
            if (unique) {
                return c.split(" ")[0];
            }
        }
        return "Not found";
    }

    private static Map.Entry calculateOverlap(Stream<String> input) {
        final States[][] fabric = new States[1000][1000];
        for(int i = 0; i < fabric.length; i++) {
            for(int j = 0; j < fabric[0].length; j++) {
                fabric[i][j] = States.NEVER;
            }
        }


        int sqInches = 0;
        ArrayList<String> claims = (ArrayList<String>) input.collect(Collectors.toList());

        for(String c : claims) {

            int[] usefulValues = Arrays.stream(parseEntry(c)).mapToInt(Integer::parseInt).toArray();

            for (int x = usefulValues[0]; x < usefulValues[0] + usefulValues[2]; x++) {
                for (int y = usefulValues[1]; y < usefulValues[1] + usefulValues[3]; y++) {
                    switch (fabric[x][y]) {
                        case ONCE:
                            fabric[x][y] = States.TWICE_OR_MORE;
                            sqInches++;
                            break;
                        case NEVER:
                            fabric[x][y] = States.ONCE;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return new AbstractMap.SimpleEntry<>(sqInches, fabric);
    }

    private static String[] parseEntry(String input) {
        return Arrays.copyOfRange(input
                .replaceAll(":", "")
                .replaceAll("x", " ")
                .replaceAll(",", " ")
                .split(" "), 2, 6);
    }
}
