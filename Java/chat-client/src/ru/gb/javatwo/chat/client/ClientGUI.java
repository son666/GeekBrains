package ru.gb.javatwo.chat.client;

import ru.gb.javatwo.chat.common.Library;
import ru.gb.javatwo.network.SocketThread;
import ru.gb.javatwo.network.SocketThreadListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, SocketThreadListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top", true);
    private final JTextField tfLogin = new JTextField("Alex");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new GridLayout(2, 3));
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    //Change Login
    private final JLabel lbChangeNick = new JLabel("Новый Ник");
    private final JTextField tfNewNickName = new JTextField();
    private final JButton btnChangeNickName = new JButton("Change Nick");

    private final JList<String> userList = new JList<>();
    private SocketThread socketThread;
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss: ");
    private final String WINDOW_TITLE = "Chat";

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
        setTitle(WINDOW_TITLE);
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
        btnChangeNickName.addActionListener(this);
        btnDisconnect.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);

        panelBottom.add(btnDisconnect);
        panelBottom.add(tfMessage);
        panelBottom.add(btnSend);
        panelBottom.add(lbChangeNick);
        panelBottom.add(tfNewNickName);
        panelBottom.add(btnChangeNickName);

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
        } else if (obj == btnChangeNickName) {
            changeNickName();
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
            showException(Thread.currentThread(), e);
        }
    }

    private void changeNickName() {
        String newNickName = tfNewNickName.getText();
        String login = tfLogin.getText();
        String password = new String(tfPassword.getPassword());
        if ("".equals(newNickName)) return;
        socketThread.sendMessage(Library.getChangeLoginRequest(login, newNickName, password));
        tfNewNickName.setText(null);
    }

    private void disConnect() {
        socketThread.close();
    }

    private void showException(Thread t, Throwable e) {
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0)
            msg = "Empty Stacktrace";
        else {
            msg = String.format("Exception in \"%s\" %s: %s\n\tat %s",
                    t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
            JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        showException(t, e);
        System.exit(1);
    }

    private void sendToLogMessage() {
        String msg = tfMessage.getText();
        String user = tfLogin.getText();
        if ("".equals(msg)) return;
        tfMessage.setText(null);
        tfMessage.requestFocusInWindow();
        socketThread.sendMessage(Library.getTypeBcastClient(msg));
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
        setTitle(WINDOW_TITLE);
        userList.setListData(new String[0]);
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        reversVisiblePanel();
        putLog("Ready");
        String login = tfLogin.getText();
        String password = new String(tfPassword.getPassword());
        thread.sendMessage(Library.getAuthRequest(login, password));
    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String msg) {
        handleMessage(msg);
    }

    @Override
    public void onSocketException(SocketThread thread, Throwable throwable) {
        showException(thread, throwable);
    }

    private void handleMessage(String value) {
        String[] arr = value.split(Library.DELIMITER);
        String msgType = arr[0];
        switch (msgType) {
            case Library.AUTH_ACCEPT:
                setTitle(WINDOW_TITLE + " authorized with nickname " + arr[1]);
                break;
            case Library.AUTH_DENIED:
                putLog(value);
                break;
            case Library.MSG_FORMAT_ERROR:
                putLog(value);
                socketThread.close();
                break;
            case Library.TYPE_BROADCAST:
                putLog(DATE_FORMAT.format(Long.parseLong(arr[1])) +
                        arr[2] + ": " + arr[3]);
                break;
            case Library.USER_LIST:
                String users = value.substring(Library.USER_LIST.length() +
                        Library.DELIMITER.length());
                String[] userArr = users.split(Library.DELIMITER);
                Arrays.sort(userArr);
                userList.setListData(userArr);
                break;
            default:
                throw new RuntimeException("Unknown message type: " + value);
        }
    }
}