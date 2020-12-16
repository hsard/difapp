package com.waes.diffapp.validation.validators;

import com.waes.diffapp.constants.DiffStatus;
import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.model.DiffResult;

/**
 * Default abstract implementation of {@link AbstractValidator}
 * implements success and failure callbacks
 * onSuccess calling validate method of nextvalidator in the chain
 * onFailure setting failure reason status in diffResult, see {@link com.waes.diffapp.constants.DiffStatus}
 *
 * @author HS
 */
public abstract class DefaultAbstractValidator extends AbstractValidator {

    @Override
    public void onSuccess(DiffEntity diffEntity, DiffResult diffResult) {
        if (this.getNextValidator() != null){
            this.getNextValidator().validate(diffEntity, diffResult);
        }
    }

    @Override
    public void onFailure(DiffEntity diffEntity, DiffResult diffResult) {
        diffResult.setResultCode(failStatus());
    }

    public abstract DiffStatus failStatus();
}
