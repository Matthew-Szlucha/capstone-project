import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Scheduler extends Application{
    @Override
    public void start(Stage stage){
        stage.setTitle("Scheduler Tool");


        final ComboBox testBox = new ComboBox();
        testBox.getItems().addAll("Test Value", "Second Test Value", "Third Test Value");
       
        Scene scene = new Scene(testBox, 450, 350);
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) throws IOException {

        //get course from CSV
        Course testCourse = Course.fromCSV("courses.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        testCourse.setPrefix(reader.readLine());
        Course.toCSV("courses.csv", testCourse);
        testCourse.show();
        
        //start JavaFX
        launch(args);
    }
}
