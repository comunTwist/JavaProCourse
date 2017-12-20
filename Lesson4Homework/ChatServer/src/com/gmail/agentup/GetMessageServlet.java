package com.gmail.agentup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetMessageServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();
    private ChatRooms chatRooms = ChatRooms.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fromStr = req.getParameter("from");
        String room = req.getParameter("room");
        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (room.equals("global")) {
            String json = msgList.toJSON(from);

            if (json != null) {
                OutputStream os = resp.getOutputStream();
                byte[] buf = json.getBytes(StandardCharsets.UTF_8);
                os.write(buf);
            }
        }
    }
}
