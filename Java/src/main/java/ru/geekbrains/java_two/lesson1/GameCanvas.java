package ru.geekbrains.java_two.lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameCanvas extends JPanel {

    MainCircles listener;
    BackGroundCanvas backGroundCanvas;
    long lastFrameTime;

    GameCanvas(MainCircles listener, BackGroundCanvas backGroundCanvas) {
        this.listener = listener;
        this.backGroundCanvas = backGroundCanvas;
        lastFrameTime = System.nanoTime();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    listener.addElement(new Ball());
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    listener.deleteElement();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //60 frames per second
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        listener.onCanvasRepainted(this, g, deltaTime);
        backGroundCanvas.onCanvasRepainted(this, deltaTime);
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public int getLeft() {
        return 0;
    }

    public int getRight() {
        return getWidth() - 1;
    }

    public int getTop() {
        return 0;
    }

    public int getBottom() {
        return getHeight() - 1;
    }

}
