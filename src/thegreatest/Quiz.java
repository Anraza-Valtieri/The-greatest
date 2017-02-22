package thegreatest;

import javafx.beans.property.StringProperty;

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
    private DbConnection dc;

    public Quiz(){
        quiz_id = -1;
    }
    public Quiz(String quizname, String questionids, String subject){
        this.quizname=quizname;
        this.questionids = questionids;
        this.subject = subject;
    }

    public int getQuiz_id() {
        return quiz_id;
    }
    public void setQuiz_id(int quiz_id){ this.quiz_id = quiz_id; }
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

    public void getQuizDBName(){
        ResultSet rs = null;

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "SELECT quizname FROM quiz WHERE quiz_id=" + quiz_id;
        dc = new DbConnection();
        try {
            System.out.println(quiz_id + " TEST");
            connection = dc.Connect();
            statement = connection.prepareStatement(sql);
            //statement.setInt(1, quiz_id);


            rs = statement.executeQuery(sql);

            if (rs.next()){
                this.setQuizname(rs.getString("quizname"));
            }
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

        return;
    }

    public String getQuestions(String subject){
        String questions = "";

        ResultSet rs = null;
        List<String> results = new ArrayList<String>();

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "SELECT questions_id FROM quiz_questions where subject = ?";
        dc = new DbConnection();
        try {
            connection = dc.Connect();
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

        //String questions = getQuestions(subject);
        String sql = "INSERT INTO quiz (quizname, question_id, subject) VALUES (?, ?, ?)";
        dc = new DbConnection();
        try {
            connection = dc.Connect();
            statement = connection.prepareStatement(sql);
            statement.setString(1, quizname);
            statement.setString(2, questionids);
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
        main.quizName = subject;
        return;

    }

    public boolean checkExist(String subject){
        dc = new DbConnection();
        Connection conn = dc.Connect();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM quiz where quizname = '"+subject+"'");
            boolean val = rs.next();
            if(val == false) {
                System.out.println("Quiz name does not exist!");
                return false;
            }
            while (val){
                System.out.println("Quiz name does exist!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public void setUpdateQuizName(String subject){
        Connection connection = null;
        PreparedStatement statement = null;

        if(checkExist(subject) == false) {
            String sql = "UPDATE quiz set subject = ?, quizname = ? where " +
                    "(subject = ?)";
            dc = new DbConnection();
            try {
                connection = dc.Connect();
                statement = connection.prepareStatement(sql);
                statement.setString(1, subject);
                statement.setString(2, subject);
                statement.setString(3, main.quizName);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Quiz name updated!");
                    Questions q = new Questions();
                    q.updateSubject(subject, main.quizName);
                    main.quizName = subject;
                    setSubject(subject);
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
        }
    }

    public void cleanupQuiz(){
        dc = new DbConnection();
        try{
            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("SET @num := 0; UPDATE quiz SET quiz_id = @num := (@num+1)");
        } catch (SQLException ex) {
            System.err.println("Error "+ex);
        }
    }

    public void deleteQuiz(String quiz_id)  {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "DELETE FROM quiz WHERE subject = ?";
        dc = new DbConnection();
        try {
            connection = dc.Connect();
            statement = connection.prepareStatement(sql);
            statement.setString(1, quiz_id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                cleanupQuiz();
                Questions q = new Questions();
                q.deleteQuestions(main.quizName);
                System.out.println("Quiz "+main.quizName+" deleted!");
                main.quizName = "";
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

}