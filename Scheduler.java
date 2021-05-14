import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import java.io.IOException;
//import java.util.Map;
import javafx.collections.*;

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
        //Map<Integer, Course> courses = Course.fromCSV("courses.csv");
        ObservableList<Course> courses = Course.fromCSV("courses.csv");


        Button button = new Button("Save changes");
        Button print = new Button("Print course");
        Button save = new Button("Export to CSV");
        TableView<Course> table = new TableView<>();
        HBox hbox = new HBox(table, button, print, save);
        
        table.setEditable(true);
       
        TableColumn<Course, String> prefix = new TableColumn<>("Prefix");
        prefix.setCellValueFactory(new PropertyValueFactory<>("prefix"));
        prefix.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        table.getColumns().add(prefix);
        
        TableColumn<Course, Integer> num = new TableColumn<>("Number");
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        table.getColumns().add(num);

        TableColumn<Course, String> days = new TableColumn<>("Day");
       days.setCellValueFactory(new PropertyValueFactory<>("days"));
       days.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
       table.getColumns().add(days);

        TableColumn<Course, String> timeslot = new TableColumn<>("Time");
        timeslot.setCellValueFactory(new PropertyValueFactory<>("timeslot"));
        timeslot.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        table.getColumns().add(timeslot);

        TableColumn<Course, Boolean> isOnline = new TableColumn<>("Online");
        isOnline.setCellValueFactory(new PropertyValueFactory<>("isOnline"));
        table.getColumns().add(isOnline);

        TableColumn<Course, String> instructor = new TableColumn<>("Instructor");
        instructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        instructor.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        table.getColumns().add(instructor);

        TableColumn<Course, String> room = new TableColumn<>("Room");
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        room.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        table.getColumns().add(room);

        for(int i = 0; i < courses.size(); i++) {
            table.getItems().add(courses.get(i));
        }


        //button.setOnAction(action -> {
           // courses.get("CS450").setInstructor(text.getText());
        //});
        
        //print.setOnAction(action -> {
            //courses.get("CS450").show();
        //});

        save.setOnAction(action -> {
            Course.toCSV("courses.csv", courses);
        });
       
        Scene scene = new Scene(hbox, 825, 350);
        stage.setScene(scene);
        stage.show();
    }
}
