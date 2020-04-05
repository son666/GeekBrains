package ru.geekbrains.java_two.lesson1;

import javax.swing.*;
import java.awt.*;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int START_ELEMENT = 10;
    private static final int ALL_ELEMENT = 100;
    private int size;

    Sprite[] sprites = new Sprite[ALL_ELEMENT];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this, new BackGroundCanvas());
        add(canvas, BorderLayout.CENTER);
        setTitle("Circles");
        initApplication();
        setVisible(true);
    }

    private void initApplication() {
        for (int i = 0; i < START_ELEMENT; i++) {
            sprites[i] = new Ball();
            size++;
        }
    }

    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < size; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < size; i++) {
            sprites[i].render(canvas, g);
        }
    }

    public void addElement(Sprite sprite) {
        if (size < sprites.length) {
            sprites[size] = sprite;
            size++;
        }
    }

    public void deleteElement() {
        if (size > 0) {
            sprites[size - 1] = null;
            size--;
        }
    }
}
