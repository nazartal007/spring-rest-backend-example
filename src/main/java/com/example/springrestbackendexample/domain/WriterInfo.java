package com.example.springrestbackendexample.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WriterInfo {

    private String writerName;
    private List<String> books;
    private String message;
}
