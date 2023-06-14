package ru.otus.spring.service;

import java.util.List;
import java.util.Map;

public interface Reader {
    List<Map<String, String>> readAllLines();
}
