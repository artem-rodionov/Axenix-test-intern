package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ListSorterTest {

    @ParameterizedTest(name = "Merge sort: {0}")
    @MethodSource("provideTestDataForSortingNaturalOrderInteger")
    public void mergeSortIntegerNaturalOrderTest(String name, List<Integer> input, List<Integer> expected) {
        ListSorter.mergeSort(input);
        assertEquals(expected, input);
    }

    @ParameterizedTest(name = "Merge sort reverse: {0}")
    @MethodSource("provideTestDataForSortingNaturalOrderInteger")
    public void mergeSortIntegerReverseOrderTest(String name, List<Integer> input, List<Integer> expected) {
        ListSorter.mergeSort(input, Comparator.reverseOrder());
        if (expected != null) {
            assertEquals(expected.reversed(), input);
        } else {
            assertNull(input);
        }
    }

    @ParameterizedTest(name = "Merge sort custom object: {0}")
    @MethodSource("provideTestDataForSortingCustomObjects")
    public void mergeSortCustomObjectTest(String name, List<Worker> input, List<Worker> expected) {
        ListSorter.mergeSort(input, (w1, w2) -> (w1.surname + w1.name).compareToIgnoreCase(w2.surname + w2.name));
        assertEquals(expected, input);
    }

    @ParameterizedTest(name = "Merge sort custom object: {0}")
    @MethodSource("provideTestDataForStableSortingCustomObjects")
    public void mergeSortStableCustomObjectTest(String name, List<Worker> input, List<Worker> expected) {
        ListSorter.mergeSort(input, (w1, w2) -> (w1.surname + w1.name).compareToIgnoreCase(w2.surname + w2.name));
        assertEquals(expected, input);
    }

    @ParameterizedTest(name = "Quick sort: {0}")
    @MethodSource("provideTestDataForSortingNaturalOrderInteger")
    public void quickSortIntegerNaturalOrderTest(String name, List<Integer> input, List<Integer> expected) {
        ListSorter.quickSort(input);
        assertEquals(expected, input);
    }

    @ParameterizedTest(name = "Quick sort: {0}")
    @MethodSource("provideTestDataForSortingNaturalOrderInteger")
    public void quickSortIntegerReverseOrderTest(String name, List<Integer> input, List<Integer> expected) {
        ListSorter.quickSort(input, Comparator.reverseOrder());
        if (expected != null) {
            assertEquals(expected.reversed(), input);
        } else {
            assertNull(input);
        }
    }

    @ParameterizedTest(name = "Quick sort custom object: {0}")
    @MethodSource("provideTestDataForSortingCustomObjects")
    public void quickSortCustomObjectTest(String name, List<Worker> input, List<Worker> expected) {
        ListSorter.quickSort(input, (w1, w2) -> (w1.surname + w1.name).compareToIgnoreCase(w2.surname + w2.name));
        assertEquals(expected, input);
    }

    private static Stream<Arguments> provideTestDataForSortingNaturalOrderInteger() {
        return Stream.of(
                Arguments.of("null значения",
                        null,
                        null),

                Arguments.of("Пустой список",
                        new ArrayList<>(),
                        List.of()),

                Arguments.of("Список из одного элемента",
                        new ArrayList<>(List.of(1)),
                        List.of(1)),

                Arguments.of("Список из двух элементов отсортированный",
                        new ArrayList<>(List.of(1, 2)),
                        List.of(1, 2)),

                Arguments.of("Список из двух элементов неотсортированный",
                        new ArrayList<>(List.of(2, 1)),
                        List.of(1, 2)),

                Arguments.of("Список из трёх элементов",
                        new ArrayList<>(List.of(3, 1, 2)),
                        List.of(1, 2, 3)),

                Arguments.of("Отсортированный список",
                        new ArrayList<>(List.of(1, 2, 3, 4, 5)),
                        List.of(1, 2, 3, 4, 5)),

                Arguments.of("Список в обратном порядке",
                        new ArrayList<>(List.of(5, 4, 3, 2, 1)),
                        List.of(1, 2, 3, 4, 5)),

                Arguments.of("Длинный список в обратном порядке",
                        new ArrayList<>(List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)),
                        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)),

                Arguments.of("Список с дубликатами",
                        new ArrayList<>(List.of(3, 1, 2, 1, 3, 2)),
                        List.of(1, 1, 2, 2, 3, 3)),

                Arguments.of("Все элементы одинаковые",
                        new ArrayList<>(List.of(7, 7, 7, 7)),
                        List.of(7, 7, 7, 7)),

                Arguments.of("Список с отрицательными числами",
                        new ArrayList<>(List.of(-3, 5, -1, 0, -7, 2)),
                        List.of(-7, -3, -1, 0, 2, 5)),

                Arguments.of("Отрицательные в обратном порядке",
                        new ArrayList<>(List.of(-1, -2, -3, -4, -5)),
                        List.of(-5, -4, -3, -2, -1)),

                Arguments.of("Границы int",
                        new ArrayList<>(List.of(Integer.MAX_VALUE, 0, Integer.MIN_VALUE)),
                        List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE)),

                Arguments.of("Случайный порядок",
                        new ArrayList<>(List.of(38, 27, 43, 3, 9, 82, 10)),
                        List.of(3, 9, 10, 27, 38, 43, 82)),

                Arguments.of("Чётное количество элементов",
                        new ArrayList<>(List.of(4, 2, 8, 6, 1, 3)),
                        List.of(1, 2, 3, 4, 6, 8)),

                Arguments.of("Нечётное количество элементов",
                        new ArrayList<>(List.of(9, 5, 1, 7, 3)),
                        List.of(1, 3, 5, 7, 9))
        );
    }

    private static Stream<Arguments> provideTestDataForSortingCustomObjects() {
        return Stream.of(
                Arguments.of("Пустой список",
                        new ArrayList<Worker>(),
                        List.of()),

                Arguments.of("Один работник",
                        new ArrayList<>(List.of(
                                new Worker(1, "Александр", "Иванов"))),
                        List.of(
                                new Worker(1, "Александр", "Иванов"))),

                Arguments.of("Два работника в обратном порядке",
                        new ArrayList<>(List.of(
                                new Worker(1, "Александр", "Иванов"),
                                new Worker(2, "Михаил", "Гайкин"))),
                        List.of(
                                new Worker(2, "Михаил", "Гайкин"),
                                new Worker(1, "Александр", "Иванов"))),

                Arguments.of("Три работника разных фамилий",
                        new ArrayList<>(List.of(
                                new Worker(1, "Дмитрий", "Кузнецов"),
                                new Worker(2, "Михаил", "Гайкин"),
                                new Worker(3, "Борис", "Петров"))),
                        List.of(
                                new Worker(2, "Михаил", "Гайкин"),
                                new Worker(1, "Дмитрий", "Кузнецов"),
                                new Worker(3, "Борис", "Петров"))),

                Arguments.of("Одинаковые фамилии, разные имена",
                        new ArrayList<>(List.of(
                                new Worker(1, "Михаил", "Иванов"),
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(3, "Борис", "Иванов"))),
                        List.of(
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(3, "Борис", "Иванов"),
                                new Worker(1, "Михаил", "Иванов"))),

                Arguments.of("Обратный алфавитный порядок",
                        new ArrayList<>(List.of(
                                new Worker(1, "Пётр", "Яковлев"),
                                new Worker(2, "Сергей", "Смирнов"),
                                new Worker(3, "Борис", "Петров"),
                                new Worker(4, "Дмитрий", "Кузнецов"),
                                new Worker(5, "Александр", "Иванов"),
                                new Worker(6, "Михаил", "Гайкин"))),
                        List.of(
                                new Worker(6, "Михаил", "Гайкин"),
                                new Worker(5, "Александр", "Иванов"),
                                new Worker(4, "Дмитрий", "Кузнецов"),
                                new Worker(3, "Борис", "Петров"),
                                new Worker(2, "Сергей", "Смирнов"),
                                new Worker(1, "Пётр", "Яковлев"))),

                Arguments.of("Уже отсортированный список",
                        new ArrayList<>(List.of(
                                new Worker(1, "Михаил", "Гайкин"),
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(3, "Дмитрий", "Кузнецов"),
                                new Worker(4, "Борис", "Петров"))),
                        List.of(
                                new Worker(1, "Михаил", "Гайкин"),
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(3, "Дмитрий", "Кузнецов"),
                                new Worker(4, "Борис", "Петров")))
        );
    }

    private static Stream<Arguments> provideTestDataForStableSortingCustomObjects() {
        return Stream.of(
                Arguments.of("Устойчивость: два одинаковых ключа",
                        new ArrayList<>(List.of(
                                new Worker(1, "Александр", "Иванов"),
                                new Worker(2, "Александр", "Иванов"))),
                        List.of(
                                new Worker(1, "Александр", "Иванов"),
                                new Worker(2, "Александр", "Иванов"))),

                Arguments.of("Устойчивость: два одинаковых ключа, обратный порядок id",
                        new ArrayList<>(List.of(
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(1, "Александр", "Иванов"))),
                        List.of(
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(1, "Александр", "Иванов"))),

                Arguments.of("Устойчивость: три одинаковых ключа",
                        new ArrayList<>(List.of(
                                new Worker(3, "Александр", "Иванов"),
                                new Worker(1, "Александр", "Иванов"),
                                new Worker(2, "Александр", "Иванов"))),
                        List.of(
                                new Worker(3, "Александр", "Иванов"),
                                new Worker(1, "Александр", "Иванов"),
                                new Worker(2, "Александр", "Иванов"))),

                Arguments.of("Устойчивость: дубликаты разбавлены другими ключами",
                        new ArrayList<>(List.of(
                                new Worker(1, "Александр", "Иванов"),
                                new Worker(2, "Михаил", "Гайкин"),
                                new Worker(3, "Александр", "Иванов"),
                                new Worker(4, "Михаил", "Гайкин"),
                                new Worker(5, "Александр", "Иванов"))),
                        List.of(
                                new Worker(2, "Михаил", "Гайкин"),
                                new Worker(4, "Михаил", "Гайкин"),
                                new Worker(1, "Александр", "Иванов"),
                                new Worker(3, "Александр", "Иванов"),
                                new Worker(5, "Александр", "Иванов"))),

                Arguments.of("Устойчивость: регистр ключа не влияет",
                        new ArrayList<>(List.of(
                                new Worker(1, "александр", "иванов"),
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(3, "АЛЕКСАНДР", "ИВАНОВ"))),
                        List.of(
                                new Worker(1, "александр", "иванов"),
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(3, "АЛЕКСАНДР", "ИВАНОВ"))),

                Arguments.of("Устойчивость: регистр, обратный порядок id",
                        new ArrayList<>(List.of(
                                new Worker(3, "АЛЕКСАНДР", "ИВАНОВ"),
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(1, "александр", "иванов"))),
                        List.of(
                                new Worker(3, "АЛЕКСАНДР", "ИВАНОВ"),
                                new Worker(2, "Александр", "Иванов"),
                                new Worker(1, "александр", "иванов"))),

                Arguments.of("Устойчивость: смешанные ключи",
                        new ArrayList<>(List.of(
                                new Worker(5, "Александр", "Иванов"),
                                new Worker(1, "Михаил", "Гайкин"),
                                new Worker(3, "Александр", "Иванов"),
                                new Worker(2, "Михаил", "Гайкин"),
                                new Worker(4, "Александр", "Иванов"))),
                        List.of(
                                new Worker(1, "Михаил", "Гайкин"),
                                new Worker(2, "Михаил", "Гайкин"),
                                new Worker(5, "Александр", "Иванов"),
                                new Worker(3, "Александр", "Иванов"),
                                new Worker(4, "Александр", "Иванов")))
        );
    }

    record Worker(Integer id, String name, String surname) {
    }

}