package com.revature.banking_app.screens;

import com.revature.banking_app.models.AppUser;
import com.revature.banking_app.services.UserService;
import com.revature.banking_app.util.ScreenRouter;

import java.io.BufferedReader;

public class DashboardScreen extends Screen {

    private final UserService userService;

    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("DashboardScreen", "/dashboard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

        AppUser sessionUser = userService.getSessionUser();
        if (sessionUser == null) {
            System.out.println("You are not currently logged in! Navigating to Login Screen");
            router.navigate("/login");
            return;
        }

        while (userService.isSessionActive()) {
            System.out.printf("\n%s's Dashboard\n", sessionUser.getFirstName());

            String menu = "1) View/edit my profile information\n" +
                    "2) View/create an account\n" +
                    "3) Deposit/Withdraw from an account \n" +
                    "4) Logout\n" +
                    "> ";

            System.out.print(menu);

            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("View/edit profile selected");
                    break;
                case "2":
                    System.out.println("View/create an account");
                    break;
                case "3":
                    System.out.println("Deposit/Withdraw from an account");
                    break;
                case "4":
                    userService.logout();
                    break;
                default:
                    System.out.println("The user made an invalid selection");
            }
        }

    }

}
