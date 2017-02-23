package thegreatest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Questions {
    int question_id;
    String teacher_id;
    String subject;
    String question_text;
    int question_type; // MCQ - 0 | T/F - 1 | SA - 2
    String data1; // SA - MCQ - T|F
    String data2; // MCQ - T|F
    String data3; // MCQ
    String data4; // MCQ
    String data5; // Store MCQ choice
    double marks;

    public Questions(){

    }

    public Questions(String teacher_id, String subject, String question_text, int question_type, String data1, String data2, String data3, String data4, String data5, double marks){
        this.teacher_id = teacher_id;
        this.subject = subject;
        this.question_text = question_text;
        this.question_type = question_type;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.marks = marks;
    }

    public int getQuestion_id() {
        return question_id;
    }
    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
    public String getTeacher_id() {
        return teacher_id;
    }
    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getQuestion_text() {
        return question_text;
    }
    public void setQuestion_text(String questions_text) {
        this.question_text = questions_text;
    }
    public int getQuestion_type() {
        return question_type;
    }
    public void setQuestion_type(int question_type) {
        this.question_type = question_type;
    }
    public String getData1() {
        return data1;
    }
    public void setData1(String data1) {
        this.data1 = data1;
    }
    public String getData2() {
        return data2;
    }
    public void setData2(String data2) {
        this.data2 = data2;
    }
    public String getData3() {
        return data3;
    }
    public void setData3(String data3) {
        this.data3 = data3;
    }
    public String getData4() {
        return data4;
    }
    public void setData4(String data4) {
        this.data4 = data4;
    }
    public String getData5() {
        return data5;
    }
    public void setData5(String data5) {
        this.data5 = data5;
    }
    public double getMarks() {
        return marks;
    }
    public void setMarks(double marks) {
        this.marks = marks;
    }

    private DbConnection dc;

    public void createQuestion(){
        Connection connection = null;
        PreparedStatement statement = null;




        String sql = "INSERT INTO quiz_questions (question_id, teacher_id, subject, question_text, question_type, data1, data2, data3, data4, data5, marks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        dc = new DbConnection();
        try {
            connection = dc.Connect();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, question_id);
            statement.setString(2, teacher_id);
            statement.setString(3, subject);
            statement.setString(4, question_text);
            statement.setInt(5, question_type);
            statement.setString(6, data1);
            statement.setString(7, data2);
            statement.setString(8, data3);
            statement.setString(9, data4);
            statement.setString(10, data5);
            statement.setDouble(11, marks);

            System.out.println(statement.toString());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Question Created!");
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

    public void updateQuestion(){
        Connection connection = null;
        PreparedStatement statement = null;


        String sql = "UPDATE quiz_questions set question_id = ?, teacher_id = ?, subject = ?, question_text = ?, " +
                "question_type = ?, data1 = ?, data2 = ?, data3 = ?, data4 = ?, data5 = ?, marks = ? where " +
                "(subject = ? and question_id = ?)";
        dc = new DbConnection();
        try {
            connection = dc.Connect();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, question_id);
            statement.setString(2, teacher_id);
            statement.setString(3, subject);
            statement.setString(4, question_text);
            statement.setInt(5, question_type);
            statement.setString(6, data1);
            statement.setString(7, data2);
            statement.setString(8, data3);
            statement.setString(9, data4);
            statement.setString(10, data5);
            statement.setDouble(11, marks);
            statement.setString(12, subject);
            statement.setInt(13, question_id);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Question update!");
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


    public ArrayList<Questions> getQuestionsData(String question_id)  {
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null;

        Questions q = null;

        String[] id = question_id.split(",");



        ArrayList<Questions> aq = new ArrayList<Questions>();

        dc = new DbConnection();
        try {
            connection = dc.Connect();

            for (int i = 0 ; i < id.length; i++){
                String query = "SELECT * FROM quiz_questions WHERE question_id = ?" ;
                statement = connection.prepareStatement(query);
                statement.setString(1, id[i]);
                rs = statement.executeQuery();

                if (rs.next()) {
                    q = new Questions();
                    q.setQuestion_text(rs.getString("question_text"));
                    q.setSubject(rs.getString("subject"));
                    q.setQuestion_type(rs.getInt("question_type"));
                    q.setData1(rs.getString("data1"));
                    q.setData2(rs.getString("data2"));
                    q.setData3(rs.getString("data3"));
                    q.setData4(rs.getString("data4"));
                    q.setData5(rs.getString("data5"));
                    q.setMarks(rs.getDouble("marks"));

                    aq.add(q);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return aq;
    }

    public ArrayList<Questions> getQuestionsDataBySubject(String subject)  {
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null;

        Questions q = null;

        ArrayList<Questions> aq = new ArrayList<Questions>();

        dc = new DbConnection();
        try {
            connection = dc.Connect();

            String query = "SELECT * FROM quiz_questions WHERE subject = ?" ;
            statement = connection.prepareStatement(query);
            statement.setString(1, subject);
            rs = statement.executeQuery();

            while (rs.next()) {
                q = new Questions();
                q.setQuestion_text(rs.getString("question_text"));
                q.setSubject(rs.getString("subject"));
                q.setQuestion_type(rs.getInt("question_type"));
                q.setData1(rs.getString("data1"));
                q.setData2(rs.getString("data2"));
                q.setData3(rs.getString("data3"));
                q.setData4(rs.getString("data4"));
                q.setData5(rs.getString("data5"));
                q.setMarks(rs.getDouble("marks"));

                aq.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return aq;
    }

    public ArrayList<Questions> getPracticeQuestions()  {
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null;

        Questions q = null;

        ArrayList<Questions> aq = new ArrayList<Questions>();

        dc = new DbConnection();
        try {
            connection = dc.Connect();

            String query = "SELECT * FROM quiz_questions ORDER BY RAND() LIMIT 6" ;
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {
                q = new Questions();
                q.setQuestion_text(rs.getString("question_text"));
                q.setSubject(rs.getString("subject"));
                q.setQuestion_type(rs.getInt("question_type"));
                q.setData1(rs.getString("data1"));
                q.setData2(rs.getString("data2"));
                q.setData3(rs.getString("data3"));
                q.setData4(rs.getString("data4"));
                q.setData5(rs.getString("data5"));
                q.setMarks(rs.getDouble("marks"));

                aq.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return aq;
    }

    public void cleanupQuestions(String subject){
        dc = new DbConnection();
        try{
            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("SET @num := 0; UPDATE quiz_questions SET question_id = @num := (@num+1) where subject = '"+subject+"'");
        } catch (SQLException ex) {
            System.err.println("Error "+ex);
        }
    }

    public void deleteQuestion(String question_id, String quizN)  {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "DELETE FROM quiz_questions WHERE question_id = ? and subject = ?";
        dc = new DbConnection();
        try {
            connection = dc.Connect();
            statement = connection.prepareStatement(sql);
            statement.setString(1, question_id);
            statement.setString(2, quizN);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                cleanupQuestions(quizN);
                System.out.println("Question Deleted!");
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

    public void deleteQuestions(String quizN)  {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "DELETE FROM quiz_questions WHERE subject = ?";
        dc = new DbConnection();
        try {
            connection = dc.Connect();
            statement = connection.prepareStatement(sql);
            statement.setString(1, quizN);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                cleanupQuestions(quizN);
                System.out.println("Questions Deleted!");
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

    public void updateSubject(String subject1,String subject2)  {
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "update quiz_questions set subject = ? WHERE subject = ?";
        dc = new DbConnection();
        try {
            connection = dc.Connect();
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject1);
            statement.setString(2, subject2);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Question Subject updated!");
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
