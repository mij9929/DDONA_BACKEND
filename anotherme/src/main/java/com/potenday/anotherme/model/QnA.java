package com.potenday.anotherme.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QnA {
    String quenstion;
    String[] answers;

    public QnA(String quenstion, String[] answers) {
        this.quenstion = quenstion;
        this.answers = answers;
    }
}
