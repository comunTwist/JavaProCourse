package com.gmail.agentup;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class GetRoomsServlet extends HttpServlet {
    private ChatRooms chatRooms = ChatRooms.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        Set<String> rooms = chatRooms.getRooms().keySet();
        StringBuilder sb = new StringBuilder();
        for (String str : rooms) {
            sb.append(str).append(", ");
        }
        if (sb.toString() != null) {
            sb.append("wooo");
            OutputStream os = resp.getOutputStream();
            byte[] buf = sb.toString().getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }

    }
}
