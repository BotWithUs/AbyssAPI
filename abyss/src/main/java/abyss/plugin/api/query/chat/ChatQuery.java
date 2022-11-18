package abyss.plugin.api.query.chat;

import abyss.plugin.api.game.chat.ChatMessage;
import abyss.plugin.api.game.chat.MessageType;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ChatQuery {

    private String[] names;
    private String[] messages;
    private int[] types;

    private Pattern messagePattern;

    private int takeCount;

    public ChatQuery() {
        this.names = null;
        this.messages = null;
        this.types = null;
        this.messagePattern = null;
        this.takeCount = -1;
    }

    public ChatQuery take(int count) {
        this.takeCount = count;
        return this;
    }

    public ChatQuery names(String... names) {
        this.names = names;
        return this;
    }

    public ChatQuery messages(String... messages) {
        this.messages = messages;
        return this;
    }

    public ChatQuery types(MessageType... types) {
        this.types = Arrays.stream(types)
                .map(MessageType::getTypes)
                .flatMapToInt(Arrays::stream)
                .toArray();
        return this;
    }

    public ChatQuery pattern(Pattern pattern) {
        this.messagePattern = pattern;
        return this;
    }

    public ChatResultSet result() {
        return new ChatResultSet(results());
    }

    private native List<ChatMessage> results();

}