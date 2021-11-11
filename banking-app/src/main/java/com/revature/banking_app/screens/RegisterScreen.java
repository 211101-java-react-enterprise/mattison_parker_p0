package com.revature.banking_app.screens;

import com.revature.banking_app.exceptions.InvalidRequestException;
import com.revature.banking_app.exceptions.ResourcePersistenceException;
import com.revature.banking_app.models.AppUser;
import com.revature.banking_app.services.UserService;
import com.revature.banking_app.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private final UserService userService;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("RegisterScreen", "/register", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("The user selected Register");
        System.out.println("Please provide us with some basic information.");
        System.out.print("First name: ");
        String firstName = consoleReader.readLine();

        System.out.print("Last name: ");
        String lastName = consoleReader.readLine();

        System.out.print("Email: ");
        String email = consoleReader.readLine();

        System.out.print("Username: ");
        String username = consoleReader.readLine();

        System.out.print("Password: ");
        String password = consoleReader.readLine();

        System.out.printf("Provided user first and last name: { \"firstName\": %s, \"lastName\": %s}\n", firstName, lastName);

        AppUser newUser = new AppUser(firstName, lastName, email, username, password);

        try {
            userService.registerNewUser(newUser);
            router.navigate("/dashboard");

        } catch (InvalidRequestException | ResourcePersistenceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // TODO log this unexpected exception to a file
            e.printStackTrace();
        }


    }

}
