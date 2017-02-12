package com.thegreatest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    boolean loginSuccess = false;
    int loginType = 0; // 0 Student 1 Teacher
    public void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your ID: ");
        String uniID = sc.nextLine();
        System.out.print("Please enter your password ");
        String pw = sc.nextLine();

        Account login = new Account();
        login.setUniqID(uniID);
        login.setPassword(pw);

        loginSuccess = login.getLogin(uniID, pw);
        loginType = login.getType();

        while(loginSuccess){

        }
    }

    public void testteacherQuiz(){

    }

    public void teststudentQuiz(){

    }
}
