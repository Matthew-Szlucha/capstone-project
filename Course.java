import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Course {
    public Course(String prefix, int num, String days, String timeslot, boolean isOnline, String instructor, String room){
        this.prefix = prefix;
        this.num = num;
        this.days = days;
        this.timeslot =  timeslot;
        this.isOnline = isOnline;
        this.instructor = instructor;
        this.room = room;
    }

    //create Course objects from CSV file and store them in a map
    public static Map<String, Course> fromCSV(String filename) {
        BufferedReader reader = null;

        //Create map of Courses with the name as the key
        Map<String, Course> map = new HashMap<String, Course>();
        try {
            reader = new BufferedReader(new FileReader(filename));

            String line = "";
            //skip header
            reader.readLine();
            //read from second line
            while ((line = reader.readLine()) != null) {
                String[] field = line.split(",");

                if (field.length > 0) {
                    //save courses in Course object and store that object in the map
                    Course course = new Course(field[0], Integer.parseInt(field[1]), field[2], field[3], Boolean.parseBoolean(field[4]), field[5], field[6]);
                    map.put(field[0] + field[1], course);
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
        return map;
    }

    //outputs Course to CSV
    public static void toCSV(String filename, Course course) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));

            writer.write("prefix,num,days,timeslot,isOnline,instructor,room");
            writer.newLine();
            writer.write(course.prefix + "," + Integer.toString(course.num) + "," + course.days + "," + course.timeslot + "," + Boolean.toString(course.isOnline) 
            + "," + course.instructor + "," + course.room);
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

    public void setInstructor(String newInstructor) {
        this.instructor = newInstructor;
    }

    public String getInstructor(){
        return this.instructor;
    }

    public void show() {
        System.out.println(prefix + num + " on " + days + " from " + timeslot + " is online: " + Boolean.toString(isOnline) + ", taught by " + instructor + " in room " + room);
    }
    
    private String prefix;
    private int num;
    private String days;
    private String timeslot;
    private boolean isOnline;
    private String instructor;
    private String room;
}
