package com.waes.diffapp.validation.validators.impl;

import static com.waes.diffapp.constants.DiffStatus.NOT_BASE64_CONTENT;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.Test;

class EncodingValidatorTest extends ValidatorTest<EncodingValidator> {

    @Test
    void conditionToValidate() {
        EncodingValidator encodingValidator = spy(EncodingValidator.class);

        validateAndExpect(encodingValidator, "sd.fsdfds", "sdfsdfds", NOT_BASE64_CONTENT);
        validateAndExpect(encodingValidator, "sdfsdfds", "sdfs.dfds", NOT_BASE64_CONTENT);
        validateAndExpect(encodingValidator, "a.sdasdasd", "qweqweqwe!", NOT_BASE64_CONTENT);
        validateAndExpect(encodingValidator, "", "", null);
        validateAndExpect(encodingValidator, "asdasdasd", "qweqweqwe", null);
        validateAndExpect(encodingValidator, "asdasdasd", "qweqweqweq", null);
    }
}