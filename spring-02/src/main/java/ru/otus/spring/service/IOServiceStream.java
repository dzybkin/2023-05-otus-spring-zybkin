package ru.otus.spring.service;

import java.io.PrintStream;

public class IOServiceStream implements IOService {

    private final PrintStream outputStream;

    public IOServiceStream(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void printLn(String inner) {
        outputStream.println(inner);
    }

    @Override
    public int readInt() {
        return 0;
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        return "";
    }
}
