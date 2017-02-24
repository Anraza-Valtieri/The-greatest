package thegreatest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by munpa on 23/2/2017.
 */
public class Result {
    private int resultID;
    private int userID;
    private String quizname;
    private String markObtained;
    private String totalMarks;
    private String inputAnswer;
    private String actualAnswer;
    private String question;
    private String indvmark;
    private DbConnection dc;

    public int getResultID() { return resultID; }

    public void setResultID(int resultID) { this.resultID = resultID; }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getQuizname() {
        return quizname;
    }

    public void setQuizname(String quizname) {
        this.quizname = quizname;
    }

    public String getMarkObtained() {
        return markObtained;
    }

    public void setMarkObtained(String markObtained) {
        this.markObtained = markObtained;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getInputAnswer() {
        return inputAnswer;
    }

    public void setInputAnswer(String inputAnswer) {
        this.inputAnswer = inputAnswer;
    }

    public String getActualAnswer() {
        return actualAnswer;
    }

    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = actualAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getIndvmark() {
        return indvmark;
    }

    public void setIndvmark(String indvmark) {
        this.indvmark = indvmark;
    }


    public Result(){

    }

    public Result(int userID, String quizname, String markObtained, String totalMarks, String inputAnswer, String actualAnswer, String question, String indvmark) {
        this.userID = userID;
        this.quizname = quizname;
        this.markObtained = markObtained;
        this.totalMarks = totalMarks;
        this.inputAnswer = inputAnswer;
        this.actualAnswer = actualAnswer;
        this.question = question;
        this.indvmark = indvmark;
    }

    public void createResult()  {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "INSERT INTO results (user, quizname, obtained, totalmark, inputanswer, actualanswer, question, indvmark) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        dc = new DbConnection();
        try {
            connection = dc.Connect();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setString(2, quizname);
            statement.setString(3, markObtained);
            statement.setString(4, totalMarks);
            statement.setString(5, inputAnswer);
            statement.setString(6, actualAnswer);
            statement.setString(7, question);
            statement.setString(8, indvmark);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("[RESULT]A new result was inserted successfully!");
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

    public Result getSingleResult()  {
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null;

        Result res = null;
        String query = "SELECT * FROM results WHERE rID=" + resultID;
        dc = new DbConnection();
        try {
            connection = dc.Connect();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery(query);

            if (rs.next()) {
                res = new Result();
                res.setUserID(rs.getInt("user"));
                res.setQuizname(rs.getString("quizname"));
                res.setMarkObtained(rs.getString("obtained"));
                res.setTotalMarks(rs.getString("totalmark"));
                res.setInputAnswer(rs.getString("inputanswer"));
                res.setActualAnswer(rs.getString("actualanswer"));
                res.setQuestion(rs.getString("question"));
                res.setIndvmark(rs.getString("indvmark"));

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
        return res;
    }

}
