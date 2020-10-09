package com.groupten.statemachine.console;

import java.io.PrintStream;

public interface ConsoleInterface {

    void printLine(Object line);
    String readLine();
    int readInteger();
    PrintStream getOut();

}
