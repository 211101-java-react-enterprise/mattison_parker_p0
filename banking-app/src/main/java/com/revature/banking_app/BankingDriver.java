package com.revature.banking_app;

import com.revature.banking_app.util.AppState;

public class BankingDriver {

    public static void main(String[] args) {

        //Creates instance of AppState then runs the startup method
        AppState app = new AppState();
        app.startup();
    }

}
