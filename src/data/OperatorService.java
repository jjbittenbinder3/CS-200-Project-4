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

    private HashMap<Integer, Member> operators = new HashMap<>();
    private final String FILE_NAME = "data/operators.csv";

    public OperatorService() {
        loadOperatorsFromCSV();
    }

    private void loadOperatorsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] o = line.split(",");

                if (o.length != 8) continue;

                int id = Integer.parseInt(o[0]);
                String name = o[1];
                String password = o[2];
                String address = o[3];
                String city = o[4];
                String state = o[5];
                String zio = o[6];
                boolean suspended = Boolean.parseBoolean(o[7]);

                Operator o = new Operator(name, id, password, suspended);
                m.setAddress(address, city, state, zio);

                operators.put(id, m);
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
                    o.getAddress() + "," +
                    o.getCity() + "," +
                    o.getState() + "," +
                    o.getZip() + "," +
                    o.getSuspended()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving operators.csv");
        }
    }

    public Operator getOperators(int id) {
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
