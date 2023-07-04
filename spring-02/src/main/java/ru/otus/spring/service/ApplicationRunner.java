package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.UserAnswer;

import java.util.ArrayList;
import java.util.List;

public class ApplicationRunner {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final IOService ioService;
    private final AssessmentService assessmentService;

    public ApplicationRunner(QuestionService questionService, QuestionMapper questionMapper, IOService ioService, AssessmentService assessmentService) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
        this.ioService = ioService;
        this.assessmentService = assessmentService;
    }

    /**
     * спрашиваем имя
     * сохраняем имя
     * получаем вопросы из сервиса
     * для всех вопросов
     * присылаем вопрос
     * получаем ответ
     * сравниваем с правильным ответом
     * сохраняем результат
     * выводим результат тестирования
     */
    public void run() {
        String userName = ioService.readStringWithPrompt("Print your name");
        String userSurname = ioService.readStringWithPrompt("Print your surname");
        ioService.printLn(String.format("Hello %s %s Now we start test", userSurname, userName));

        List<Question> questions = questionService.getAll();

        List<UserAnswer> userAnswers = new ArrayList<>();
        for (Question question : questions) {
            ioService.printLn(questionMapper.toString(question) + System.lineSeparator());
            int answer = ioService.readInt();
            userAnswers.add(new UserAnswer(question.getId(), answer));
        }

        boolean passed = assessmentService.isPassed(userAnswers, questions);
        if (passed) {
            ioService.printLn("Congratulation! You passed the test.");
        } else {
            ioService.printLn("You didn't pass the test but you can try again");
        }
    }
}
