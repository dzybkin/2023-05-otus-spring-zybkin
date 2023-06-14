package ru.otus.spring.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import ru.otus.spring.exception.ReaderException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader implements Reader {

    public static final String PARAM_NAME_ID = "id";
    public static final String PARAM_NAME_QUESTION = "question";
    public static final String PARAM_NAME_ANSWERS = "answers";
    public static final String PARAM_NAME_RIGHT = "right";
    private final String fileAddress;

    public CsvReader(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    @Override
    public List<Map<String, String>> readAllLines() {
        InputStream inputStream = CsvReader.class.getResourceAsStream(fileAddress);
        InputStreamReader streamReader = new InputStreamReader(inputStream);

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator
        try (CSVReader reader = new CSVReaderBuilder(streamReader)
                .withCSVParser(csvParser)
                .withSkipLines(1)           // skip the first line, header info
                .build()) {
            return rowsToMap(reader.readAll());
        } catch (CsvException | IOException e) {
            throw new ReaderException("Exception with writing file " + fileAddress, e);
        }
    }

    private List<Map<String, String>> rowsToMap(List<String[]> rows) {
        List<Map<String, String>> rowsWithNames = new ArrayList<>();
        for (String[] row : rows) {
            Map<String, String> rowWithNames = new HashMap<>();
            rowWithNames.put(PARAM_NAME_ID, row[0]);
            rowWithNames.put(PARAM_NAME_QUESTION, row[1]);
            rowWithNames.put(PARAM_NAME_ANSWERS, row[2]);
            rowWithNames.put(PARAM_NAME_RIGHT, row[3]);
            rowsWithNames.add(rowWithNames);
        }
        return rowsWithNames;
    }
}
