package com.waes.diffapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiffRequestDto {

    @NotNull
    @NotBlank
    private String content;
}
