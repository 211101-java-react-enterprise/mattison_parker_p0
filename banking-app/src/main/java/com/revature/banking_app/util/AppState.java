package com.revature.banking_app.util;

import com.revature.banking_app.daos.AppUserDAO;
import com.revature.banking_app.screens.*;
import com.revature.banking_app.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean appRunning;
    private final ScreenRouter router;

    public AppState() {

        appRunning = true;
        router = new ScreenRouter();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        AppUserDAO userDAO = new AppUserDAO();
        UserService userService = new UserService(userDAO);
        router.addScreen(new WelcomeScreen(consoleReader, router));
        router.addScreen(new RegisterScreen(consoleReader, router, userService));
        router.addScreen(new LoginScreen(consoleReader, router, userService));
        router.addScreen(new DashboardScreen(consoleReader, router, userService));
        router.addScreen(new ProfileScreen(consoleReader, router, userService));

    }

    public void startup() {

        try {
            while (appRunning) {
                router.navigate("/welcome");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void shutdown() {
        appRunning = false;
    }
}
