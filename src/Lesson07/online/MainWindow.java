package Lesson07.online;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 07.04.2021
 */

public class MainWindow extends JFrame {

    private final int WIN_WIDTH = 500;
    private final int WIN_HEIGHT = 555;
    private final int WIN_POS_X = 600;
    private final int WIN_POS_Y = 250;

    private Settings settingsWindow;
    private GameMap gameMap;

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocation(WIN_POS_X, WIN_POS_Y);
        setTitle("Our Game");
        setResizable(false);

        settingsWindow = new Settings(this);
        gameMap = new GameMap();

        JButton btnStartGame = new JButton("Start New Game");
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);
            }
        });

        JButton btnExitGame = new JButton("Exit Game");
        btnExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel containerForButton = new JPanel();
        containerForButton.setLayout(new GridLayout(1, 2));
        containerForButton.add(btnStartGame);
        containerForButton.add(btnExitGame);

        add(containerForButton, BorderLayout.SOUTH);

        add(gameMap);

        setVisible(true);
    }

    void startGameWithUserSetting(int modeGame, int mapSizeX, int mapSizeY, int winLength) {
        gameMap.startGame(modeGame, mapSizeX, mapSizeY, winLength);
    }
}

/*
1. Полностью разобраться с кодом
(попробовать полностью самостоятельно переписать одно из окон)
2. Составить список вопросов и приложить в виде комментария к домашней работе
3. *Расчертить панель GameMap на поле для игры, согласно mapSizeX,Y

*/
