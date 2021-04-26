import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

    
    public static void main(String[] args){

        //get course from CSV
        Course testCourse = Course.fromCSV("courses.csv");
        testCourse.show();
        
        //start JavaFX
        launch();
    }
}
