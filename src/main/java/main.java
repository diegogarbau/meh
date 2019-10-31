import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class HelloWorld {

    public static void main(String[] args) {

        List<String> combinations = Combinations.getCombinationsStream(Arrays.asList("1", "2", "3"));
        System.out.println("Nº of  different combinations: " + combinations.size());
        System.out.println(combinations);
        // prints: [[1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
    }

    private static class Combinations {

        private static final long MAXIM_NUMBER_OF_PERMUTATIONS = 10;

        // there are 2 ^ list.size()-1 possible combinations
        // stream through them and map the number of the combination to the combination
        private static List<String> getCombinationsStream(List<String> list) {
            return LongStream.range(1, 1 << list.size())
                .mapToObj(l -> bitMapToList(l, list))
//                .limit(MAXIM_NUMBER_OF_PERMUTATIONS)
                .map(combination -> combination
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")))
                .collect(toList());
        }

        // use the number of the combination (bitmap) as a bitmap to filter the input list
        private static List<String> bitMapToList(long bitmap, List<String> list) {
            return IntStream.range(0, list.size())
                .filter(i -> 0 != ((1 << i) & bitmap))
                .mapToObj(list::get)
                .collect(Collectors.toList());
        }
    }
}