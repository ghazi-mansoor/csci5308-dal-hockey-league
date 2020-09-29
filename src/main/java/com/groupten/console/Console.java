package com.groupten.console;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Console implements ConsoleInterface {

    private InputStream in;
    private PrintStream out;
    private Scanner sc;

    public Console() {
        in = System.in;
        out = System.out;
        sc = new Scanner(in);
    }

    @Override
    public void printLine(String line) {
        out.println(line);
    }

    @Override
    public String readLine() {
        return sc.nextLine();
    }

    @Override
    public int readInteger() {
        return sc.nextInt();
    }
}
