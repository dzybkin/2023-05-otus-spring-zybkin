package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

public class ApplicationRunner {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final OutputService outputService;

    public ApplicationRunner(QuestionService questionService, QuestionMapper questionMapper, OutputService outputService) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
        this.outputService = outputService;
    }

    public void run() {
        List<Question> questions = questionService.getAll();
        questions.stream()
                .map(question -> questionMapper.toString(question) + System.lineSeparator())
                .forEach(outputService::outputString);
    }
}
