import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.util.Map;

public class Scheduler extends Application{


    
    public static void main(String[] args) throws IOException {

        //Map<String, Course> courses = Course.fromCSV("courses.csv");
        //courses.get("CS108").show();
        
        //start JavaFX
        launch();
        
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("Scheduler");
        Map<String, Course> courses = Course.fromCSV("courses.csv");


        TextField text = new TextField();
        text.setText(courses.get("CS450").getInstructor());
        Button button = new Button("Save changes");
        Button print = new Button("Print course");
        Button save = new Button("Export to CSV");
        HBox hbox = new HBox(text, button, print, save);

        button.setOnAction(action -> {
            courses.get("CS450").setInstructor(text.getText());
        });
        
        print.setOnAction(action -> {
            courses.get("CS450").show();
        });

        save.setOnAction(action -> {
            Course.toCSV("courses.csv", courses.get("CS450"));
        });
       
        Scene scene = new Scene(hbox, 450, 350);
        stage.setScene(scene);
        stage.show();
    }
}
