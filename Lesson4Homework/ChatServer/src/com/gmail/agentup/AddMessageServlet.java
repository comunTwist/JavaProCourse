package com.gmail.agentup;

import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class AddMessageServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();
    private UserList userList = UserList.getInstance();
    private ChatRooms chatRooms = ChatRooms.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message message = Message.fromJSON(bufStr);
        if (message != null) {
            if (message.getText().equals("@getusers")) {
                getUsers();
            } else if (message.getText().equals("@logout")) {
                logout(message);
            } else if (message.getText().equals("@createroom")) {
                chatRooms.getRooms().put("room 1", MessageList.getInstance());
            } else {
                msgList.add(message);
            }

        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }

    private void getUsers() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (int i = 0; i < userList.getList().size(); i++) {
            sb.append(userList.getList().get(i).getLogin()).append(" (").append(userList.getList().get(i).getStatus()).append(")").append(System.lineSeparator());
        }
        msgList.add(new Message("System Bot", sb.toString()));
    }

    private void logout(Message message) {
        for (int i = 0; i < userList.getList().size(); i++) {
            if (message.getFrom().equals(userList.getList().get(i).getLogin())) {
                userList.getList().get(i).setStatus("offline");
                msgList.add(new Message(userList.getList().get(i).getLogin(), "@logout"));
            }
        }
    }
}
