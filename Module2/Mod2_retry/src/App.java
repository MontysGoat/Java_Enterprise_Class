

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Module 1 Project started");
        System.out.println();
        ClassPathChecker.CheckClassPath();
        if (false) {
            FlatFileParser parser = new FlatFileParser();
            List<Student> students = new ArrayList<>();
            try {
                students = parser.Parse("data.txt");
            } catch (FileNotFoundException ex) {
                System.out.println("Failed to load data file. Program exiting.");
            }

            DBUpdater dbUpdater = new DBUpdater();
            dbUpdater.UpdateStudents(students);

            DBPrinter dbPrinter = new DBPrinter();
            try {
                dbPrinter.PrintStudentsForwardAndBackward();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        JNDIPrinter jndiPrinter = new JNDIPrinter();
        try {
            jndiPrinter.AddStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
