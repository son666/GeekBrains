package ru.gb.javatwo.chat.server.core;

import ru.gb.javatwo.chat.common.Library;
import ru.gb.javatwo.network.ServerSocketThread;
import ru.gb.javatwo.network.ServerSocketThreadListener;
import ru.gb.javatwo.network.SocketThread;
import ru.gb.javatwo.network.SocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {

    ServerSocketThread server;
    private Vector<SocketThread> vectorSocketClient = new Vector();
    private ChatServerListener listener;

    public ChatServer(ChatServerListener listener) {
        this.listener = listener;
    }

    public void start(int port) {
        if (server != null && server.isAlive())
            putLog("Already running");
        else
            server = new ServerSocketThread(this, "Server", port, 2000);
    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            System.out.println("Nothing to stop");
        } else {
            server.interrupt();
        }
    }

    private void putLog(String msg) {
        listener.onChatServerMessage(msg);
    }

    /**
     * Server Socket Thread Listener methods
     * */

    @Override
    public void onServerStarted(ServerSocketThread thread) {
        putLog("Server thread started");
        SqlClient.connect();
    }

    @Override
    public void onServerCreated(ServerSocketThread thread, ServerSocket server) {
        putLog("Server socket started");
    }

    @Override
    public void onServerTimeout(ServerSocketThread thread, ServerSocket server) {
        //putLog("Server timeout");
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket) {
        putLog("Client connected");
        String name = "SocketThread " + socket.getInetAddress() + ":" + socket.getPort();
        new ClientThread(this, name, socket);
    }

    @Override
    public void onServerException(ServerSocketThread thread, Throwable throwable) {
        putLog("Server exception");
        throwable.printStackTrace();
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server thread stopped");
        dropAllClients();
        SqlClient.disconnect();
    }


    /**
     * Socket Thread Listener methods
     * */

    @Override
    public synchronized void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Socket started");
    }

    @Override
    public synchronized void onSocketStop(SocketThread thread) {
        putLog("Socket stopped");
        ClientThread client = (ClientThread) thread;
        vectorSocketClient.remove(thread);
        if (client.isAuthorized() && !client.isReconnecting()) {
            sendMessageAllClient(Library.getTypeBroadcast("Server",
                    client.getNickname() + " disconnected"));
        }
        sendMessageAllClient(Library.getUserList(getUsers()));
    }

    @Override
    public synchronized void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Socket ready");
        vectorSocketClient.add(thread);
    }

    @Override
    public synchronized void onReceiveString(SocketThread thread, Socket socket, String msg) {
        ClientThread client = (ClientThread) thread;
        if (client.isAuthorized()) {
            handleAuthMessage(client, msg);
        } else
            handleNonAuthMessage(client, msg);
    }

    void handleAuthMessage(ClientThread client, String msg) {
        String[] arr = msg.split(Library.DELIMITER);
        String msgType = arr[0];
        switch (msgType) {
            case Library.TYPE_BCAST_CLIENT:
                sendMessageAllClient(Library.getTypeBroadcast(
                        client.getNickname(), arr[1]));
                break;
            case Library.CHANGE_LOGIN_REQUEST:
                String login = arr[1];
                String newNickName = arr[2];
                String password = arr[3];
                String oldNickName = SqlClient.getNickname(login, password);
                String newNick = SqlClient.changeNickname(login, newNickName, password);
                if (newNick == null) return;
                client.setNickName(newNick);
                sendMessageAllClient(Library.getTypeBroadcast("Server",  oldNickName + " change Nick to " + newNick));

                break;
            default:
                client.sendMessage(Library.getMsgFormatError(msg));
        }
    }

    void handleNonAuthMessage(ClientThread client, String msg) {
        String[] arr = msg.split(Library.DELIMITER);
        if (arr.length != 3 || !arr[0].equals(Library.AUTH_REQUEST)) {
            client.msgFormatError(msg);
            return;
        }
        String login = arr[1];
        String password = arr[2];
        String nickname = SqlClient.getNickname(login, password);
        if (nickname == null) {
            putLog("Invalid login attempt: " + login);
            client.authFail();
            return;
        } else {
            ClientThread oldClient = findClientByNickname(nickname);
            client.authAccept(nickname);
            if (oldClient == null) {
                sendMessageAllClient(Library.getTypeBroadcast("Server", nickname + " connected"));
            } else {
                oldClient.reconnect();
                vectorSocketClient.remove(oldClient);
            }
        }
        sendMessageAllClient(Library.getUserList(getUsers()));
    }

    @Override
    public void onSocketException(SocketThread thread, Throwable throwable) {
        throwable.printStackTrace();
    }

    public void sendMessageAllClient(String msg) {
        if (vectorSocketClient.size() != 0) {
            for (SocketThread socketClient : vectorSocketClient) {
                ClientThread client = (ClientThread) socketClient;
                if (!client.isAuthorized()) continue;
                client.sendMessage(msg);
            }
        }
    }

    public void dropAllClients() {
        for (SocketThread client : vectorSocketClient) {
            client.close();
        }
    }

    private synchronized String getUsers() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vectorSocketClient.size(); i++) {
            ClientThread client = (ClientThread) vectorSocketClient.get(i);
            if (!client.isAuthorized()) continue;
            sb.append(client.getNickname()).append(Library.DELIMITER);
        }
        return sb.toString();
    }

    private synchronized ClientThread findClientByNickname(String nickname) {
        for (int i = 0; i < vectorSocketClient.size(); i++) {
            ClientThread client = (ClientThread) vectorSocketClient.get(i);
            if (!client.isAuthorized()) continue;
            if (client.getNickname().equals(nickname))
                return client;
        }
        return null;
    }
}
