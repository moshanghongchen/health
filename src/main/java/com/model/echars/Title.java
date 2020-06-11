package com.model.echars;

import lombok.Data;

import java.io.Serializable;

@Data
public class Title implements Serializable {
    private String text="ECharts 入门示例";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}