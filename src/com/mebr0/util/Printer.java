package com.mebr0.util;

import java.util.stream.IntStream;

/**
 * Class for printing information to stderr and stdout streams
 * Only static methods
 *
 * @author A.Yergali
 * @version 2.0
 */
public abstract class Printer {

    private Printer() {
        throw new AssertionError("No " + getClass().getSimpleName() + " instances for you!");
    }

    public static void options(String... texts) {
        IntStream.range(0, texts.length).
                mapToObj(i -> (i + 1) + ". " + texts[i]).
                forEach(Printer::print);
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static void out(String text) {
        System.out.print(text);
    }

    public static void error(String text) {
        System.out.println("ERROR -> " + text);
    }
}
