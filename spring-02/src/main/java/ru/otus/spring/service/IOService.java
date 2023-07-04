package ru.otus.spring.service;

public interface IOService {

    void printLn(String s);
    int readInt();
    String readStringWithPrompt(String prompt);
}
