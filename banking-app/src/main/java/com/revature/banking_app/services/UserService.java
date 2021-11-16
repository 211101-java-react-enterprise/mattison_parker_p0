package com.revature.banking_app.services;

import com.revature.banking_app.daos.AccountDAO;
import com.revature.banking_app.daos.AppUserDAO;
import com.revature.banking_app.exceptions.AuthenticationException;
import com.revature.banking_app.exceptions.InvalidRequestException;
import com.revature.banking_app.exceptions.ResourcePersistenceException;
import com.revature.banking_app.models.Account;
import com.revature.banking_app.models.AppUser;

import java.math.BigDecimal;

public class UserService {

    private final AppUserDAO userDAO ;
    private final AccountDAO accountDAO;
    private AppUser sessionUser;

    public UserService(AppUserDAO userDAO, AccountDAO accountDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        this.sessionUser = null;
    }

    public AppUser getSessionUser() {
        return sessionUser;
    }

    public boolean registerNewUser(AppUser newUser) {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provided!");
        }

        boolean usernameAvailable = userDAO.findUserByUsername(newUser.getUsername()) == null;
        boolean emailAvailable = userDAO.findUserByEmail(newUser.getEmail()) == null;

        if (!usernameAvailable || !emailAvailable) {
            String msg = "The values provided for the following fields are already taken by other users:";
            if (!usernameAvailable) msg = msg + "\n\t- username";
            if (!emailAvailable) msg = msg + "\n\t- email";
            throw new ResourcePersistenceException(msg);
        }

        AppUser registeredUser = userDAO.save(newUser);

        if (registeredUser == null) {
            throw new ResourcePersistenceException("The user could not be persisted to the datasource!");
        }

        return true;

    }

    public void authenticateUser(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        AppUser authenticatedUser = userDAO.findUserByUsernameAndPassword(username, password);

        if (authenticatedUser == null) {
            throw new AuthenticationException();
        }

        sessionUser = authenticatedUser;

    }

    public boolean createNewAccount(Account newAccount){
        //TODO Validate Given Account Information Before Passing On To The AppUserDAO
        if(!isAccountValid(newAccount)){
            throw new InvalidRequestException("Invalid account data provided!");
        }

        Account account = accountDAO.save(newAccount);

        if (account == null) {
            throw new ResourcePersistenceException("The account could not be persisted to the datasource!");
        }

        return true;
    }

    public void logout() {
        sessionUser = null;
    }

    public boolean isSessionActive() {
        return sessionUser != null;
    }

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }

    public boolean isAccountValid(Account account){
        if(account == null) return false;
        if(account.getAccountName() == null || account.getAccountName().trim().equals("")) return false;
        if(account.getAccountType() == null || account.getAccountType().trim().equals("")) return false;
        return account.getBalance() != null;
    }

}
