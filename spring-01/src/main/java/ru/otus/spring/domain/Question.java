package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Question {
    private int id;
    private String text;
    private List<String> answers;
    private int rightAnswer;
}
