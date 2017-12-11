//Написать код, который сериализирует и десериализирует в/из файла все значения полей класса, которые
//отмечены аннотацией @Save

package com.gmail.agentup;

import java.io.*;
import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        SomeClass smc = new SomeClass();
        Class<?> cls = smc.getClass();
        Field [] fields = cls.getDeclaredFields();
        StringBuilder sb = new StringBuilder();

        for (Field field : fields) {
            if(field.isAnnotationPresent(Save.class)) {
                if(Modifier.isPrivate(field.getModifiers())){
                    field.setAccessible(true);
                }
                sb.append(field.get(smc)).append(System.lineSeparator());
            }
        }

        File saver = new File("saver.txt");
        saveValue(sb,saver);
        for (String param : readValue(saver).split(System.lineSeparator())) {
            System.out.println(param);
        }

    }

    public static void saveValue(StringBuilder string, File file) {
        try (ObjectOutput oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(string);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String readValue(File file) {
        try (ObjectInput ois = new ObjectInputStream(new FileInputStream(file))) {
            StringBuilder sb = new StringBuilder();
            sb = (StringBuilder) ois.readObject();
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
