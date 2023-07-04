package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.UserAnswer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssessmentServiceImpl implements AssessmentService {

    private final int numberOfCorrectAnswersToPass;

    public AssessmentServiceImpl(int numberOfCorrectAnswersToPass) {
        this.numberOfCorrectAnswersToPass = numberOfCorrectAnswersToPass;
    }

    /**
     * If count right user answers greater than or equal to numberOfCorrectAnswersToPass than true
     */
    @Override
    public boolean isPassed(List<UserAnswer> userAnswers, List<Question> questions) {
        Map<Integer, Integer> rightAnswers = questions
                .stream()
                .collect(Collectors.toMap(Question::getId, Question::getRightAnswer));

        int countRightAnswers = 0;
        for (UserAnswer userAnswer : userAnswers) {
            if (userAnswer.getAnswer() == rightAnswers.get(userAnswer.getIdQuestion())) {
                countRightAnswers++;
            }
        }

        return countRightAnswers >= numberOfCorrectAnswersToPass;
    }
}
