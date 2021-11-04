package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "Jobs", (byte) 25);
        userService.saveUser("Jhon", "Doe", (byte) 14);
        userService.saveUser("Mila", "Anders", (byte) 20);
        userService.saveUser("Felix", "Bloom", (byte) 22);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
