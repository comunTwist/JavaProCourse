package com.gmail.agentup;


import java.util.HashMap;
import java.util.Map;

public class ChatRooms {
    private static final ChatRooms chatRooms = new ChatRooms();
    private Map<String, MessageList> rooms = new HashMap<>();

    public static ChatRooms getInstance() {
        return chatRooms;
    }

    public Map<String, MessageList> getRooms() {
        return rooms;
    }
}
