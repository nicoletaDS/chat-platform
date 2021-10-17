package server;

import java.time.LocalDateTime;
import java.util.*;

/**
 * A simple class representing a server.
 */
public class Server {
    private final int MAX_USERS_NUMBER = 100;
    private final int MAX_CHATS_NUMBER = 20;

    private static int nextId = 0;

    private final int id;
    private final String serverName;
    private final LocalDateTime creationTime;
    private final List<User> users;
    private final List<Chat> chats;
    private final Map<Integer, Role> roles;

    public Server(final String serverName, final LocalDateTime creationTimeime) {
        this.id = nextId++;
        this.serverName = serverName;
        this.creationTime = creationTimeime;
        this.users = new ArrayList<>(MAX_USERS_NUMBER);
        this.chats = new ArrayList<>(MAX_CHATS_NUMBER);
        this.roles = new HashMap<>();
    }

    public String getServerName() { return this.serverName; }

    public String getCreationTime() {
        return this.creationTime.toString();
    }

    /**
     * Add a new user to server, if the list is not full.
     * Every new user is automatically added to all chats from server.
     * @param user the new user to be added
     * @throws FullListException if the list is full
     */
    public void addUser(User user) throws FullListException {
        if (users.size() == MAX_USERS_NUMBER) {
            throw new FullListException("The users list is full.");
        } else {
            users.add(user);
            // Set user role to normal user (default)
            roles.put(user.getId(), Role.USER);
            // Add user to all existing chats
            for (Chat chat : chats) {
                chat.addUser(user);
            }
        }
    }

    public void removeUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                roles.remove(user.getId());
            }
        }
        // Remove user from all existing chats
        for (Chat chat : chats) {
            chat.removeUser(id);
        }
    }

    public void addChat(String name) {
        if (chats.size() == MAX_CHATS_NUMBER) {
            throw new FullListException("The chat list is full.");
        } else {
            Chat chat = new Chat(name);
            chats.add(chat);
            for (User user : users) {
                chat.addUser(user);
            }
        }
    }

    public void removeChat(int id) {
        chats.removeIf(chat -> chat.getId() == id);
    }

    public void commitMessage(int chatId, ChatMessage message) {
        for (Chat chat : chats) {
            if (chat.getId() == chatId) {
                chat.commitMessage(message);
            }
        }
    }

    public void listUsers() {
        StringBuilder message = new StringBuilder("# Users=" + users.size() + "\n");
        for (User user : users) {
            String[] msg = user.toString().split("]");
            message.append(msg[0]).append(",").append(roles.get(user.getId()))
                    .append("]").append(msg[1]).append("\n");
        }
        System.out.println(message);
    }

    public void listChats() {
        StringBuilder message = new StringBuilder("# Chats=" + chats.size() + "\n");
        for (Chat chat : chats) {
            message.append(chat.toString()).append("\n");
        }
        System.out.println(message);
    }

    public void listMessages(int chatId) {
        for (Chat chat : this.chats) {
            if (chat.getId() == chatId) {
                chat.listMessages();
            }
        }
    }

    public void changeUserRole(int userId, Role newRole) {
        for (int id : this.roles.keySet()) {
            if (id == userId) {
                roles.replace(id, newRole);
            }
        }
    }

    public String toString() {
        return this.serverName + "(" + id + "):" + this.users.size() + ","
                + this.chats.size();
    }

}
