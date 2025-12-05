/*
  Author: Jackson Lammons
*/

package data;

import model.Manager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ManagerService {

    private HashMap<Integer, Manager> managers = new HashMap<>();
    private final String FILE_NAME = "data/managers.csv";

    public ManagerService() {
        loadManagersFromCSV();
    }

    private void loadManagersFromCSV() {
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

                Manager m = new Manager(name, id, password);
                m.setContactInfo(phoneNumber, email);

                managers.put(id, m);
            }

        } catch (IOException e) {
            System.out.println("managers.csv not found or unreadable.");
        }
    }

    private void saveManagersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Manager m : managers.values()) {
                bw.write(
                    m.getID() + "," +
                    m.getName() + "," +
                    m.getPassword() + "," +
                    m.getPhoneNumber() + "," +
                    m.getEmail()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving managers.csv");
        }
    }

    public Manager getManager(int id) {
        return managers.get(id);
    }

    public void addManager(Manager m) {
        managers.put(m.getID(), m);
        saveManagersToCSV();
    }

    public void updateManager(Manager m) {
        managers.put(m.getID(), m);
        saveManagersToCSV();
    }

    public void removeManager(int id) {
        managers.remove(id);
        saveManagersToCSV();
    }

    public boolean managerExists(int id) {
        return managers.containsKey(id);
    }
}
