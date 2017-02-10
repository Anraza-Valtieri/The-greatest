package com.thegreatest;

import java.util.Scanner;


public class LoginTest {
	


	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter your ID: ");
		String uniID = sc.nextLine();
		System.out.print("Please enter your password ");
		String pw = sc.nextLine();

		Account login = new Account();
		login.setUniqID(uniID);
		login.setPassword(pw);
		
		login.getLogin(uniID, pw);
	}

		
	

		    /*if(uiID.equals(uniqID) && pw.equals(password)) 
		        System.out.println("You are logged in");
		        else 
		        System.out.println("Log in fail");*/



		

		
		
		
		
		
	}

