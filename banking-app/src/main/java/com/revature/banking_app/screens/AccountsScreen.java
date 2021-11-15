package com.revature.banking_app.screens;

import com.revature.banking_app.models.Account;
import com.revature.banking_app.services.UserService;
import com.revature.banking_app.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountsScreen extends Screen{

    private final UserService userService;

    public AccountsScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService){
        super("AccountScreen", "/accounts", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

    }
}
