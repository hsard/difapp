package com.waes.diffapp.validation.validators.impl;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.waes.diffapp.constants.DiffStatus;
import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.validation.validators.DefaultAbstractValidator;

/**
 *  Ensures diff sides contents not to be blank (null or empty)
 *  Sets result status: INCOMPLETE_CONTENT, see {@link DiffStatus}
 *
 * @author HS
 */
public class ContentValidator extends DefaultAbstractValidator {

    @Override
    public boolean conditionToValidate(DiffEntity diffEntity, DiffResult diffResult) {
        return isNotBlank(diffEntity.getLeftSide()) && isNotBlank(diffEntity.getRightSide());
    }

    @Override
    public DiffStatus failStatus() {
        return DiffStatus.INCOMPLETE_CONTENT;
    }
}
