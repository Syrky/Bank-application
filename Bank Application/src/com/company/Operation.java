package com.company;

import com.company.data.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Operation {
    private String cardnum;
    private String pincode;
    private Integer balance;

    Operation(String cardnum, String pincode) {
        this.cardnum = cardnum;
        this.pincode = pincode;
    }

    public Integer showBalance(String cardnum) {
        try {
            Connection c = DBManager.connection();
            Statement stm = c.createStatement();
            Statement stm5 = c.createStatement();
            String sql5 = "SELECT * from Balance where card_number = ' " + cardnum + "'";

            for(ResultSet rs5 = stm5.executeQuery(sql5); rs5.next(); this.balance = rs5.getInt(2)) {
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }

        return this.balance;
    }

    public void deposit(Integer amount, String cardnum) {
        try {
            Connection c = DBManager.connection();
            Statement stm6 = c.createStatement();
            String sql6 = "Update Balance set balance = balance + '" + amount + "' where card number = '" + cardnum + "'";
            stm6.executeUpdate(sql6);
        } catch (Exception var6) {
            System.out.println(var6);
        }

    }

    public void sendMoney(Integer amount_other, String number_other, String cardnum) {
        try {
            Connection c = DBManager.connection();
            Statement stm7 = c.createStatement();
            String sql7 = "Update balance set balance = balance + '" + amount_other + "' where card_number = '" + number_other + "'";
            stm7.executeUpdate(sql7);
            Statement stm8 = c.createStatement();
            String sql8 = "Update balance set balance = balance - '" + amount_other + "' where card_number = '" + cardnum + "'";
            stm8.executeUpdate(sql8);
        } catch (Exception var9) {
            System.out.println(var9);
        }

    }
}

