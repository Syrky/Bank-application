package com.company;

import com.company.data.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static boolean isLogin = false;

    public Main() {
    }

    public static void main(String[] args) throws Exception {
        int option = 0;
        Scanner sc = new Scanner(System.in);

        while (option == 0) {
            System.out.println("Select an option\n");
            System.out.println("1.Create account");
            System.out.println("2.Sign in\n");

            while (option < 1 || option > 2) {
                System.out.print("Type your choice:");
                option = sc.nextInt();
            }
        }

        switch (option) {
            case 1:
                System.out.println("\n\nCreate new Account\n");
                System.out.println("Enter your first name:");
                String firstName = sc.next().trim();
                System.out.println("Enter your last name:");
                String lastName = sc.next().trim();
                Account account = new Account(firstName, lastName);
                account.register();
                break;
            case 2:
                while (isLogin == false) {
                    System.out.println("\n\nSign in\n");
                    System.out.println("Enter your card number:");
                    String cardnum = sc.next();
                    System.out.println("Enter your pincode:");
                    String code = sc.next();
                    Operation operation = new Operation(cardnum, code);

                    try {
                        Connection c = DBManager.connection();
                        Statement stm4 = c.createStatement();
                        String sql4 = "SELECT * from Card where card_number = '" + cardnum + "' and pincode = '" + code + "'";
                        ResultSet rs4 = stm4.executeQuery(sql4);
                        if (rs4.next()) {
                            isLogin = true;
                            System.out.println("\n\n      Login Succes \n");
                            System.out.println("Enter a option\n");
                            System.out.println("1.Show Balance");
                            System.out.println("2.Deposit");
                            System.out.println("3.Send money to other person");

                            int option_user = 0;
                            while ( option_user<1 || option_user > 3 ) {
                                System.out.println("Type your choice: ");
                                option_user = sc.nextInt();
                            }

                            int balance = 0;
                            switch (option_user) {
                                case 1:
                                    System.out.println(" Show Balance");
                                    balance = operation.showBalance(cardnum);
                                    System.out.println(balance + "$");
                                    break;
                                case 2:
                                    System.out.println("\n\n  Make deposit \n");

                                    int money;
                                    for (money = 0; money <= 0; money = sc.nextInt()) {
                                        System.out.println("Enter Money:");
                                    }

                                    operation.deposit(money, cardnum);
                                    balance = operation.showBalance(cardnum);
                                    System.out.println("\nCurrent Balance is" + balance + "$");
                                    break;
                                case 3:
                                    System.out.println("Send money to anothar person:");
                                    System.out.println("Enter number of othar client:");
                                    String other_number = sc.next();

                                    int other_money;
                                    for (other_money = 0; other_money <= 0; other_money = sc.nextInt()) {
                                        System.out.println("Enter Money to send for other client:");
                                    }

                                    operation.sendMoney(other_money, other_number, cardnum);
                                    System.out.println("\n You sent " + other_money + "$ to " + other_number);
                            }
                        } else {
                            System.out.println("\n Login Fail");
                        }
                    } catch (Exception var18) {
                        System.out.println(var18);
                    }
                }

        }
    }
}
