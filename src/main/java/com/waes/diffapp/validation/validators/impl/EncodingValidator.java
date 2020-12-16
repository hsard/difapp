package com.waes.diffapp.validation.validators.impl;

import static org.apache.tomcat.util.codec.binary.Base64.isBase64;

import com.waes.diffapp.constants.DiffStatus;
import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.validation.validators.DefaultAbstractValidator;

/**
 * Ensures diff sides contents to be base64 encoded
 * Sets result status: NOT_BASE64_CONTENT, see {@link DiffStatus}
 *
 * @author HS
 */
public class EncodingValidator extends DefaultAbstractValidator {

    @Override
    public boolean conditionToValidate(DiffEntity diffEntity, DiffResult diffResult) {
        return isBase64(diffEntity.getLeftSide()) && isBase64(diffEntity.getRightSide());
    }

    @Override
    public DiffStatus failStatus() {
        return DiffStatus.NOT_BASE64_CONTENT;
    }
}
