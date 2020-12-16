package com.waes.diffapp.util;

import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

public class MessageSourceUtil {

    private MessageSourceUtil() {
    }

    public static String getLocalizedMessage(String key) {
        return ResourceBundle.getBundle("messages", LocaleContextHolder.getLocale()).getString(key);
    }
}
