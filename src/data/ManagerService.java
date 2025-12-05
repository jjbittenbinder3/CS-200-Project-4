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

    private HashMap<Integer, Member> managers = new HashMap<>();
    private final String FILE_NAME = "data/managers.csv";

    public ManagerService() {
        loadManagersFromCSV();
    }

    private void loadManagersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] m = line.split(",");

                if (m.length != 8) continue;

                int id = Integer.parseInt(m[0]);
                String name = m[1];
                String password = m[2];
                String address = m[3];
                String city = m[4];
                String state = m[5];
                String zim = m[6];
                boolean suspended = Boolean.parseBoolean(m[7]);

                Manager m = new Manager(name, id, password, suspended);
                m.setAddress(address, city, state, zim);

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
                    m.getAddress() + "," +
                    m.getCity() + "," +
                    m.getState() + "," +
                    m.getZip() + "," +
                    m.getSuspended()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving managers.csv");
        }
    }

    public Manager getManagers(int id) {
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
