package ru.geekbrains.java_two.lesson1;

import java.awt.*;

public class BackGroundCanvas {

    private Color color = null;
    private float timeChangeBGColor = 0.0f;

    void onCanvasRepainted(GameCanvas canvas, float deltaTime) {
        render(canvas, deltaTime);
    }

    void render(GameCanvas canvas, float deltaTime) {
        timeChangeBGColor += deltaTime;
        if (timeChangeBGColor > 5.0f) {
            color = new Color(
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255)
            );
            canvas.setBackground(color);
            timeChangeBGColor = 0.0f;
        }
    }

}
