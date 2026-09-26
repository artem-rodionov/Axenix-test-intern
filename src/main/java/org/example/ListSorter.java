package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Collections.swap;

/**
 * Класс для сортировки списков
 */
public class ListSorter {

    /**
     * Метод быстрой сортировки для элементов реализующих Comparable.
     * Временная сложность: O(n log n) в среднем, O(n^2) в худшем
     * Пространственная сложность: O(log n) — стек рекурсии
     *
     * @param list Сортируемый список
     * @param <T> Тип элемента списка
     */
    public static <T extends Comparable<? super T>> void quickSort(List<T> list) {
        quickSort(list, Comparator.naturalOrder());
    }

    /**
     * Метод быстрой сортировки, принимающий компаратор для типа T.
     * Временная сложность: O(n log n) в среднем, O(n^2) в худшем
     * Пространственная сложность: O(log n) — стек рекурсии
     *
     * @param list Сортируемый список
     * @param comparator Компаратор, который умеет сравнивать T или его родителя
     * @param <T> Тип элемента списка
     */
    public static <T> void quickSort(List<T> list, Comparator<? super T> comparator) {
        if (list == null || list.size() < 2) return;
        quickSort(0, list.size() - 1, list, comparator);
    }

    /**
     * Метод сортировки слиянием для элементов реализующих Comparable.
     * Временная сложность: O(n log n)
     * Пространственная сложность: O(n)
     *
     * @param list Сортируемый список
     * @param <T> Тип элемента списка
     */
    public static <T extends Comparable<? super T>> void mergeSort(List<T> list) {
        mergeSort(list, Comparator.naturalOrder());
    }

    /**
     * Метод сортировки слиянием, принимающий компаратор для типа T.
     * Временная сложность: O(n log n)
     * Пространственная сложность: O(n)
     *
     * @param list Сортируемый список
     * @param comparator Компаратор, который умеет сравнивать T или его родителя
     * @param <T> Тип элемента списка
     */
    public static <T> void mergeSort(List<T> list, Comparator<? super T> comparator) {
        if (list == null || list.size() < 2) return;
        List<T> temp = new ArrayList<>(list);
        mergeSort(0, list.size() - 1, list, temp, comparator);
    }

    /**
     * Рекурсивная реализация быстрой сортировки для диапазона [l..r].
     * Временная сложность: O(n log n) в среднем, O(n^2) в худшем
     * Пространственная сложность: O(log n) — стек рекурсии
     *
     * @param l начало сортируемой части
     * @param r конец сортируемой части
     * @param list Сортируемый список
     * @param comp Компаратор, который умеет сравнивать T или его родителя
     * @param <T> Тип элемента списка
     */
    private static <T> void quickSort(int l, int r, List<T> list, Comparator<? super T> comp) {
        if (l >= r) return;

        int[] bounds = partition(l, r, list, comp);
        int i = bounds[0];
        int j = bounds[1];

        quickSort(l, j, list, comp);
        quickSort(i, r, list, comp);
    }

    /**
     * Рекурсивная реализация сортировки слиянием для диапазона [l..r].
     * Временная сложность: O(n log n)
     * Пространственная сложность: O(n)
     *
     * @param l начало сортируемой части
     * @param r конец сортируемой части
     * @param list Сортируемый список
     * @param temp временный буфер для слияния
     * @param comp Компаратор, который умеет сравнивать T или его родителя
     * @param <T> Тип элемента списка
     */
    private static <T> void mergeSort(int l, int r, List<T> list, List<T> temp, Comparator<? super T> comp) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        mergeSort(l, m, list, temp, comp);
        mergeSort(m + 1, r, list, temp, comp);
        merge(l, m, r, list, temp, comp);
    }

    /**
     * Метод разбивающий массив относительно опорного элемента,
     * принимает компаратор для типа T и границы сортируемой части списка.
     * Временная сложность: O(r - l)
     * Пространственная сложность: O(1)
     *
     * @param l начало сортируемой части
     * @param r конец сортируемой части
     * @param list Сортируемый список
     * @param comp Компаратор, который умеет сравнивать T или его родителя
     * @param <T> Тип элемента списка
     * @return массив из двух границ {i, j}: элементы [l..j] <= опорного,
     * элементы [i..r] >= опорного
     */
    private static <T> int[] partition(int l, int r, List<T> list, Comparator<? super T> comp) {
        int pivotIndex = ThreadLocalRandom.current().nextInt(l, r + 1);
        T x = list.get(pivotIndex);

        int i = l;
        int j = r;

        while (i <= j) {
            while (comp.compare(list.get(i), x) < 0) i++;
            while (comp.compare(list.get(j), x) > 0) j--;
            if (i <= j) {
                swap(list, i, j);
                i++;
                j--;
            }
        }

        return new int[]{i, j};
    }

    /**
     * Метод сливающий два массива, принимает компаратор для типа T и границы сортируемой части списка.
     * Временная сложность: O(r - l)
     * Пространственная сложность: O(1)
     *
     * @param l начало сортируемой части
     * @param m индекс середины, конец левой половины
     * @param r конец сортируемой части
     * @param list Сортируемый список
     * @param temp временный буфер для слияния
     * @param comp Компаратор, который умеет сравнивать T или его родителя
     * @param <T> Тип элемента списка
     */
    private static <T> void merge(int l, int m, int r, List<T> list, List<T> temp, Comparator<? super T> comp) {
        int l_cur = l;
        int r_cur = m + 1;
        for (int i = l; i <= r; i++) {
            if (l_cur > m) {
                temp.set(i, list.get(r_cur));
                r_cur++;
            } else if (r_cur > r) {
                temp.set(i, list.get(l_cur));
                l_cur++;
            } else if (comp.compare(list.get(l_cur), list.get(r_cur)) <= 0) {
                temp.set(i, list.get(l_cur));
                l_cur++;
            } else {
                temp.set(i, list.get(r_cur));
                r_cur++;
            }
        }

        for (int i = l; i <= r; i++) {
            list.set(i, temp.get(i));
        }
    }
}
