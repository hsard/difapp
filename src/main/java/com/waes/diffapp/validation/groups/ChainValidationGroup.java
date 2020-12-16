package com.waes.diffapp.validation.groups;

import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.validation.validators.ChainValidator;

/**
 * abstract Chain validation group
 *
 * @author HS
 */
public abstract class ChainValidationGroup {

    /**
     * first validator of the chain
     */
    ChainValidator chainStartValidator;

    /**
     * starts execution of the validation chain for {@link DiffEntity} and putting results into {@link DiffResult}
     *
     * @param diffEntity entity to be validated
     * @param diffResult object containing validation results
     * @return diffResult result object containing validation results
     */
    public DiffResult execute(DiffEntity diffEntity, DiffResult diffResult) {
        chainStartValidator.validate(diffEntity, diffResult);
        return diffResult;
    }
}
