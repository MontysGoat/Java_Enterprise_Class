

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlatFileParser {

  public List<Student> Parse(String fileName) throws FileNotFoundException {
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    List<Student> retVal = new ArrayList<>();
    while(scanner.hasNextLine()){
      String currentLine = scanner.nextLine();
      String [] tokens = currentLine.split(" ");
      retVal.add(new Student(tokens[0],
                             tokens[1],
                             tokens[2],
                             tokens[3],
                             tokens[4],
                             tokens[5],
                             tokens[6])
                             );
    }
    scanner.close();
    return retVal;
  }
}