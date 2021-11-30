package com.cs316.money_mind.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Localization
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class Localization {

    @Autowired
    Environment env;

    @Autowired
    MessageSource messageSource;

    /**
     * get.Message
     * Language file-аас мессежнүүд авах
     *
     * @param messageKey - prop key
     **/

    public String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
    }

    /**
     * get.Message
     * Language file-аас мессежнүүд авах
     *
     * @param messageKey - prop key
     * @param obj        - хувьсагчид
     **/

    public String getMessage(String messageKey, Object[] obj) {
        return messageSource.getMessage(messageKey, obj, LocaleContextHolder.getLocale());
    }

}
