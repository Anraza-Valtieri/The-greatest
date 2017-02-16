package thegreatest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Quiz
{
    private int quiz_id;
    private String quizname;
    private String questionids;
    private String subject;

    public Quiz(){

    }
    public Quiz(String quizname, String questionids, String subject){
        this.quizname=quizname;
        this.questionids = questionids;
        this.subject = subject;
    }

    public int getQuiz_id() {
        return quiz_id;
    }
    public String getQuizname() {
        return quizname;
    }
    public void setQuizname(String quizname) {
        this.quizname = quizname;
    }
    public String getQuestionids() {
        return questionids;
    }
    public void setQuestionids(String questionids) {
        this.questionids = questionids;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQuestions(String subject){
        String questions = "";

        ResultSet rs = null;
        List<String> results = new ArrayList<String>();

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "SELECT questions_id FROM quiz_questions where subject = ?";

        try {
            connection = dnConnector.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject);


            rs = statement.executeQuery(sql);

            while (rs.next()){
                results.add(Integer.toString(rs.getInt("question_id")));
            }

            questions = String.join(", ", results);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return questions;

    }

    public void createquiz(){
        Connection connection = null;
        PreparedStatement statement = null;

        String questions = getQuestions(subject);


        String sql = "INSERT INTO quiz (quizname, question_id, subject) VALUES (?, ?, ?)";

        try {
            connection = dnConnector.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, quizname);
            statement.setString(2, questions);
            statement.setString(3, subject);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Quiz Created!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return;

    }



    public void deleteQuiz(int quiz_id)  {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "DELETE FROM quiz WHERE quiz_id = ?";

        try {
            connection = dnConnector.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, quiz_id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Quiz Deleted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return;
    }





	/*

	//create and save questions to the master file
	//returns true if successfull else returns false
	public boolean createQuestions()
	{
		int go=0,saved=0;
		boolean result=false,ret=false;

		String[] ques;
		String msg="",nos="";

		try
		{
			br=new BufferedReader(ir);
			while(result==false)
			{
				System.out.print("\n Please mention no. of questions to store");
				System.out.print("\n [Enter 0 to skip this step] : ");
				nos=br.readLine();

				result=isNum(nos);
				if(result==false)
				{
					System.out.println("The input should be an positive integer only...!");
				}
				else
				{
					if(nos.equals("0"))
					{
						go=0;
					}
					else
					{
						go=1;
					}
				}
			}

			if(go==0)
			{
				ret=true;
			}
			else
			{
				for(int i=1;i<=Integer.parseInt(nos);i++)
				{
					saved=0;
					ques=new String[6];
					while(saved!=1)
					{
						msg="";
						br=new BufferedReader(ir);
						//question body
						System.out.print("\n Please type Question No. "+i+" : ");
						ques[0]=br.readLine();
						//choice 1
						System.out.print("\n Option 1 : ");
						ques[1]=br.readLine();
						//choice 2
						System.out.print("\n Option 2 : ");
						ques[2]=br.readLine();
						//choice 3
						System.out.print("\n Option 3 : ");
						ques[3]=br.readLine();
						//choice 4
						System.out.print("\n Option 4 : ");
						ques[4]=br.readLine();
						//correct answer
						System.out.print("\n Correct Answer(Mention option no. only) : ");
						ques[5]=br.readLine();

						//validating input
						if(ques[0].equals(""))
						{
							msg="The question body should not be blank...!\n";
						}
						for(int j=1;j<=4;j++)
						{
							if(ques[j].equals(""))
							{
								msg=msg+"Option "+j+" should have been mentioned...!\n";
							}
						}
						if(isNum(ques[5])==false)
						{
							msg=msg+"The input for correct answer should be an integer...!\n";
						}
						else
						{
							if(Integer.parseInt(ques[5])>=1 && Integer.parseInt(ques[5])<=4)
							{
								//do nothing, the input for correct answer is okey
							}
							else
							{
								msg=msg+"Invalid answer indication no. It must be within 1 and 4...!";
							}
						}

						//data violated according to rule, so display the errors
						if(!msg.equals(""))
						{
							System.out.print("\n The following mistakes have been occured :-");
							System.out.print("\n --------------------------------------------");
							System.out.print("\n " + msg);
							saved=0;
						}
						else //save the question and proceed
						{
							try
							{
								fos=new FileOutputStream("Questions.txt",true);
								ps=new PrintStream(fos);

								ps.println(ques[0]+","+ques[1]+","+ques[2]+","+ques[3]+","+ques[4]+","+ques[5]);
								fos.close();
								System.out.println("Successfully saved...");
								saved=1;
							}
							catch(Exception rx)
							{
								System.out.println("Error in accepting details. Please try again...!");
								saved=0;
							}
						}
					}
					ret=true;
				}
			}
		}
		catch(Exception ex)
		{
			ret=false;
		}

		return ret;
	}

	//function to check whether the input is a valid integer or not
	public boolean isNum(String input)
	{
		boolean b=false;

		char a[]=new char[input.length()];
		a=input.toCharArray();

		for(int i=0;i<a.length;i++)
		{
		  if(a[i]>='0' && a[i]<='9')
		  {
		    b=true;
		  }
		  else
		  {
		    b=false;
		    break;
		  }
		}

		return b;
	}

	//start the quiz contest module
	//returns 0 if committed successfully otherwise anyone of the following :-
	//1 -- the master file is not located
	//2 -- the master file is located with empty stream
	//3 -- any other runtime exception
	public int startQuiz() //throws IOException
	{
		int retVal=0,counter=0,commit=0,nos=0;
		int attempted=0,skipped=0,correct=0,wrong=0;
		boolean made=false;

		String thisLine,summary="";
		String[] fullText;
		String[] question;

		if(checkFile("Questions.txt")==false)
		{
			retVal=1;
		}
		else
		{
			try
			{
				frs=new FileReader("Questions.txt");
				br=new BufferedReader(frs);
				while((thisLine=br.readLine()) != null) //counting total no. of entries
				{
					counter+=1;
					nos+=1;
				}

				frs.close();

				if(counter==0)  //file located with empty stream, so give user the option to create a new file
				{
					retVal=2;
				}
				else
				{
					frs=new FileReader("Questions.txt");
					br=new BufferedReader(frs);
					fullText=new String[counter];
					counter=0;
					while((thisLine=br.readLine()) != null) //enumerating through the master file and fetching all entries
					{
						fullText[counter]=thisLine;
						counter+=1;
					}
					frs.close();


					//displaying questions to the user and respective actions
					for(int i=0;i<fullText.length;i++)
					{
						question=new String[6];
						question=fullText[i].split(",");
						commit=0;
						made=false;

						br=new BufferedReader(ir);

						while(commit!=1)
						{
							try
							{
								System.out.print("\n Question No. "+(i+1));
								System.out.print("\n ------------------");
								System.out.print("\n "+question[0]);
								System.out.print("\n Option 1 -->"+question[1]);
								System.out.print("\n Option 2 -->"+question[2]);
								System.out.print("\n Option 3 -->"+question[3]);
								System.out.print("\n Option 4 -->"+question[4]);
								while(made!=true)
								{
									System.out.print("\n YOUR ANSWER[1-4] (Type 0 to Skip) : ");
									String opt=br.readLine();
									if(isNum(opt)==false)
									{
										System.out.println("Invalid input. Your choice should be an integer only...!");
										made=false;
									}
									else
									{
										if(Integer.parseInt(opt)>=1 && Integer.parseInt(opt)<=4)
										{
											attempted+=1;
											if(Integer.parseInt(opt)==Integer.parseInt(question[5]))
											{
												correct+=1;
											}
											else
											{
												if(summary.equals(""))
												{
													summary="Question : "+question[0]+"\n";
													summary=summary + "Correct Answer : "+question[5]+" "+question[Integer.parseInt(question[5])]+"\n";
													summary=summary + "Your Answer was : "+opt+" "+question[Integer.parseInt(opt)];
												}
												else
												{
													summary=summary + "\n\n Question : "+question[0]+"\n";
													summary=summary + "Correct Answer : "+question[5]+" "+question[Integer.parseInt(question[5])]+"\n";
													summary=summary + "Your Answer was : "+opt+" "+question[Integer.parseInt(opt)];

												}

												wrong+=1;
											}
											made=true;
										}
										else
										{
											if(Integer.parseInt(opt)==0)  //question has been skipped by participent
											{
												skipped+=1;
												made=true;
											}
											else
											{
												System.out.println("Incorrect choice. Please make it between 1 and 4 only...!");
												made=false;
											}
										}
									}
								}
							}
							catch(Exception ex)
							{
								retVal=3;
							}
							commit=1;
						}
					}

					if(retVal==0)
					{
						System.out.println("\n CONTEST RESULT");
						System.out.println("\n ------------------------------");
						System.out.println("\n No. of Question(s) Presented = "+nos);
						System.out.println("\n No. of Question(s) Attempted = "+attempted);
						System.out.println("\n No. of Question(s) Skipped = "+skipped);
						System.out.println("\n Correct Answers = "+correct);
						System.out.println("\n Wrong Answers = "+wrong);
						if(attempted!=0)
						{
							System.out.println("\n Accuracy Percentage = "+(correct*100)/attempted+"%\n");
						}
						else
						{
							System.out.println("\n Accuracy Percentage = Not Calculated\n");
						}

						if(!summary.equals(""))
						{
							System.out.println("\n\n Correct Answer(s) of Wrong answered question(s) :-\n");
							System.out.print("--------------------------------------------------------\n");
							System.out.print(summary+"\n");
						}
						System.out.print("--------------------------------------------------------\n\n");
					}
				}
			}
			catch(Exception mx)
			{
				retVal=3;
			}
		}

		return retVal;
	}

*/
}