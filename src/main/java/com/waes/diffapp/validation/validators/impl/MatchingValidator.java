package com.waes.diffapp.validation.validators.impl;

import static com.waes.diffapp.constants.AnalyzerType.CUSTOM;

import java.util.Objects;

import com.waes.diffapp.constants.DiffStatus;
import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.validation.analyzers.AnalyzerFactory;
import com.waes.diffapp.validation.validators.DefaultAbstractValidator;

/**
 * Ensures diff sides contents to match
 * Success case: diff result status is set to MATCHING_CONTENT, see {@link DiffStatus}
 * Failure case: diff result status is set to MISMATCHING_CONTENT, see {@link DiffStatus}
 * and Custom Analyzer {@link com.waes.diffapp.validation.analyzers.CustomAnalyzer} is executed
 * to report actual diffs(offsets and lengths)
 *
 * @author HS
 */
public class MatchingValidator extends DefaultAbstractValidator {

    @Override
    public boolean conditionToValidate(DiffEntity diffEntity, DiffResult diffResult) {
        return diffEntity.getLeftSide().equals(diffEntity.getRightSide());
    }

    @Override
    public void onSuccess(DiffEntity diffEntity, DiffResult diffResult) {
        diffResult.setResultCode(DiffStatus.MATCHING_CONTENT);
    }

    @Override
    public void onFailure(DiffEntity diffEntity, DiffResult diffResult) {
        super.onFailure(diffEntity, diffResult);
        Objects.requireNonNull(AnalyzerFactory.get(CUSTOM)).analyze(diffEntity, diffResult);
    }

    @Override
    public DiffStatus failStatus() {
        return DiffStatus.MISMATCHING_CONTENT;
    }
}
