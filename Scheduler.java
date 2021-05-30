import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.io.IOException;
//import java.util.Map;
import javafx.collections.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Scheduler extends Application{

    public static ObservableList listFromCSV(String filename){
        BufferedReader reader = null;
        ObservableList list = FXCollections.<Object>observableArrayList();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = "";
            line = reader.readLine();
            String[] field = line.split(",");
            for(int i = 0; i < field.length; i++){
                list.add(field[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ie) {
                System.out.println("Error closing the BufferedReader");
                ie.printStackTrace();
            }
        }
        return list;
    }
    
    public static void main(String[] args) throws IOException {
        
        //start JavaFX
        launch();
        
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("Scheduler");
        ObservableList<Course> courses = Course.fromCSV("courses.csv");
        String onlineString[] = {"true", "false"};

        Button save = new Button("Export to CSV");
        Button add = new Button("Add new course");
        TextField prefixField = new TextField("Prefix");
        TextField numField = new TextField("Course Number");
        TextField daysField = new TextField("Days");
        TextField timeField = new TextField("Timeslot");
        ComboBox<String> onlineBox = new ComboBox<String>(FXCollections.observableArrayList(onlineString));
        ComboBox<String> instructorBox = new ComboBox<String>(listFromCSV("instructors.csv"));
        TextField roomField = new TextField("Room");
        HBox textbox = new HBox(prefixField, numField, daysField, timeField, onlineBox, instructorBox, roomField, add);
        TableView<Course> table = new TableView<>();
        VBox vbox = new VBox(table, save, textbox);
        vbox.setSpacing(5);
        
        table.setEditable(true);
       
        TableColumn<Course, String> prefix = new TableColumn<>("Prefix");
        prefix.setCellValueFactory(new PropertyValueFactory<>("prefix"));
        prefix.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        table.getColumns().add(prefix);
        
        TableColumn<Course, String> num = new TableColumn<>("Number");
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        num.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
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

        save.setOnAction(action -> {
            Course.toCSV("courses.csv", courses);
        });

        add.setOnAction(action -> {
            table.getItems().add(new Course(
                prefixField.getText(), numField.getText(), daysField.getText(), timeField.getText(), 
                Boolean.parseBoolean(onlineBox.getValue()), instructorBox.getValue(), roomField.getText()));
            courses.add(new Course(
                prefixField.getText(), numField.getText(), daysField.getText(), timeField.getText(), 
                Boolean.parseBoolean(onlineBox.getValue()), instructorBox.getValue(), roomField.getText()));
        });
       
        Scene scene = new Scene(vbox, 999, 300);
        stage.setScene(scene);
        stage.show();
    }
}
