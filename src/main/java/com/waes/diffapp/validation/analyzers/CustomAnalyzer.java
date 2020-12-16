package com.waes.diffapp.validation.analyzers;

import com.waes.diffapp.model.DiffResult;
import com.waes.diffapp.model.DiffEntity;
import org.apache.commons.lang3.StringUtils;

/**
 * Custom implementation of analyzer
 *
 * @author HS
 */
public class CustomAnalyzer implements Analyzer {

    @Override
    public void analyze(DiffEntity diffEntity, DiffResult diffResult) {
        findDifferences(diffEntity.getLeftSide(), diffEntity.getRightSide(), 0, diffResult);
    }

    /**
     * Recursively compares two strings and states indexes&offsets of different portions
     *
     * @param leftString  left string
     * @param rightString right string
     * @param offset      index to start analyzes from
     * @param diffResult  Diff Result object which will store recursively added diff records
     */
    private void findDifferences(String leftString, String rightString, int offset, DiffResult diffResult) {
        if (offset < leftString.length()) {
            int startIndex = findFirstIndexForMatchingCriteria(leftString, rightString, offset, false);

            if (startIndex != StringUtils.INDEX_NOT_FOUND) {
                int endIndex = findFirstIndexForMatchingCriteria(leftString, rightString, startIndex + 1, true);

                diffResult.addDiff(startIndex, (endIndex != StringUtils.INDEX_NOT_FOUND ? endIndex : leftString.length()) - startIndex);

                if (endIndex != StringUtils.INDEX_NOT_FOUND) {
                    findDifferences(leftString, rightString, endIndex, diffResult);
                }
            }
        }
    }

    /**
     * Iterating through the same length two strings starting at offset
     * and finding first matching/non-matching characters pair in them
     *
     * @param leftString  left string
     * @param rightString right string
     * @param offset      index to start analyzes from
     * @param shouldMatch true: requires to return index of first matching chars pair,
     *                    false: requires to return index of first non-matching chars pair
     * @return index of first matching/non-matching pair satisfying @shouldMatch criteria or -1 if no any found
     */
    private int findFirstIndexForMatchingCriteria(String leftString, String rightString, int offset, boolean shouldMatch) {
        int i;
        for (i = offset; i < leftString.length() && i < rightString.length(); ++i) {
            if (shouldMatch == (leftString.charAt(i) == rightString.charAt(i))) {
                break;
            }
        }

        if (i < rightString.length() || i < leftString.length()) {
            return i;
        }

        return StringUtils.INDEX_NOT_FOUND;
    }
}
