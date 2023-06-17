package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Map;

import static java.lang.System.lineSeparator;
import static ru.otus.spring.service.CsvReader.PARAM_NAME_ID;
import static ru.otus.spring.service.CsvReader.PARAM_NAME_QUESTION;
import static ru.otus.spring.service.CsvReader.PARAM_NAME_ANSWERS;
import static ru.otus.spring.service.CsvReader.PARAM_NAME_RIGHT;

public class QuestionMapper {
    public Question toQuestion(Map<String, String> questionString) {
        return Question.builder()
                .id(Integer.parseInt(questionString.get(PARAM_NAME_ID)))
                .text(questionString.get(PARAM_NAME_QUESTION))
                .answers(parseAnswers(questionString.get(PARAM_NAME_ANSWERS)))
                .rightAnswer(Integer.parseInt(questionString.get(PARAM_NAME_RIGHT)))
                .build();
    }

    private List<String> parseAnswers(String inner) {
        return List.of(inner.split(","));
    }

    public String toString(Question question) {
        StringBuilder builder = new StringBuilder();
        builder.append("Question ").append(question.getId())
                .append(" - ").append(question.getText())
                .append(lineSeparator());

        builder.append("Answers: ").append(lineSeparator());
        int counter = 1;
        for (String answer : question.getAnswers()) {
            builder.append(counter).append(". ").append(answer).append(lineSeparator());
            counter++;
        }

        return builder.toString();
    }
}
