package server;

import java.time.LocalDateTime;

/**
 * A class representing a message that a {@link User} can send in a {@link Chat}.
 */
public class ChatMessage {

    private final String content;
    private final User user;
    private final LocalDateTime creationTime;

    public ChatMessage(final String content, final User user, final LocalDateTime creationTime) {
        this.content = content;
        this.user = user;
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return this.user + "[" + this.creationTime.toString() + "]:" + this.content;
    }
}
