package ru.geekbrains.java_three.lesson07;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        processing(TestClass.class);
    }

    public static void processing(Class cl) throws InvocationTargetException, IllegalAccessException {
        ArrayList<Method> methods = new ArrayList<>();
        for (Method method : cl.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                if (method.getAnnotation(Test.class).priority() < 1 || method.getAnnotation(Test.class).priority() > 10) {
                    throw new RuntimeException("Test priority incorrect!");
                }
                methods.add(method);
            }
        }

        methods.sort((o1, o2) -> -o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority());

        for (Method method : cl.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (methods.size() > 0 && methods.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException("Too many @BeforeSuite");
                }
                methods.add(0, method);
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (methods.size() > 0 && methods.get(methods.size() - 1).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("Too many @AfterSuite");
                }
                methods.add(method);
            }
        }

        for (int i = 0; i < methods.size(); i++) {
            methods.get(i).invoke(null);
        }
    }
}
