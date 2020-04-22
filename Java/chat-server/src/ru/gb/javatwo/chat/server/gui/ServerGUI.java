package ru.gb.javatwo.chat.server.gui;

import ru.gb.javatwo.chat.server.core.ChatServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private static final int POS_X = 1000;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;

    private final ChatServer server;
    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(1, 2));
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("SendAllClient");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI();
            }
        });
    }

    ServerGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addActionListener(this);
        JScrollPane scrLog = new JScrollPane(log);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        panelTop.add(btnStart);
        panelTop.add(btnStop);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(scrLog, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        server = new ChatServer();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnStart) {
            server.start(8189);
        } else if (source == btnStop) {
            server.stop();
        } else if (source == btnSend || source == tfMessage) {
            sendMessageAllClients();
        } else {
            throw new RuntimeException("Unknown source:" + source);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg = String.format("Exception in thread \"%s\" %s: %s\n\tat %s", t.getName(), e.getClass().getCanonicalName(), e.getMessage(), e.getStackTrace()[0]);
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    private void sendMessageAllClients() {
        String msg = tfMessage.getText();
        if ("".equals(msg)) return;
        tfMessage.setText(null);
        tfMessage.requestFocusInWindow();
        if (server.sendMessageAllClient("Message from Server: " + msg)) {
            putLog("Send all clients: " + msg);
        } else {
            putLog("No connect clients!");
        }

    }

    private void putLog(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(message + System.lineSeparator());
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
