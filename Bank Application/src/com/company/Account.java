package com.company;

import com.company.data.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Account {
    private final String FirstName;
    private final String LastName;
    private Integer balance;

    Account(String firstName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public Boolean register() throws Exception {
        try {
            Connection c = DBManager.connection();
            Statement stm1 = c.createStatement();
            String sql = "INSERT into Account values(null,'" + this.FirstName + "', '" + this.LastName + "')";
            stm1.executeUpdate(sql);
            Statement stm2 = c.createStatement();
            String sql2 = "SELECT last_insert_id()";
            ResultSet rs2 = stm2.executeQuery(sql2);

            int last_account_id;
            for(last_account_id = 0; rs2.next(); last_account_id = rs2.getInt(1)) {
            }

            String cardNumber = this.generateCardNumber();
            String code = this.generatePinCode();
            Statement stm3 = c.createStatement();
            String sql3 = "INSERT into Card values( ' " + last_account_id + " ', '" + cardNumber + "', '" + code + "')";
            stm3.executeUpdate(sql3);
            Statement stm4 = c.createStatement();
            String sql4 = "INSERT into Balance values('" + cardNumber + "','0'";
            stm4.executeUpdate(sql4);
            c.close();
            System.out.println("\n\n Account was succesfully created.\n");
            System.out.println("Card Number: " + cardNumber);
            System.out.println("Pincode: " + code);
            return true;
        } catch (Exception var14) {
            System.out.println(var14);
            return false;
        }
    }

    public String generateCardNumber() {
        int length = 8;
        String passwordSet = "1234567890";
        char[] cardNumber = new char[length];

        for(int i = 0; i < length; ++i) {
            int rand = (int)(Math.random() * (double)passwordSet.length());
            cardNumber[i] = passwordSet.charAt(rand);
        }

        return new String(cardNumber);
    }

    public String generatePinCode() {
        int length = 4;
        String passwordSet = "1234567890";
        char[] pincode = new char[length];

        for(int i = 0; i < length; ++i) {
            int rand = (int)(Math.random() * (double)passwordSet.length());
            pincode[i] = passwordSet.charAt(rand);
        }

        return new String(pincode);
    }
}
