package com.waes.diffapp.validation.analyzers;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

import java.util.List;

import com.waes.diffapp.constants.DiffSide;
import com.waes.diffapp.model.DiffEntity;
import com.waes.diffapp.model.DiffResult;
import org.junit.jupiter.api.Test;

class CustomAnalyzerTest {

    @Test
    void analyze() {
        Analyzer analyzer = spy(CustomAnalyzer.class);

        analyzeAndExpect(analyzer, "asdasdasd", "asdasdasd", null);
        analyzeAndExpect(analyzer, "asdasdasd", "asdasdasda", asList(new DiffResult.DiffEntry(9, 0)));
        analyzeAndExpect(analyzer, "asdasdasd", "asdasqwsd", asList(new DiffResult.DiffEntry(5, 2)));
        analyzeAndExpect(analyzer, "asdasdasdasd", "asdasqwsdqwe", asList(new DiffResult.DiffEntry(5, 2), new DiffResult.DiffEntry(9, 3)));
    }

    public void analyzeAndExpect(Analyzer analyzer, String leftContent, String rightContent, List<DiffResult.DiffEntry> diffEntryList) {
        DiffEntity diffEntity = new DiffEntity();
        diffEntity.setSideContent(DiffSide.LEFT, leftContent);
        diffEntity.setSideContent(DiffSide.RIGHT, rightContent);

        DiffResult diffResult = new DiffResult();
        analyzer.analyze(diffEntity, diffResult);

        assertEquals(diffEntryList, diffResult.getDiffs());
    }

}