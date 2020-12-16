package com.waes.diffapp.validation.validators;

import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.model.DiffEntity;
/**
 *  Abstract implementation of ChainValidator
 *  adding abstract support of methods for condition validation, success and failure callbacks
 *
 * @author HS
 */
public abstract class AbstractValidator implements ChainValidator {
    private AbstractValidator nextValidator;

    @Override
    public void validate(DiffEntity diffEntity, DiffResult diffResult) {
        if (conditionToValidate(diffEntity, diffResult)) {
            onSuccess(diffEntity, diffResult);
        } else {
            onFailure(diffEntity, diffResult);
        }
    }

    @Override
    public ChainValidator setNextValidator(ChainValidator nextValidator) {
        this.nextValidator = (AbstractValidator) nextValidator;
        return this.nextValidator;
    }

    @Override
    public AbstractValidator getNextValidator() {
        return nextValidator;
    }

    /**
     * Success case callback method
     */
    public abstract void onSuccess(DiffEntity diffEntity, DiffResult diffResult);

    /**
     * Failure case callback method
     */
    public abstract void onFailure(DiffEntity diffEntity, DiffResult diffResult);

    /**
     * Logic of validation method
     */
    public abstract boolean conditionToValidate(DiffEntity diffEntity, DiffResult diffResult);

}
