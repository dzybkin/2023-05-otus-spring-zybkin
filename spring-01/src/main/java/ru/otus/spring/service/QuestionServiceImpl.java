package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionServiceImpl implements QuestionService {
    private final Reader reader;
    private final QuestionMapper mapper;

    public QuestionServiceImpl(Reader reader, QuestionMapper mapper) {
        this.reader = reader;
        this.mapper = mapper;
    }

    @Override
    public List<Question> getAll() {
        List<Map<String, String>> rows = reader.readAllLines();
        return rows.stream()
                .map(mapper::toQuestion)
                .collect(Collectors.toList());
    }
}
