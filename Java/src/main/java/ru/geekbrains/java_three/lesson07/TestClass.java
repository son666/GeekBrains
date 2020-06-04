package ru.geekbrains.java_three.lesson07;

public class TestClass {

    @Test(priority = 1)
    public static void method1() {
        System.out.println("M1");
    }

    @BeforeSuite
    public static void method2() {
        System.out.println("@BeforeSuite M2");
    }

    @AfterSuite
    public static void method6() {
        System.out.println("@AfterSuite M6");
    }

    @Test(priority = 10)
    public static void method3() {
        System.out.println("M3");
    }

    @Test(priority = 7)
    public static void method4() {
        System.out.println("M4");
    }

    public static void method5() {
        System.out.println("M5");
    }


}
