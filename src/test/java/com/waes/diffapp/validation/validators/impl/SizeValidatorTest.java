package com.waes.diffapp.validation.validators.impl;

import static com.waes.diffapp.constants.DiffStatus.MISMATCHING_SIZES;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.Test;

class SizeValidatorTest extends ValidatorTest<SizeValidator> {
    @Test
    void conditionToValidate() {
        SizeValidator sizeValidator = spy(SizeValidator.class);

        validateAndExpect(sizeValidator, "sdfsdfds", "", MISMATCHING_SIZES);
        validateAndExpect(sizeValidator, "", "sdfsfsdf", MISMATCHING_SIZES);
        validateAndExpect(sizeValidator, "asdasdasd", "qweqweqweq", MISMATCHING_SIZES);
        validateAndExpect(sizeValidator, "asdasdasdq", "qweqweqwe", MISMATCHING_SIZES);
        validateAndExpect(sizeValidator, "", "", null);
        validateAndExpect(sizeValidator, "asdasdasd", "qweqweqwe", null);
    }
}