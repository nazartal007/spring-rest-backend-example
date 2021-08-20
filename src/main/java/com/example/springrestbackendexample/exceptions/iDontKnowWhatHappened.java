package com.example.springrestbackendexample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class iDontKnowWhatHappened extends Exception  {

    public iDontKnowWhatHappened(String msg) {
        super(msg);
    }

    public iDontKnowWhatHappened() {
        super();
    }
}
