package com.github.yukon39.bsl.debugserver.configuration;

/**
 * Язык для сообщений, ресурсов и прочих взаимододействий между
 * BSL Debug Server и пользователем.
 */
public enum Language {

    /**
     * Русский
     */
    RU("ru"),

    /**
     * Английский
     */
    EN("en");

    /**
     * Язык по умолчанию
     */
    public static final Language DEFAULT_LANGUAGE = EN;

    private final String languageCode;

    /**
     * @param languageCode код языка в соответствии с {@link java.util.Locale#getLanguage()}
     */
    Language(String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * @return код языка в соответствии с {@link java.util.Locale#getLanguage()}
     */
    public String getLanguageCode() {
        return languageCode;
    }
}
