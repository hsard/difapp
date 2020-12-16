package com.waes.diffapp.validation.validators.impl;

import com.waes.diffapp.constants.DiffStatus;
import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.validation.validators.DefaultAbstractValidator;

/**
 *  Ensures diff sides contents to have the same size
 *  Sets result status: MISMATCHING_SIZES, see {@link DiffStatus}
 *
 * @author HS
 */
public class SizeValidator extends DefaultAbstractValidator {

    @Override
    public boolean conditionToValidate(DiffEntity diffEntity, DiffResult diffResult) {
        return diffEntity.getLeftSide().length() == diffEntity.getRightSide().length();
    }

    @Override
    public DiffStatus failStatus() {
        return DiffStatus.MISMATCHING_SIZES;
    }
}
