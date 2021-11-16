package com.revature.banking_app.screens;

import com.revature.banking_app.daos.AppUserDAO;
import com.revature.banking_app.models.AppUser;
import com.revature.banking_app.services.UserService;
import com.revature.banking_app.util.ScreenRouter;

import java.io.BufferedReader;
import java.sql.SQLOutput;

public class ProfileScreen extends Screen{

    private final UserService userService;

    public ProfileScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService){
        super("ProfileScreen", "/profile", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        AppUser sessionUser = userService.getSessionUser();
        boolean isInProfile = true;
        while(isInProfile) {
            System.out.print("1) View your profile" +
                    "\n2) Edit your profile information" +
                    "\n3) Return to your dashboard" +
                    "\n> ");

            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    viewProfile(sessionUser);
                    break;
                case "2":
                    //TODO Implemtent means of updating a users information through the UserService Class
                    //userService.
                    break;
                case "3":
                    isInProfile = false;
                    break;
                default:
                    System.out.println("The user made an invalid selection");
            }
        }
    }

    public void viewProfile(AppUser sessionUser){


        System.out.printf("Profile Information: " +
                "\nFirst Name: %s" +
                "\nLast Name: %s" +
                "\nEmail: %s" +
                "\nUsername: %s\n\n", sessionUser.getFirstName(), sessionUser.getLastName(), sessionUser.getEmail(), sessionUser.getUsername());
    }
}
