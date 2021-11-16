package com.revature.banking_app.daos;

import com.revature.banking_app.models.Account;
import com.revature.banking_app.models.AppUser;
import com.revature.banking_app.services.UserService;
import com.revature.banking_app.util.ConnectionFactory;
import com.revature.banking_app.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class AccountDAO implements CrudDAO<Account>{

    @Override
    public Account save(Account newAccount) {
        //TODO Assign user_id and account_id in the user_accounts table
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            newAccount.setId(UUID.randomUUID().toString());

            String sql = "insert into accounts (id, account_name, account_type, balance) values (?, ?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newAccount.getId());
            pstmt.setString(2, newAccount.getAccountName());
            pstmt.setString(3, newAccount.getAccountType());
            pstmt.setBigDecimal(4, newAccount.getBalance());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return newAccount;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Account findById(String id) {
        return null;
    }

    @Override
    public boolean update(Account updatedObj) {
        return false;
    }


    @Override
    public boolean removeById(String id) {
        return false;
    }
}
