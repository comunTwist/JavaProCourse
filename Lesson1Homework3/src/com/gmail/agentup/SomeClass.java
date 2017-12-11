package com.gmail.agentup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
@interface Save{}
public class SomeClass {
    @Save int a = 0;
    @Save private String text = "Hello World";
    char b = 10;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
