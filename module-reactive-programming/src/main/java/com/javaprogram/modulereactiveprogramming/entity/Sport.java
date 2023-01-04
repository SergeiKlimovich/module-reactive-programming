package com.javaprogram.modulereactiveprogramming.entity;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Sport {
    @Id
    private int id;
    private String name;

    public Sport(String name) {
        this.name = name;
    }
}
