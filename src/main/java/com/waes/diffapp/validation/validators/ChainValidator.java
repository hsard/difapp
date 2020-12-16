package com.waes.diffapp.validation.validators;

import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.model.DiffEntity;

/**
 * Processor for validating content of diffEntity and putting diff results into diffResult
 * Designed for use in scope of design pattern "Chain of Responsibility", so assumes setup of the next validator in chain
 *
 * @author HS
 */
public interface ChainValidator {

    void validate(DiffEntity diffEntity, DiffResult diffResult);

    /**
     * Method sets next validator to be used in validation chain
     * @return next validator
     */
    ChainValidator setNextValidator(ChainValidator nextValidator);

    /**
     * @return next validator to be used in validation chain
     */
    ChainValidator getNextValidator();
}
