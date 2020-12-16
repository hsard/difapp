package com.waes.diffapp.util;

import com.waes.diffapp.constants.DiffSide;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convertor to map lowercase (left,right) strings to DiffSide enums
 * Mainly handles strings from url path
 *
 * @author HS
 */
@Component
public class DiffSideEnumConvertor implements Converter<String, DiffSide> {

    @Override
    public DiffSide convert(String value) {
        return DiffSide.valueOf(value.toUpperCase());
    }
}