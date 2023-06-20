package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Ранне приложения")
@ExtendWith(MockitoExtension.class)
class ApplicationRunnerTest {

    @InjectMocks
    private ApplicationRunner runner;

    @Mock
    private QuestionService questionService;
    @Mock
    private QuestionMapper questionMapper;
    @Mock
    private OutputService outputService;

    @Test
    @DisplayName("должен корректно печатать сообщения")
    void test() {
        var questionText1 = "Question 1";
        var questionText2 = "Question 2";
        var expectedInnerText1 = questionText1 + System.lineSeparator();
        var expectedInnerText2 = questionText2 + System.lineSeparator();
        var question1 = mock(Question.class);
        var question2 = mock(Question.class);


        given(questionService.getAll()).willReturn(List.of(question1, question2));
        given(questionMapper.toString(question1)).willReturn(questionText1);
        given(questionMapper.toString(question2)).willReturn(questionText2);

        runner.run();

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(outputService, times(2)).printLn(messageCaptor.capture());

        List<String> allPrintedMessages = messageCaptor.getAllValues();
        assertThat(allPrintedMessages.get(0)).isEqualTo(expectedInnerText1);
        assertThat(allPrintedMessages.get(1)).isEqualTo(expectedInnerText2);
    }
}