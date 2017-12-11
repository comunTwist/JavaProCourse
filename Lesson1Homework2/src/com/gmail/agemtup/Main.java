//Написать класс TextContainer, который содержит в себе строку. С помощью механизма аннотаций указать
//        1) в какой файл должен сохраниться текст 2) метод, который выполнит сохранение. Написать класс Saver,
//        который сохранит поле класса TextContainer в указанный файл.
//@SaveTo(path=“c:\\file.txt”)
//class Container {
//    String text = “...”;
//    @Saver
//    public void save(..) {...}
//}

package com.gmail.agemtup;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        TextConteiner tc = new TextConteiner();
        Class<?> cls = tc.getClass();
        String path = cls.getAnnotation(SaveTo.class).path();
        String text = "";

        try {
            text = (String) cls.getDeclaredField("text").get(tc);
        } catch (NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Save save = new Save();
        Class<?> clsSave = save.getClass();
        Method[] saver = clsSave.getDeclaredMethods();
        for (Method m: saver) {
            if(m.isAnnotationPresent(Saver.class)){
                System.out.println(m.getName());
                m.invoke(save,path,text);
                break;
            }
        }


        //Save.saveString(path,text);
    }
}
