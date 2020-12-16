package com.waes.diffapp.validation.analyzers;

import static org.junit.Assert.assertEquals;

import java.util.Objects;

import com.waes.diffapp.constants.AnalyzerType;
import org.junit.jupiter.api.Test;

class AnalyzerFactoryTest {

    @Test
    void get() {
        assertEquals(CustomAnalyzer.class, Objects.requireNonNull(AnalyzerFactory.get(AnalyzerType.CUSTOM)).getClass());
    }
}