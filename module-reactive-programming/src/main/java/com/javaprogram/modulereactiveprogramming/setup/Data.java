package com.javaprogram.modulereactiveprogramming.setup;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Data {
    @JsonProperty("data")
    private List<ResponseDto> data;
}
