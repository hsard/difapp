package com.waes.diffapp.validation.groups;

import static org.junit.Assert.assertEquals;

import com.waes.diffapp.constants.DiffSide;
import com.waes.diffapp.constants.DiffStatus;
import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.model.DiffResult;

public abstract class ChainValidationGroupTest<T extends ChainValidationGroup> {

    public void validateChainAndExpect(T chainValidationGroup, String leftContent, String rightContent, DiffStatus expectedStatus) {
        DiffEntity diffEntity = new DiffEntity();
        diffEntity.setSideContent(DiffSide.LEFT, leftContent);
        diffEntity.setSideContent(DiffSide.RIGHT, rightContent);
        DiffResult diffResult = new DiffResult();
        chainValidationGroup.execute(diffEntity, diffResult);
        assertEquals(expectedStatus, diffResult.getResultCode());
    }
}
