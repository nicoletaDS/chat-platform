package server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represent a simple chat that {@link User users} can use to send
 * {@link ChatMessage messages} to each other.
 */
public class Chat {

    private final int MAX_USERS_NUMBER = 100;

    private static int nextId = 0;
    
    private final int id;
    private final String chatName;
    private final List<User> users;
    private final LinkedList<ChatMessage> messages;

    /**
     * Creates a new instance of {@link Chat}.
     *
     * @param name the name of the chat
     */
    public Chat(final String name) {
        this.id = nextId++;
        this.chatName = name;
        this.users = new ArrayList<>(MAX_USERS_NUMBER);
        this.messages = new LinkedList<>();
    }


    public int getId() {
        return id;
    }

    public void addUser(final User user) {
        if ( users.size() == MAX_USERS_NUMBER) {
            throw new FullListException("The users list is full.");
        } else {
            users.add(user);
        }
    }

    public void removeUser(final int userId) {
        users.removeIf(user -> user.getId() == userId);
    }
    

    public void commitMessage(final ChatMessage message) {
        // if the message list is full, delete the first element that was added
        final int MAX_MESSAGES_NUMBER = 100;
        if (messages.size() == MAX_MESSAGES_NUMBER) {
            messages.removeFirst();
        }

        messages.addLast(message);
    }

    public void clearChat() {
        messages.clear();
    }

    public void listMessages() {
        StringBuilder output = new StringBuilder(id + ", " + this.chatName);
        for (ChatMessage message : messages) {
            output.append(message.toString());
        }
        System.out.println(output);
    }

    @Override
    public String toString() {
        return id + ", " + this.chatName + "\n";
    }
}
