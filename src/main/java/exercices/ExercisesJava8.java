package exercices;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ExercisesJava8 {
    static int frequencyOfCharIn(String str, char chr) {
        return (int) str.chars().filter(c -> c == chr).count();
    }

    static Map<Character, Long> getFrequencyOfEachCharIn(String inputStr) {
        return inputStr.chars()
                .map(Character::toLowerCase)
                .mapToObj(c -> (char) c)
                .collect(groupingBy(identity(), counting()));
    }

    static boolean isAnagram(String s1, String s2) {
        String s1Sorted = Stream.of(s1.replaceAll("\\s", "")
                .split("")).map(String::toUpperCase)
                .sorted().collect(Collectors.joining());
        String s2Sorted = Stream.of(s2.replaceAll("\\s", "")
                .split("")).map(String::toUpperCase)
                .sorted().collect(Collectors.joining());
        return s1Sorted.equals(s2Sorted);
    }

    static int getSecondSmallestElement(int[] inputArray) {
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] < firstMin) {
                secondMin = firstMin;
                firstMin = inputArray[i];
            } else if (inputArray[i] < secondMin) {
                secondMin = inputArray[i];
            }
        }
        return secondMin;
    }
}
