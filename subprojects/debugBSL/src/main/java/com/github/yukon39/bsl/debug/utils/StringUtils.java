package com.github.yukon39.bsl.debug.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class StringUtils {

    @Contract(value = "_ -> new", pure = true)
    public static byte[] toByteArray(char[] charArray) {
        var charBuffer = CharBuffer.wrap(charArray);
        var byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
        return byteBuffer.array();
    }

    @Contract("_ -> new")
    public static byte[] toByteArray(@NotNull String string) {
        return toByteArray(string.toCharArray());
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull String toString(byte[] byteArray) {
        if (Objects.nonNull(byteArray)) {
            return new String(byteArray, StandardCharsets.UTF_8);
        } else {
            return "";
        }
    }
}