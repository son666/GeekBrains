package ru.geekbrains.java_one.lesson5.abstractclass;

/*
1. Создать классы Собака, Лошадь, Птица и Кот с наследованием от класса Животное.
2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина,
означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,; прыжок: кот 2 м.,
собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).
4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат.
(Например, dog1.run(150); -> результат: 'Пёсик пробежал!')
5. Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м
*/

public abstract class Animal {
    public static final int SWIM_FAIL = 0;
    public static final int SWIM_OK = 1;
    public static final int SWIM_WTF = -1;

    private String type;
    private String name;
    private int distanceRun;
    private int distanceSail;
    private double heightJump;

    public Animal(String type, String name, int distanceRun, int distanceSail, double heightJump) {
        this.type = type;
        this.name = name;
        this.distanceRun = distanceRun;
        this.distanceSail = distanceSail;
        this.heightJump = heightJump;
    }

    public boolean run(int distanceRun) {
        return this.distanceRun >= distanceRun;
    }

    public int sail(int distanceSail) {
        return (this.distanceSail >= distanceSail) ? SWIM_OK : SWIM_FAIL;
    }

    public boolean jump(double heightJump) {
        return this.heightJump >= heightJump;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", " +
                "Run max: " + distanceRun + ", " +
                "Jump max: " + heightJump + ", " +
                "Sil max: " + distanceSail;
    }
}
