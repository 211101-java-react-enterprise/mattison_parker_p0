package com.revature.banking_app.screens;

import com.revature.banking_app.models.Account;
import com.revature.banking_app.models.AppUser;
import com.revature.banking_app.services.UserService;
import com.revature.banking_app.util.LinkedList;
import com.revature.banking_app.util.ScreenRouter;

import java.io.BufferedReader;

public class DepositWithdrawScreen extends Screen{

    private final UserService userService;

    public DepositWithdrawScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("DepositWithdrawScreen", "/depositwithdraw", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        AppUser sessionUser = userService.getSessionUser();
        LinkedList<Account> accounts = sessionUser.getAccounts();



    }
}
