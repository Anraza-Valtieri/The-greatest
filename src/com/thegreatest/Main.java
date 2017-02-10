package com.thegreatest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]) {

    }

    public static void testteacherQuiz(){
        Quiz z = new Quiz();

        int ch=0,allow=0;
        boolean go=false;
        String uname="",passwd="",msg="";

        InputStreamReader ir=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(ir);

        while(ch!=3)
        {
            try
            {
                System.out.print("\n QUIZ APPLICATION");
                System.out.print("\n ----------------");
                System.out.print("\n 1. Create Questions");
                System.out.print("\n 2. Start com.company.Quiz Contest");
                System.out.print("\n 3. Exit Application");
                System.out.print("\n Please enter a choice[1-3]:");

                ch=Integer.parseInt(br.readLine());
                if(ch>=1 && ch<=3)
                {
                    switch(ch)
                    {
                        case 1:  //modify the master file to create new questions for the quiz
                            if(z.checkFile("Questions.txt")==true)
                            {
                                System.out.print("\n The Master File is already exists.");
                                System.out.print("\n Do you wish to alter it[y/n]:");
                                String s=br.readLine();

                                if(s.equals("y") || s.equals("Y"))
                                {
                                    go=true;
                                }
                                else
                                {
                                    go=false;
                                }
                            }
                            else
                            {
                                go=true;
                            }

                            if(go==true)
                            {
                                allow=0;
                                int t=1;

                                while(allow==0 && t<=3)
                                {
                                    System.out.print("\n Mention UserName :");
                                    uname=br.readLine();
                                    System.out.print("\n Mention Password :");
                                    passwd=br.readLine();

                                    if(uname.equals("admin") && passwd.equals("admin"))
                                    {
                                        allow=1;
                                    }
                                    else
                                    {
                                        if(t<3)
                                        {
                                            System.out.println("Incorrect username or password. Please try again...!");
                                            System.out.println("Attempt = "+t+"/3");
                                            allow=0;
                                            t+=1;
                                        }
                                        else
                                        {
                                            System.out.println("Sorry, your attempt is over. System will now back to opt prompt...");
                                            allow=2;
                                        }
                                    }
                                }

                                if(allow==1) //allow to create questions
                                {
                                    boolean res=z.createQuestions();
                                    if(res==false)
                                    {
                                        System.out.println("Unable to continue execution. Please try again...!");
                                    }
                                }
                                else
                                {
                                    ch=0;
                                }
                            }
                            else
                            {
                                ch=0;
                            }
                            break;
                        case 2:  //core quiz contest routine
                            int r=z.startQuiz();
                            switch(r)
                            {
                                case 0:
                                    msg="";
                                    break;
                                case 1:
                                    msg="\nUnable to start the quiz. Could not locate the master file...!\n";
                                    msg=msg+"Please make sure the file \"Questions.txt\" exists in the ";
                                    msg=msg+"application path.\n";
                                    msg=msg+"If you are unable to locate the file, please hit OPTION 1 and ";
                                    msg=msg+"create a new file.";
                                    break;
                                case 2:
                                    msg="\nUnable to start the quiz. Could not locate entries in the master file...!\n";
                                    msg=msg+"Please make sure the master file is not empty and it is valid in ";
                                    msg=msg+"the application path. Otherwise hit OPTION 1 and create a new file.";
                                    break;
                                case 3:
                                    msg="Sorry, the program is interrupted due to in-process exception...!";
                                    break;
                            }

                            if(!msg.equals(""))
                            {
                                System.out.println(msg);
                            }
                            break;
                        case 3:  //exit the program
                            System.out.println("Thank you for using this program...");
                            System.out.println("Goodbye...");
                    }
                }
                else
                {
                    System.out.println("Invalid option. Please try again...");
                    ch=0;
                }
            }
            catch(Exception ex)
            {
                System.out.println("Error in accepting input...!");
                ch=0;
            }
        }
    }

    public static void teststudentQuiz(){
        Quiz z = new Quiz();

        int ch=0,allow=0;
        boolean go=false;
        String uname="",passwd="",msg="";

        InputStreamReader ir=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(ir);

        while(ch!=2)
        {
            try
            {
                System.out.print("\n QUIZ APPLICATION");
                System.out.print("\n ----------------");
                System.out.print("\n 1. Start com.company.Quiz Contest");
                System.out.print("\n 2. Quit com.company.Quiz");
                System.out.print("\n Please enter a choice[1-2]:");

                ch=Integer.parseInt(br.readLine());
                if(ch>=1 && ch<=2)
                {
                    switch(ch)
                    {
                        case 1:  //core quiz contest routine
                            int r=z.startQuiz();
                            switch(r)
                            {
                                case 0:
                                    msg="";
                                    break;
                                case 1:
                                    msg="\nUnable to start the quiz. Could not locate the master file...!\n";
                                    msg=msg+"Please make sure the file \"Questions.txt\" exists in the ";
                                    msg=msg+"application path.\n";
                                    msg=msg+"If you are unable to locate the file, please hit OPTION 1 and ";
                                    msg=msg+"create a new file.";
                                    break;
                                case 2:
                                    msg="\nUnable to start the quiz. Could not locate entries in the master file...!\n";
                                    msg=msg+"Please make sure the master file is not empty and it is valid in ";
                                    msg=msg+"the application path. Otherwise hit OPTION 1 and create a new file.";
                                    break;
                                case 3:
                                    msg="Sorry, the program is interrupted due to in-process exception...!";
                                    break;
                            }

                            if(!msg.equals(""))
                            {
                                System.out.println(msg);
                            }
                            break;
                        case 2:  //exit the program
                            System.out.println("Thank you for using this program...");
                            System.out.println("Goodbye...");
                    }
                }
                else
                {
                    System.out.println("Invalid option. Please try again...");
                    ch=0;
                }
            }
            catch(Exception ex)
            {
                System.out.println("Error in accepting input...!");
                ch=0;
            }
        }
    }
}
