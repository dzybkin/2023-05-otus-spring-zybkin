package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.UserAnswer;

import java.util.List;

public interface AssessmentService {
    boolean isPassed(List<UserAnswer> userAnswers, List<Question> questions);
}
