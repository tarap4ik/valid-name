package com.org.test.validatenametest.exception;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public class NotFoundRequestContentException extends RuntimeException {
    public NotFoundRequestContentException() {
        super("JSON не были найдены");
        log.error(getMessage());
    }
}
