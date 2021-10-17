package io;

import server.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {

        Server teaPot = new Server("TeaPot", LocalDateTime.now());


        User[] users = new User[100];
        for (int i = 0; i < 33; i++) {
            User newUser = new User("Sid");
            users[i] = newUser;
        }

        User tommy = new User("Tommy");
        teaPot.addUser(tommy);
        tommy.setStatus(Status.ONLINE);
        teaPot.changeUserRole(tommy.getId(), Role.ADMIN);
        teaPot.listUsers();
        tommy.updateStatusMessage("I'm available!");

        teaPot.addChat("General");
        User bobby = new User("Bobby");
        teaPot.addUser(bobby);

        ChatMessage message0 = new ChatMessage("Hello, guys!", bobby,
                LocalDateTime.now());
        teaPot.commitMessage(0, message0);
        ChatMessage message1 = new ChatMessage("Hello, Bobby!", tommy,
                LocalDateTime.now());
        teaPot.commitMessage(0, message1);

        teaPot.listChats();
        try {
            teaPot.listMessages(0);
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }

        teaPot.changeUserRole(bobby.getId(), Role.MODERATOR);
        teaPot.listUsers();
        teaPot.removeUser(34);

    }
}
