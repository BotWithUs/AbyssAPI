package abyss.plugin.api.params;

import abyss.plugin.api.AsyncData;
import abyss.plugin.api.Cache;
import abyss.plugin.api.Param;
import abyss.plugin.api.game.chat.ChatMessage;
import abyss.plugin.api.game.chat.GameChat;

import java.util.*;

public class Struct extends AsyncData {

    private final int id;
    private final Map<Integer, Param> params;

    public Struct(int id) {
        super();
        this.id = id;
        this.params = new HashMap<>();
    }

    public void initialize() {
    }

    public final int getId() {
        return this.id;
    }

    public final String readString(int key) {
        Param result = params.get(key);
        if (result == null)
            return "";
        return result.getStringValue();
    }

    public int readInt(int key) {
        Param result = params.get(key);
        if (result == null)
            return -1;
        return result.getIntValue();
    }

    public final Struct readStruct(int key) {
        Param param = params.get(id);
        if (param == null)
            return null;
        int id = param.getIntValue();
        if (id == -1)
            return null;
        return Cache.getStruct(id, false);
    }

    public Map<Integer, Param> getParams() {
        return params;
    }

    final void define(int id, Param param) {
        params.put(id, param);
    }

    public static <T extends Struct> T fromArray(T struct, Param... values) {
        for (Param value : values) {
            struct.define(value.getId(), value);
        }
        return struct;
    }

    public static <T extends Struct> T to(T struct, Struct from) {
        from.params.forEach(struct::define);
        return struct;
    }

    private static int msgCount = 0;

    public static void varifyMessageChanged() {
        ChatMessage[] msgs = GameChat.all();
        if(msgs.length != msgCount) { // size changed, new messages?
            msgCount = msgs.length;
            List<ChatMessage> latestMsgs = Arrays.asList(msgs).subList(msgs.length, msgs.length - 3);

            // do shit with latestMsgs
        }
    }
}