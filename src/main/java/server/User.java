package server;

/**
 * A simple class representing a user.
 */
public class User {

    private static int nextId = 0;

    private final int id;
    private final String nickname;
    private String statusMessage;
    private Status onlineStatus;

    public User(String nickname) {
        this.id = nextId++;
        this.nickname = nickname;
        this.statusMessage = "";
        this.onlineStatus = Status.OFFLINE;
    }

    public int getId() { return id;}

    public String getNickname() {
        return this.nickname;
    }

    public void updateStatusMessage(final String message) {
        this.statusMessage = message;
    }

    public String getStatusMessage() { return this.statusMessage; }

    public void setStatus(final Status status) {
        this.onlineStatus = status;
    }

    public Status getStatus() { return this.onlineStatus; }

    @Override
    public String toString(){
        return "[" + this.onlineStatus + "]" + this.nickname + "(" + id + ")"
                + ":" + this.statusMessage + "\n";
    }
}
