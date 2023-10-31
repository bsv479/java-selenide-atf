package exercices;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ExercisesJava8Test {
    private static Stream<Arguments> getFrequencyOfCharInArgs() {
        return Stream.of(
                arguments("Panama", 'a', 3),
                arguments("Sicily", 'i', 2),
                arguments("Argentina", 'm', 0)
        );
    }

    private static Stream<Arguments> getFrequencyOfEachCharInStringArgs() {
        return Stream.of(
                arguments("Panama", Map.of('p', 1L, 'a', 3L, 'n', 1L, 'm', 1L)),
                arguments("Puppy", Map.of('p', 3L, 'u', 1L, 'y', 1L)),
                arguments("Kitty", Map.of('k', 1L, 'i', 1L, 't', 2L, 'y', 1L))
        );
    }

    private static Stream<Arguments> isAnagramArgs() {
        return Stream.of(
                arguments("Mother In Law", "Hitler Woman", true),
                arguments("keEp", "peeK", true),
                arguments("joy", "enjoy", false)
        );
    }

    private static Stream<Arguments> secondSmallestElementArgs() {
        return Stream.of(
                arguments(new int[]{17, 11, 15, 64, 13, 88, 35}, 13),
                arguments(new int[]{17, 7, 15, 64, 13, 4, 35}, 7),
                arguments(new int[]{17, 17, -2, 64, 17, -1, 35}, -1),
                arguments(new int[] {1, 2, 23, 64, 41, 88, 35}, 2),
                arguments(new int[] {2, 1, 23, 64, 41, 88, 35}, 2)
        );
    }

    @ParameterizedTest(name = "#{index} - get frequency of {1} in {0}")
    @MethodSource("getFrequencyOfCharInArgs")
    void testFrequencyOfCharIn(String inputStr, char chr, int expectedTimes) {
        int actualTimes = ExercisesJava8.frequencyOfCharIn(inputStr, chr);
        assertThat(actualTimes).isEqualTo(expectedTimes);
    }

    @ParameterizedTest(name = "#{index} - frequency Of Each Char in {0}")
    @MethodSource("getFrequencyOfEachCharInStringArgs")
    void getFrequencyOfEachCharInStringTest(String inputStr, Map<Character, Long> expectedEntries) {
        Map<Character, Long> actualEntries = ExercisesJava8.getFrequencyOfEachCharIn(inputStr);
        System.out.println("expectedEntries: " + expectedEntries.toString());
        System.out.println("actualEntries: " + actualEntries.toString());
        assertThat(actualEntries).hasSameSizeAs(expectedEntries)
                .containsExactlyInAnyOrderEntriesOf(expectedEntries);
    }

    @ParameterizedTest(name = "#{index} - <{0}> is anagram of <{1}> to be <{2}>")
    @MethodSource("isAnagramArgs")
    void isAnagramTest(String s1, String s2, boolean expectedResult) {
        boolean actualResult = ExercisesJava8.isAnagram(s1, s2);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "#{index} - secondSmallestElement of <{0}> is <{1}>")
    @MethodSource("secondSmallestElementArgs")
    void secondSmallestElementTest(int[] inputArr, int expectedSecondMin) {
        int actualSecondMin = ExercisesJava8.getSecondSmallestElement(inputArr);
        assertThat(actualSecondMin).isEqualTo(expectedSecondMin);
    }
}