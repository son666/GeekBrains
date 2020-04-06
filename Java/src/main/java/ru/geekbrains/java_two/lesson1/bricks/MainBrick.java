package ru.geekbrains.java_two.lesson1.bricks;

import ru.geekbrains.java_two.lesson1.circles.BackGroundCanvas;
import ru.geekbrains.java_two.lesson1.common.GameCanvas;
import ru.geekbrains.java_two.lesson1.common.GameCanvasListener;
import ru.geekbrains.java_two.lesson1.common.GameObject;
import ru.geekbrains.java_two.lesson1.common.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MainBrick extends JFrame implements GameCanvasListener {
    BackGroundCanvas backGroundCanvas;

    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private int size;

    GameObject[] gameObjects = new GameObject[1];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainBrick();
            }
        });
    }

    private MainBrick() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    addElement(new Brick(e.getX(), e.getY()));
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    deleteElement();
                }
            }
        });
        add(canvas, BorderLayout.CENTER);
        setTitle("Bricks");
        initApplication();
        setVisible(true);
    }

    private void initApplication() {
        gameObjects[0] = new BackGroundCanvas();
            size++;
    }

    public void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < size; i++) {
            gameObjects[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < size; i++) {
            gameObjects[i].render(canvas, g);
        }
    }

    public void addElement(Sprite sprite) {
        if (size <= gameObjects.length) {
            gameObjects = Arrays.copyOf(gameObjects, gameObjects.length * 2);
            gameObjects[size] = sprite;
            size++;
        }
    }

    public void deleteElement() {
        if (size > 1) size--;
    }
}
