package com.gmail.agemtup;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface SaveTo {
    String path();
}

@SaveTo(path="saver.txt")
public class TextConteiner {
    String text = "Hello World";
}

