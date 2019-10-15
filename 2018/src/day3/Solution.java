package day3;

import common.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    private static final String PATH = "2018/src/day3/input.txt";
    private enum States{
        NEVER, ONCE, TWICE_OR_MORE
    }

    public static void main(String[] args) {
        System.out.println("Overlap (in^2): " + calculateOverlap(Tools.getInputSupplier(PATH).get()));
    }

    private static int calculateOverlap(Stream<String> input) {
        final States[][] fabric = new States[1000][1000];
        for(int i = 0; i < fabric.length; i++) {
            for(int j = 0; j < fabric[0].length; j++) {
                fabric[i][j] = States.NEVER;
            }
        }


        int sqInches = 0;
        ArrayList<String> claims = (ArrayList<String>) input.collect(Collectors.toList());

        for(String c : claims) {
            String[] usefulParts = Arrays.copyOfRange(c.replaceAll(":", "")
                    .replaceAll("x", " ")
                    .replaceAll(",", " ")
                    .split(" "), 2, 6);

            int[] usefulValues = Arrays.stream(usefulParts).mapToInt(Integer::parseInt).toArray();

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
        return sqInches;
    }
}
