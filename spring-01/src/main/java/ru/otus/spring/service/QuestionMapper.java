package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import static java.lang.System.lineSeparator;

public class QuestionMapper {

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
