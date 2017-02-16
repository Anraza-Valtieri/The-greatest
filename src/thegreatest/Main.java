package thegreatest;

// JAVAFX
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws IOException {
//		Parent root = FXMLLoader.load(getClass().getResource("/View/mainPage.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        root.getStylesheets().add("View/application.css");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Learn Java Program");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*public static void main(String args[]) {
        int loginType = 0; // 0 Student 1 Teacher 2 Admin
        boolean loginSuccess = false;

        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your ID: ");
        String uniID = sc.nextLine();
        System.out.print("Please enter your password ");
        String pw = sc.nextLine();

        Account login = new Account();
        login.setUniqID(uniID);
        login.setPassword(pw);

        loginSuccess = login.getLogin(uniID, pw);
        System.out.println(loginSuccess);
        loginType = login.getType();

        while(loginSuccess == true){
            if(loginType == 0){ // Student

            }

            if(loginType == 1){ // Teacher

            }

            if(loginType == 2){ // Admin

            }
        }
    }*/

    public void testteacherQuiz(){

    }

    public void teststudentQuiz(){

    }
}
