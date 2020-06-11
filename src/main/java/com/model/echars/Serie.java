package com.model.echars;

import lombok.Data;

import java.io.Serializable;

@Data
public class Serie implements Serializable {
    String name;
    String type;
    Integer [] data;
    public Serie(String name, String type, Integer[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

}