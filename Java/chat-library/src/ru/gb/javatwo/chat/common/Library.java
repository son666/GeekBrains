package ru.gb.javatwo.chat.common;

/*
 * Протокол обмена сообщениями между клиентом и сервером
 * */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Library {
    /*
     * /auth_request±login±password
     * /auth_accept±nickname
     * /auth_denied
     * /broadcast±msg
     *
     * /msg_format_error±msg
     * */

    public static final String DELIMITER = "±";
    public static final String AUTH_REQUEST = "/auth_request";
    public static final String AUTH_ACCEPT = "/auth_accept";
    public static final String AUTH_DENIED = "/auth_denied";
    public static final String MSG_FORMAT_ERROR = "/msg_format_error";
    // если мы вдруг не поняли, что за сообщение и не смогли разобрать
    public static final String TYPE_BROADCAST = "/bcast";
    // то есть сообщение, которое будет посылаться всем

    public static String getAuthRequest(String login, String password) {
        return AUTH_REQUEST + DELIMITER + login + DELIMITER + password;
    }

    public static String getAuthAccept(String nickname) {
        return getParseString(AUTH_ACCEPT + DELIMITER + nickname);
    }

    public static String getAuthDenied() {
        return getParseString(AUTH_DENIED);
    }

    public static String getMsgFormatError(String message) {
        return getParseString(MSG_FORMAT_ERROR + DELIMITER + message);
    }

    public static String getTypeBroadcast(String src, String message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return getParseString(TYPE_BROADCAST + DELIMITER + dtf.format(LocalDateTime.now()) +
                DELIMITER + src + DELIMITER + message);
    }

    private static String getParseString(String message) {
        String[] msg = message.split(DELIMITER);
        if (TYPE_BROADCAST.equals(msg[0])) {
            return msg[1] + " Message from " + msg[2] + ": " + msg[3];
        } else if (AUTH_ACCEPT.equals(msg[0])) {
            return "User " + msg[1] + " successfully login in server!";
        } else if (AUTH_DENIED.equals(msg[0])) {
            return "Faild login in server!";
        } else if (MSG_FORMAT_ERROR.equals(msg[0])) {
            return "Invalid format message: " + msg[1];
        }
        return null;
    }
}
