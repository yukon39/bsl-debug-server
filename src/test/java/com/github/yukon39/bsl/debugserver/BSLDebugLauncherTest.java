package com.github.yukon39.bsl.debugserver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import static org.junit.jupiter.api.Assertions.*;

class BSLDebugLauncherTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @ExpectSystemExitWithStatus(2)
    void testParseError() {

        // given
        String[] args = new String[]{"--error"};

        // when
        try {
            BSLDebugLauncher.main(args);
        } catch (RuntimeException ignored) {
            // catch prevented system.exit call
        }

        // then
        assertThat(errContent.toString()).containsIgnoringCase("Unknown option: '--error'");
    }
}