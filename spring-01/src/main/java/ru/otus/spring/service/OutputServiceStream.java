package ru.otus.spring.service;

import java.io.PrintStream;

public class OutputServiceStream implements OutputService {

    private final PrintStream outputStream;

    public OutputServiceStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void printLn(String inner) {
        outputStream.println(inner);
    }
}
