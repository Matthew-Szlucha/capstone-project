import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Map;
//import java.util.HashMap;
import javafx.collections.*;

public class Course {
    public Course(String prefix, String num, String days, String timeslot, boolean isOnline, String instructor, String room){
        this.prefix = prefix;
        this.num = num;
        this.days = days;
        this.timeslot =  timeslot;
        this.isOnline = isOnline;
        this.instructor = instructor;
        this.room = room;
    }

    //create Course objects from CSV file and store them in a list
    public static ObservableList<Course> fromCSV(String filename) {
        BufferedReader reader = null;

        //Create map of Courses with the name as the key
        ObservableList<Course> list = FXCollections.<Course>observableArrayList();
        //Map<Integer, Course> map = new HashMap<Integer, Course>();
        try {
            reader = new BufferedReader(new FileReader(filename));

            String line = "";
            //skip header
            reader.readLine();
            //read from second line
            while ((line = reader.readLine()) != null) {
                String[] field = line.split(",");
                //int i = 0;

                if (field.length > 0) {
                    //save courses in Course object and store that object in the map
                    Course course = new Course(field[0], field[1], field[2], field[3], Boolean.parseBoolean(field[4]), field[5], field[6]);
                    //map.put(i, course);
                    list.add(course);
                }
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
    
    //outputs Course to CSV
    public static void toCSV(String filename, ObservableList<Course> courses) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));

            writer.write("prefix,num,days,timeslot,isOnline,instructor,room");
            
            writer.newLine();
            
            for(int i = 0; i < courses.size(); i++){
            writer.write(courses.get(i).getPrefix() + "," + courses.get(i).getNum() + "," + courses.get(i).getDays() + "," + 
            courses.get(i).getTimeslot() + "," + Boolean.toString(courses.get(i).getIsOnline()) + "," + courses.get(i).getInstructor() + "," + courses.get(i).getRoom());
            writer.newLine();
            }
            writer.close();
        
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException ie) {
                System.err.println("Error closing BufferedWriter");
                ie.printStackTrace();
            }
        }
    }

    public void setPrefix(String newPrefix) {
        this.prefix = newPrefix;
    }

    public String getPrefix(){
        return this.prefix;
    }
    
    public void setNum(String newNum) {
        this.num = newNum;
    }

    public String getNum(){
        return this.num;
    }
    
    public void setDays(String newDays) {
        this.days = newDays;
    }

    public String getDays(){
        return this.days;
    }
    
    public void setTimeslot(String newTimeslot) {
        this.timeslot = newTimeslot;
    }

    public String getTimeslot(){
        return this.timeslot;
    }
    
    public void setIsOnline(Boolean newIsOnline) {
        this.isOnline = newIsOnline;
    }

    public Boolean getIsOnline(){
        return this.isOnline;
    }
    
    public void setInstructor(String newInstructor) {
        this.instructor = newInstructor;
    }

    public String getInstructor(){
        return this.instructor;
    }

    public void setRoom(String newRoom) {
        this.room = newRoom;
    }

    public String getRoom(){
        return this.room;
    }

    public void show() {
        System.out.println(prefix + num + " on " + days + " from " + timeslot + " is online: " + Boolean.toString(isOnline) + ", taught by " + instructor + " in room " + room);
    }
    
    private String prefix;
    private String num;
    private String days;
    private String timeslot;
    private boolean isOnline;
    private String instructor;
    private String room;
}
