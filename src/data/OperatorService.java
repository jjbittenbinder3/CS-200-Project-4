/*
  Author: Jackson Lammons
*/

package data;

import model.Operator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class OperatorService {

    private HashMap<Integer, Operator> operators = new HashMap<>();
    private final String FILE_NAME = "data/operators.csv";

    public OperatorService() {
        loadOperatorsFromCSV();
    }

    private void loadOperatorsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");

                if (s.length != 8) continue;

                int id = Integer.parseInt(s[0]);
                String name = s[1];
                String password = s[2];
                String phoneNumber = s[3];
                String email = s[4];

                Operator o = new Operator(name, id, password);
                o.setContactInfo(phoneNumber, email);

                operators.put(id, o);
            }

        } catch (IOException e) {
            System.out.println("operators.csv not found or unreadable.");
        }
    }

    private void saveOperatorsToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Operator o : operators.values()) {
                bw.write(
                    o.getID() + "," +
                    o.getName() + "," +
                    o.getPassword() + "," +
                    o.getPhoneNumber() + "," +
                    o.getEmail()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving managers.csv");
        }
    }

    public Operator getOperator(int id) {
        return operators.get(id);
    }

    public void addOperator(Operator o) {
        operators.put(o.getID(), o);
        saveOperatorsToCSV();
    }

    public void updateOperator(Operator o) {
        operators.put(o.getID(), o);
        saveOperatorsToCSV();
    }

    public void removeOperator(int id) {
        operators.remove(id);
        saveOperatorsToCSV();
    }

    public boolean operatorExists(int id) {
        return operators.containsKey(id);
    }
}
