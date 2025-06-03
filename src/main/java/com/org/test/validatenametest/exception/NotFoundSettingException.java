package com.org.test.validatenametest.exception;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public class NotFoundSettingException extends RuntimeException {
    public NotFoundSettingException() {
        super("Настройки не были найдена");
        log.error(getMessage());
    }
    public NotFoundSettingException(String subscription) {
        super(MessageFormat.format("Настройка {0} не была найдена", subscription));
        log.error(getMessage());
    }
}
