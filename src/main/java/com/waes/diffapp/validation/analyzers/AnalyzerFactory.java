package com.waes.diffapp.validation.analyzers;

import com.waes.diffapp.constants.AnalyzerType;

/**
 * Analyzers Factory
 *
 * @author HS
 */
public class AnalyzerFactory {

    private AnalyzerFactory() {
    }

    /**
     * Get analyzer instance for the key
     *
     * @author HS
     */
    public static Analyzer get(AnalyzerType type) {

        if (type == AnalyzerType.CUSTOM) {
            return new CustomAnalyzer();
        }
        return null;
    }
}
