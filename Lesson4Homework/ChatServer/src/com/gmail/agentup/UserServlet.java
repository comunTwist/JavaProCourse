package com.gmail.agentup;

import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserServlet extends HttpServlet {

    private UserList userList = UserList.getInstance();
    private User user = new User();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        user = User.fromJSON(bufStr);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        user.setLogin(req.getParameter("name"));
        user.setPass(req.getParameter("pass"));
        user.setStatus(req.getParameter("status"));
        String response = "";
        if (!user.getLogin().equals("") && !user.getPass().equals("")) {
            boolean userExist = true;
            for (int i = 0; i < userList.getList().size(); i++) {
                if (userList.getList().get(i).getLogin().equals(user.getLogin())) {
                    if (userList.getList().get(i).getPass().equals(user.getPass())) {
                        userList.getList().get(i).setStatus("online");
                        user = userList.getList().get(i);
                        response = "Hello " + user.getLogin() + " (" + user.getStatus() + ")";
                        System.out.println(response);

                    } else {
                        response = "Password incorrect";
                    }
                    userExist = false;
                    break;
                }
            }
            if (userExist) {
                response = "Hello " + user.getLogin() + " (" + user.getStatus() + ")";
                userList.add(user);
            }
        } else {
            response = "Bad request";
        }
        OutputStream os = resp.getOutputStream();
        byte[] buf = response.getBytes(StandardCharsets.UTF_8);
        os.write(buf);
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
