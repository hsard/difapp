package com.waes.diffapp.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.waes.diffapp.util.MessageSourceUtil.getLocalizedMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.waes.diffapp.constants.DiffStatus;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

/**
 * Object to store diff analyzes results
 *
 * @author HS
 */
@Data
public class DiffResult {

    /**
     * Fields stores final status of the analyzes and localized description message
     */
    private DiffStatus resultCode;
    private String resultDescription;

    /**
     * List containing diff entries if applicable
     */
    @JsonInclude(NON_NULL)
    private List<DiffEntry> diffs;

    public void setResultCode(DiffStatus resultCode) {
        this.resultCode = resultCode;
        this.resultDescription = getLocalizedResultDescription(resultCode);
    }

    public void addDiff(int startIndex, int offset) {
        if (diffs == null) {
            diffs = new ArrayList<>();
        }
        diffs.add(new DiffEntry(startIndex, offset));
    }

    @Data
    public static class DiffEntry {
        private int start;
        private int offset;

        public DiffEntry(int start, int offset) {
            this.start = start;
            this.offset = offset;
        }
    }

    public static String getLocalizedResultDescription(DiffStatus diffStatus) {
        return Optional.ofNullable(diffStatus)
            .map(ds -> getLocalizedMessage("diff.result." + ds.getMessagePropertyKey()))
            .orElse(Strings.EMPTY);
    }
}
