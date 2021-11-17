package com.revature.banking_app.screens;

import com.revature.banking_app.models.Account;
import com.revature.banking_app.models.AppUser;
import com.revature.banking_app.services.UserService;
import com.revature.banking_app.util.LinkedList;
import com.revature.banking_app.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

public class AccountsScreen extends Screen{

    private final UserService userService;

    public AccountsScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService){
        super("AccountScreen", "/accounts", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        AppUser sessionUser = userService.getSessionUser();
        LinkedList<Account> accounts = sessionUser.getAccounts();

        while(userService.isSessionActive()) {

            if (sessionUser.getAccounts().isEmpty()) {

                makeAccount();

            } else {

                System.out.println("Your accounts:");
                for (int i = 0; i < accounts.size(); i++) {
                    Account account = accounts.get(i);

                    //TODO Format Balance to display in currency form
                    System.out.println(String.format(account.getAccountName() + " : " + account.getAccountType() + " | $" + account.getBalance()));
                }
                System.out.print("\n1) Add an account" +
                        "\n2) Return to your dashboard" +
                        "\n> ");
                String userSelection = consoleReader.readLine();
                switch (userSelection){
                    case "1":
                        makeAccount();
                        break;
                    case "2":
                        router.navigate("/dashboard");
                }

            }
        }
    }

    public void makeAccount() throws IOException {
        AppUser sessionUser = userService.getSessionUser();
        String accountName = "";
        String accountType = "";
        boolean isSelectingType = true;
        BigDecimal startingBalance;

        System.out.println("Please provide information to create an account: ");
        System.out.print("Account Name: ");
        accountName = consoleReader.readLine();

        while(isSelectingType){
            System.out.print("Account Type: " +
                    "\n1) Checking" +
                    "\n2) Savings" +
                    "\n> ");
            switch (consoleReader.readLine()) {
                case "1":
                    accountType = "Checking";
                    isSelectingType = false;
                    break;
                case "2":
                    accountType = "Savings";
                    isSelectingType = false;
                    break;
                default:
                    System.out.println("The user made an invalid selection");
            }
        }

        System.out.print("How much would you like to deposit into this account: ");
        startingBalance = new BigDecimal(consoleReader.readLine());

        Account newAccount = new Account(accountName, accountType, startingBalance);
        userService.createNewAccount(newAccount);
        sessionUser.addAccount(newAccount);
    }
}
