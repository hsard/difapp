package com.waes.diffapp.validation.groups;

import static com.waes.diffapp.constants.DiffStatus.INCOMPLETE_CONTENT;
import static com.waes.diffapp.constants.DiffStatus.MATCHING_CONTENT;
import static com.waes.diffapp.constants.DiffStatus.MISMATCHING_CONTENT;
import static com.waes.diffapp.constants.DiffStatus.MISMATCHING_SIZES;
import static com.waes.diffapp.constants.DiffStatus.NOT_BASE64_CONTENT;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.Test;

class CustomChainValidationGroupTest extends ChainValidationGroupTest<CustomChainValidationGroup> {

    @Test
    void execute() {
        CustomChainValidationGroup customChainValidationGroup = spy(CustomChainValidationGroup.class);

        validateChainAndExpect(customChainValidationGroup, "", "", INCOMPLETE_CONTENT); // ensures blank validation
        validateChainAndExpect(customChainValidationGroup, "asdasdasd", "qweqweqwe", MISMATCHING_CONTENT); // ensures mismatching validation
        validateChainAndExpect(customChainValidationGroup, "asdasdasd", "qweqweqweq", MISMATCHING_SIZES); // ensures mismatching sizes validation
        validateChainAndExpect(customChainValidationGroup, "asdasdasd", "asdasdasd", MATCHING_CONTENT); // ensures matching content validation
        validateChainAndExpect(customChainValidationGroup, "sd.fsdfds", "sdfsdfds", NOT_BASE64_CONTENT); // ensures wrong encoded content validation
    }
}