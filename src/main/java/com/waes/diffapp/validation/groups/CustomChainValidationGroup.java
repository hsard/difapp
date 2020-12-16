package com.waes.diffapp.validation.groups;

import com.waes.diffapp.validation.validators.impl.ContentValidator;
import com.waes.diffapp.validation.validators.impl.EncodingValidator;
import com.waes.diffapp.validation.validators.impl.MatchingValidator;
import com.waes.diffapp.validation.validators.impl.SizeValidator;

/**
 *  Custom implementation of {@link ChainValidationGroup}
 *  Defining the validation flow chain required by WAES
 *
 * @author HS
 */
public class CustomChainValidationGroup extends ChainValidationGroup {

    public CustomChainValidationGroup() {
        chainStartValidator = new ContentValidator();
        chainStartValidator
            .setNextValidator(new EncodingValidator())
            .setNextValidator(new SizeValidator())
            .setNextValidator(new MatchingValidator());
    }
}
