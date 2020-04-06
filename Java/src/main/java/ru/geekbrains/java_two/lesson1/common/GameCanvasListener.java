package ru.geekbrains.java_two.lesson1.common;

import java.awt.*;

public interface GameCanvasListener {

    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime);
}
