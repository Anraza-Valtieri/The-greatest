package com.thegreatest;

import java.util.Scanner;

public class RegisterTest {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		String name = sc.nextLine();
		System.out.print("Please enter your ID: ");
		String uniID = sc.nextLine();
		System.out.print("Please enter your email ");
		String mail = sc.nextLine();
		System.out.print("Please enter your password ");
		String pw = sc.nextLine();
		System.out.print("Please enter type: ");
		int typeAcc = sc.nextInt();

		
		Account test = new Account();
		test.setName(name);
		test.setUniqID(uniID);
		test.setEmail(mail);
		test.setPassword(pw);
		test.setType(typeAcc);
		
		
		test.createAccount(name, uniID, mail, pw, typeAcc);
	}
}
