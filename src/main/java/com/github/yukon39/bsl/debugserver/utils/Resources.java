package com.github.yukon39.bsl.debugserver.utils;

import com.github.yukon39.bsl.debugserver.configuration.Language;
import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Вспомогательный класс для оптимизированного чтения ресурсов прикладных классов с учетом {@link Language}.
 */
@UtilityClass
public class Resources {
    /**
     * @param language Язык получения ресурсной строки.
     * @param clazz    Класс, ресурсы которого необходимо прочитать.
     * @param key      Ключ из {@link ResourceBundle}.
     * @return Содержимое ресурса.
     */
    public String getResourceString(Language language, Class<?> clazz, String key) {
        String languageCode = language.getLanguageCode();
        Locale locale = Locale.forLanguageTag(languageCode);
        return ResourceBundle.getBundle(clazz.getName(), locale, new UTF8Control()).getString(key).intern();
    }

    /**
     * @param language Язык получения ресурсной строки.
     * @param clazz    Класс, ресурсы которого необходимо прочитать.
     * @param key      Ключ из {@link ResourceBundle}.
     * @param args     Аргументы для форматирования ресурсной строки.
     * @return Содержимое ресурса.
     */
    public String getResourceString(Language language, Class<?> clazz, String key, Object... args) {
        return String.format(getResourceString(language, clazz, key), args).intern();
    }
}
