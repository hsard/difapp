package com.waes.diffapp.validation.validators.impl;

import static com.waes.diffapp.constants.DiffStatus.MATCHING_CONTENT;
import static com.waes.diffapp.constants.DiffStatus.MISMATCHING_CONTENT;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.Test;

class MatchingValidatorTest extends ValidatorTest<MatchingValidator>{
    @Test
    void conditionToValidate() {
        MatchingValidator matchingValidator = spy(MatchingValidator.class);

        validateAndExpect(matchingValidator, "", "", MATCHING_CONTENT);
        validateAndExpect(matchingValidator, "asdasdasd", "qweqweqwe", MISMATCHING_CONTENT);
        validateAndExpect(matchingValidator, "asdasdasd", "asdasdasd", MATCHING_CONTENT);
        validateAndExpect(matchingValidator, "asdasdasd", "qweqweqweq", MISMATCHING_CONTENT);
        validateAndExpect(matchingValidator, "sd.fsdfds", "sdfsdfds", MISMATCHING_CONTENT);
    }
}