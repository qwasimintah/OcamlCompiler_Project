import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;

public class Outgesture {
  public Outgesture(String fileout){
    try {
        System.setOut(new PrintStream(new File(fileout)));
    } catch (Exception e) {
         e.printStackTrace();
    }
  }
}
