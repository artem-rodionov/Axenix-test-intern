package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        int[] sizes = {1_000, 10_000, 100_000, 1_000_000};

        for (int size : sizes) {
            System.out.println("=== n = " + size + " ===");

            run("mergeSort random",   size, randomList(size),       ListSorter::mergeSort);
            run("mergeSort sorted",   size, sortedList(size),       ListSorter::mergeSort);
            run("mergeSort reversed", size, reversedList(size),     ListSorter::mergeSort);

            run("quickSort random",   size, randomList(size),       ListSorter::quickSort);
            run("quickSort sorted",   size, sortedList(size),       ListSorter::quickSort);
            run("quickSort reversed", size, reversedList(size),     ListSorter::quickSort);

            run("Collections.sort",   size, randomList(size),       Collections::sort);

            System.out.println();
        }
    }

    private static void run(String name, int size, List<Integer> data,
                            Consumer<List<Integer>> sorter) {
        // Прогрев
        for (int i = 0; i < 3; i++) {
            List<Integer> copy = new ArrayList<>(data);
            sorter.accept(copy);
        }

        // Замер
        int rounds = 5;
        long total = 0;
        for (int i = 0; i < rounds; i++) {
            List<Integer> copy = new ArrayList<>(data);
            long start = System.nanoTime();
            sorter.accept(copy);
            long end = System.nanoTime();
            total += (end - start);
        }
        long avgMs = total / rounds / 1_000_000;
        System.out.printf("  %-20s %,d ms%n", name, avgMs);
    }

    private static List<Integer> randomList(int n) {
        Random rnd = new Random(42);
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) list.add(rnd.nextInt());
        return list;
    }

    private static List<Integer> sortedList(int n) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) list.add(i);
        return list;
    }

    private static List<Integer> reversedList(int n) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) list.add(n - i);
        return list;
    }
}