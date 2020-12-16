package com.waes.diffapp.validation.analyzers;

import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.model.DiffEntity;

/**
 * Basic Analyzer interface
 *
 * @author HS
 */
public interface Analyzer {

    /**
     * Analyze method for running comparision analyzes for diffEntity and putting results into diffResult
     *
     * @param diffEntity entity to analyze
     * @param diffResult resulting object containing analyze details
     * @author HS
     */
    void analyze(DiffEntity diffEntity, DiffResult diffResult);
}
