package com.gmail.agentup;
import java.io.*;
import java.util.zip.*;
public class Main {
    public static void main(String[] args) {
        String filename = "task.txt";
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("output.zip"));
            FileInputStream fis= new FileInputStream(filename);)
        {
            ZipEntry entry1=new ZipEntry(filename);
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }
}
