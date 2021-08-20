package com.example.springrestbackendexample.controller;

import com.example.springrestbackendexample.domain.BookInfo;
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
// methods of working with library applications using spring and lombok, as well as tests for this application
    private Map<String, WriterInfo> writers = new HashMap() {
        {
            put("Пушкин", WriterInfo.builder()
                    .writerName("Пушкин")
                    .books(Arrays.asList("Руслан и Людмила","Золотой петушок"))
                    .build());
            put("Лермонтов", WriterInfo.builder()
                    .writerName("Лермонтов")
                    .books(Arrays.asList("Герой нашего времени","Мцыри"))
                    .build());
            put("Достоевский", WriterInfo.builder()
                    .writerName("Достоевский")
                    .books(Arrays.asList("Братья Карамазовы"))
                    .build());
        }
    };

    @PostMapping("writers/addBook")
    @ApiOperation("Добавить книгу с автором, если автор уже существует, то ему добавится новая книга")
    public WriterInfo doLogin(@RequestBody BookInfo bookInfo) throws iDontKnowWhatHappened {
        if(writers.containsKey(bookInfo.getWriterName()) && writers.get(bookInfo.getWriterName()).getBooks().contains(bookInfo.getBook())) {
            return WriterInfo.builder()
                    .message("Книга уже есть у этого автора.")
                    .writerName(bookInfo.getWriterName())
                    .books(writers.get(bookInfo.getWriterName()).getBooks())
                    .build();
        } else {
            throw new iDontKnowWhatHappened("Что-то пошло не так с добавлением этого всего.");
        }
    }

    @GetMapping("writers/allBooks")
    @ApiOperation("Получение всех книг находящихся в библиотеке")
    public List<WriterInfo> getAllBooksInfo(){
        return writers.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
