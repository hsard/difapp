package com.waes.diffapp.validation.validators.impl;

import static com.waes.diffapp.constants.DiffStatus.INCOMPLETE_CONTENT;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.Test;

class ContentValidatorTest extends ValidatorTest<ContentValidator> {

    @Test
    void conditionToValidate() {
        ContentValidator contentValidator = spy(ContentValidator.class);

        validateAndExpect(contentValidator, "", "", INCOMPLETE_CONTENT);
        validateAndExpect(contentValidator, "sdfsdfds", "", INCOMPLETE_CONTENT);
        validateAndExpect(contentValidator, "", "sdfsfsdf", INCOMPLETE_CONTENT);
        validateAndExpect(contentValidator, "adsadasd", "sdfsfsdf", null);
    }

}