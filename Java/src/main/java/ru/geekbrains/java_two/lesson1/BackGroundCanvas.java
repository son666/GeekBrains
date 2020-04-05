package ru.geekbrains.java_two.lesson1;

import java.awt.*;

public class BackGroundCanvas {

    private Color color = null;
    private float timeSec = 0.0f;
    private float timeSecChangeBG = 5.0f;

    void onCanvasRepainted(GameCanvas canvas, float deltaTime) {
        render(canvas, deltaTime);
    }

    void render(GameCanvas canvas, float deltaTime) {
        timeSec += deltaTime;
        if (timeSec > timeSecChangeBG) {
            color = new Color(
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255)
            );
            canvas.setBackground(color);
            timeSec = 0.0f;
        }
    }

}
