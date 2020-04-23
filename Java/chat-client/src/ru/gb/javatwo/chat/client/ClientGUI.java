package ru.gb.javatwo.chat.client;

import ru.gb.javatwo.network.SocketThread;
import ru.gb.javatwo.network.SocketThreadListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, SocketThreadListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top", true);
    private final JCheckBox cbSendMessageAll = new JCheckBox("Send all", false);
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new GridLayout(1, 4));
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private final JButton btnSendAll = new JButton("SendAll");

    private final JList<String> userList = new JList<>();
    private SocketThread socketThread;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setAlwaysOnTop(true);
        userList.setListData(new String[]{"user1", "user2", "user3", "user4",
                "user5", "user6", "user7", "user8", "user9",
                "user-with-exceptionally-long-name-in-this-chat"});
        JScrollPane scrUser = new JScrollPane(userList);
        JScrollPane scrLog = new JScrollPane(log);
        scrUser.setPreferredSize(new Dimension(100, 0));
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addActionListener(this);
        btnLogin.addActionListener(this);
        btnDisconnect.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect);
        panelBottom.add(tfMessage);
        panelBottom.add(cbSendMessageAll);
        panelBottom.add(btnSend);
        add(scrUser, BorderLayout.EAST);
        add(scrLog, BorderLayout.CENTER);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);
        panelBottom.setVisible(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (obj == btnSend || obj == tfMessage) {
            sendToLogMessage();

        } else if (obj == btnLogin) {
            connect();
        } else if (obj == btnDisconnect) {
            disConnect();
        } else {
            throw new RuntimeException("Unknown source:" + obj);
        }
    }

    private void connect() {
        try {
            Socket socket = new Socket(tfIPAddress.getText(), Integer.parseInt(tfPort.getText()));
            socketThread = new SocketThread(this, tfLogin.getText(), socket);
        } catch (IOException e) {
            uncaughtException(Thread.currentThread(), e);
        }
    }

    private void disConnect() {
        socketThread.interrupt();
        socketThread.sendMessage(tfLogin.getText() + " exit from chat!");
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0) {
            msg = "Empty Stacktrace";
        } else {
            msg = String.format("Exception in thread \"%s\" %s: %s\n\tat %s",
                    t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        }
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    private void sendToLogMessage() {
        String msg = tfMessage.getText();
        String user = tfLogin.getText();
        if ("".equals(msg)) return;
        if (cbSendMessageAll.isSelected()) {
            msg = "Message all: " + msg;
        }
        tfMessage.setText(null);
        tfMessage.requestFocusInWindow();
        socketThread.sendMessage(String.format("%s: %s", user, msg));
        saveToFile(msg, user);
    }

    private void putLog(String message) {
        if ("".equals(message)) return;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(message + System.lineSeparator());
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }

    private void saveToFile(String msg, String user) {
        try (FileWriter fileWriter = new FileWriter("E:\\logChat.txt", true)) {
            fileWriter.write(user + " : " + msg + System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            uncaughtException(Thread.currentThread(), e);
        }
    }

    private void reversVisiblePanel() {
        if (panelTop.isVisible()) {
            panelTop.setVisible(false);
            panelBottom.setVisible(true);
        } else {
            panelTop.setVisible(true);
            panelBottom.setVisible(false);
        }
    }

    /**
     * Socket Thread Listener methods
     */

    @Override
    public void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Start");
    }

    @Override
    public void onSocketStop(SocketThread thread) {
        reversVisiblePanel();
        putLog("Stop");
        saveToFile("Exit from chat", tfLogin.getText());
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        reversVisiblePanel();
        putLog("Ready");
        saveToFile("Connection to chat", tfLogin.getText());
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String msg) {
        putLog(msg);
    }

    @Override
    public void onSocketException(SocketThread thread, Throwable throwable) {
        reversVisiblePanel();
        uncaughtException(thread, throwable);
        saveToFile(String.format("Exception in thread \"%s\" %s: %s\n\tat %s",
                thread.getName(), throwable.getClass().getCanonicalName(), throwable.getMessage(), throwable.getStackTrace()[0]),
                tfLogin.getText());
    }
}