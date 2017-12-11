package com.gmail.agemtup;

import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Saver {}

public class Save {

    @Saver
    public static void saveString (String path, String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(text);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

}
