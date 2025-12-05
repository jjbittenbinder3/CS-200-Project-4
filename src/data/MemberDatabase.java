/*
  Author: James Pepper
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

package data;

public class MemberDatabase() {

  public MemberDatabase() {
    try(BufferedReader read = new BufferedReader(new FileReader(csvFile))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] fields = line.split(",");
        for (String f : fields)
          System.out.println(f);
        }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}



public class CSVReaderSimple {
    public static void main(String[] args) {
        String csvFile = "data/file.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            

        
            
        }
    }
