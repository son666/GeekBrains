package ru.geekbrains.java_two.lesson1.circles;

import ru.geekbrains.java_two.lesson1.common.GameCanvas;
import ru.geekbrains.java_two.lesson1.common.GameObject;

import java.awt.*;

public class BackGroundCanvas implements GameObject {

    private Color color = null;
    private float timeSec = 0.0f;
    private float timeSecChangeBG = 5.0f;

    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    @Override
    public void update(GameCanvas canvas, float deltaTime) {
        timeSec += deltaTime;
        if (timeSec > timeSecChangeBG) {
            color = new Color(
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255)
            );
            timeSec = 0.0f;
        }
    }

    @Override
    public void render(GameCanvas canvas, Graphics g) {
            canvas.setBackground(color);
    }

}
