package com.waes.diffapp.validation.validators.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

import com.waes.diffapp.constants.DiffSide;
import com.waes.diffapp.constants.DiffStatus;
import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.validation.validators.AbstractValidator;

public abstract class ValidatorTest<T extends AbstractValidator> {

    public void mockOnSuccess(T validator, DiffEntity diffEntity, DiffResult diffResult) {
        doNothing().when(validator).onSuccess(diffEntity, diffResult);
    }

    public void validateAndExpect(T validator, String leftContent, String rightContent, DiffStatus expectedStatus) {
        DiffEntity diffEntity = new DiffEntity();
        diffEntity.setSideContent(DiffSide.LEFT, leftContent);
        diffEntity.setSideContent(DiffSide.RIGHT, rightContent);

        DiffResult diffResult = new DiffResult();
        mockOnSuccess(validator,diffEntity, diffResult);
        validator.validate(diffEntity, diffResult);

        assertEquals(expectedStatus, diffResult.getResultCode());
    }
}
