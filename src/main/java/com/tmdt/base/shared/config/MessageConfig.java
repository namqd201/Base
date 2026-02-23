package com.tmdt.base.shared.config;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageConfig {
    private static MessageSource messageSource;

    public MessageConfig(MessageSource messageSource) {
        MessageConfig.messageSource = messageSource;
    }

    public static String get(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    public static String get(String code, Object... args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }
}
