package com.waes.diffapp.constants;

/**
 * Enum keys of possible Diff Result Statuses
 * <p>
 *
 * @author HS
 */
public enum DiffStatus {
    NOT_BASE64_CONTENT("wrongEncoding"),
    INCOMPLETE_CONTENT("incomplete"),
    MISMATCHING_SIZES("mismatchingSizes"),
    MATCHING_CONTENT("matching"),
    MISMATCHING_CONTENT("mismatching");

    /**
     * Used for dynamic resolving of the description messages for the statuses based on locale
     */
    String messagePropertyKey;

    DiffStatus(String messagePropertyKey) {
        this.messagePropertyKey = messagePropertyKey;
    }

    public String getMessagePropertyKey() {
        return messagePropertyKey;
    }
}
