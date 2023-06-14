package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис вывода сообщений")
@ExtendWith(MockitoExtension.class)
class OutputServiceStreamTest {

    @InjectMocks
    private OutputServiceStream outputServiceStream;
    @Mock
    private PrintStream outputStream;

    @Test
    @DisplayName("должен корректно печатать сообщения")
    void test() {
        String expected = "Test string for printing";
        outputServiceStream.outputString(expected);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(outputStream).println(messageCaptor.capture());

        assertThat(messageCaptor.getValue()).isEqualTo(expected);
    }
}