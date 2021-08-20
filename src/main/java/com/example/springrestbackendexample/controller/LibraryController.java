package com.example.springrestbackendexample.controller;

import com.example.springrestbackendexample.domain.BookInfo;
import com.example.springrestbackendexample.domain.Message;
import com.example.springrestbackendexample.domain.WriterInfo;
import com.example.springrestbackendexample.exceptions.iDontKnowWhatHappened;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class LibraryController {
    private Map<String, WriterInfo> writers = new HashMap() {
        {
            put("Пушкин", WriterInfo.builder()
                    .writerName("Пушкин")
                    .books(Arrays.asList("Руслан и Людмила", "Золотой петушок"))
                    .build());
            put("Лермонтов", WriterInfo.builder()
                    .writerName("Лермонтов")
                    .books(Arrays.asList("Герой нашего времени", "Мцыри"))
                    .build());
            put("Достоевский", WriterInfo.builder()
                    .writerName("Достоевский")
                    .books(Arrays.asList("Братья Карамазовы"))
                    .build());
        }
    };

    @PostMapping("writers/addBook")
    @ApiOperation("Добавить книгу с автором, если автор уже существует, то ему добавится новая книга")
    public Object doLogin(@RequestBody BookInfo bookInfo) throws iDontKnowWhatHappened {
        String writer = bookInfo.getWriterName();
        String book = bookInfo.getBook();

        if (writers.containsKey(writer) && writers.get(writer).getBooks().contains(book)) {
            return WriterInfo.builder()
                    .message("Книга с таким названием уже есть у этого автора.")
                    .writerName(writer)
                    .books(writers.get(writer).getBooks())
                    .build();
        } else if (writers.containsKey(writer)) {
            return WriterInfo.builder()
                    .message("Новая книга добавлена автору " + writer)
                    .writerName(writer)
                    .books(writers.get(writer).getBooks())
                    .build();
        } else if (!writers.containsKey(writer)) {
            writers.put(writer, WriterInfo.builder()
                                    .writerName(writer)
                                    .books(Arrays.asList(book))
                                    .build());

            return Message.builder()
                    .message(String.format("Добавлен новый автор %s с книгой %s", writer, book))
                    .build();

        } else {
            throw iDontKnowWhatHappened.builder().message("попробуем эту дичь, но завтра").build();
        }
    }

    @GetMapping("writers/allBooks")
    @ApiOperation("Получение всех книг находящихся в библиотеке")
    public List<WriterInfo> getAllBooksInfo() {
        return writers.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
