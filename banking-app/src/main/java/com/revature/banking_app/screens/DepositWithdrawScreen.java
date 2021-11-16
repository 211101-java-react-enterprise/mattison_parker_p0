package com.revature.banking_app.screens;

import com.revature.banking_app.util.ScreenRouter;

import java.io.BufferedReader;

public class DepositWithdrawScreen extends Screen{

    public DepositWithdrawScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("DepositWithdrawScreen", "/depositwithdraw", consoleReader, router);
    }

    @Override
    public void render() throws Exception {

    }
}
