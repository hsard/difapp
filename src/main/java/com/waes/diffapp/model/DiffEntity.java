package com.waes.diffapp.model;

import static com.waes.diffapp.constants.DiffSide.LEFT;
import static com.waes.diffapp.constants.DiffSide.RIGHT;

import com.waes.diffapp.constants.DiffSide;
import lombok.Getter;

@Getter
public class DiffEntity extends ExpirableBean {
    private String leftSide;
    private String rightSide;

    public void setSideContent(DiffSide diffSide, String content) {
        if (LEFT.equals(diffSide)) {
            leftSide = content;
        } else if (RIGHT.equals(diffSide)) {
            rightSide = content;
        }
    }
}
