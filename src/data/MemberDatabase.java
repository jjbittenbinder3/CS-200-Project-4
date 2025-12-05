/*
  Author: James Pepper
*/

package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MemberDatabase() {

  public MemberDatabase() {
    try(BufferedReader read = new BufferedReader(new FileReader("src/data/members.csv"))) {
      String line;
      while ((line = read.readLine()) != null) {
        String[] fields = line.split(",");
        for (String f : fields)
          System.out.println(f);
        }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
