package com.example.springrestbackendexample.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class iDontKnowWhatHappened extends Exception {

    private String message;
}
