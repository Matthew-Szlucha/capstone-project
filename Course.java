import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    //read contents of new Course object from CSV file
    public static Course fromCSV(String filename){
        BufferedReader br = null;
        Course course = null;
        
        //dealing with files can be messy, catch an exception if anything goes wrong
        try {
            br = new BufferedReader(new FileReader(filename));

            String line = "";
            //skip header
            br.readLine();
            //read from second line
            while((line = br.readLine()) != null){
                String[] field = line.split(",");
                if(field.length > 0){
                    course = new Course(field[0], Integer.parseInt(field[1]), field[2], field[3], Boolean.parseBoolean(field[4]), field[5], field[6]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ie) {
                System.err.println("Error closing BufferedReader");
                ie.printStackTrace();
            }
        }
        return course;
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
