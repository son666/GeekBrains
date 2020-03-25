package ru.geekbrains.java_one.lesson7;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;

    FieldPanel() {
        setBackground(Color.LIGHT_GRAY);
    }

    public void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        System.out.printf("damn mode: %d\nfield size: %d,\nwin length: %d\n", gameMode, fieldSizeX, winLength);
        JTextField[][] arrayTextField = new JTextField[fieldSizeX][fieldSizeY];
        setLayout(new GridLayout(fieldSizeX, fieldSizeY));
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        for (int i = 0; i < arrayTextField.length; i++) {
            for (int j = 0; j < arrayTextField[i].length; j++) {
                arrayTextField[i][j] = new JTextField();
                arrayTextField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                add(arrayTextField[i][j]);
            }
        }
    }
}
