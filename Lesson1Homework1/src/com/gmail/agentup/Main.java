//Создать аннотацию, которая принимает параметры для метода. Написать код, который вызовет метод,
//помеченный этой аннотацией, и передаст параметры аннотации в вызываемый метод.
//@Test(a=2, b=5)
//public void test(int a, int b) {...}

package com.gmail.agentup;

import java.lang.annotation.*;
import java.lang.reflect.*;

@Inherited
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Test {
    int a() default 1;
    int b();
}


class TestClass{
    @Test(a = 2, b = 5)
    public static void testMethod(int a, int b){
        System.out.println(a +" "+ b);
    }
}

public class Main {

    public static void main(String[] args) {

        final Class<?> cls = TestClass.class;
        Method testMethod = cls.getMethods()[0];
        //System.out.println(testMethod.isAnnotationPresent(Test.class));
        Test myAnotation = testMethod.getAnnotation(Test.class);

        TestClass.testMethod(8,12);
        TestClass.testMethod(myAnotation.a(),myAnotation.b());
    }

}
