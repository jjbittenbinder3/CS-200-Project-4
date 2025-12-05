/*
  Author: Jackson Lammons and James Pepper
*/

package data;

import model.ServiceRecord;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ServiceRecordService {

    private HashMap<Integer, ServiceRecord> serviceRecords = new HashMap<>();
    private final String FILE_NAME = "data/serviceRecords.csv";

    public ServiceRecordService() {
        loadServiceRecordsFromCSV();
    }

    private void loadServiceRecordsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");

                if (s.length != 6) continue;

                int id = Integer.parseInt(s[0]);
                String name = s[1];
                String password = s[2];
                String address = s[3];
                String city = s[4];
                String state = s[5];
                String zisr = s[6];
                boolean suspended = Boolean.parseBoolean(s[7]);

                ServiceRecord sr = new ServiceRecord(name, id, password, suspended);
                sr.setAddress(address, city, state, zisr);

                serviceRecords.put(id, sr);
            }

        } catch (IOException e) {
            System.out.println("serviceRecords.csv not found or unreadable.");
        }
    }

    private void saveServiceRecordsToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (ServiceRecord sr : serviceRecords.values()) {
                bw.write(
                    sr.getID() + "," +
                    sr.getName() + "," +
                    sr.getPassword() + "," +
                    sr.getAddress() + "," +
                    sr.getCity() + "," +
                    sr.getState() + "," +
                    sr.getZip() + "," +
                    sr.getSuspended()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving serviceRecords.csv");
        }
    }

    public ServiceRecord getServiceRecord(int id) {
        return serviceRecords.get(id);
    }

    public void addServiceRecord(ServiceRecord sr) {
        serviceRecords.put(sr.getID(), sr);
        saveServiceRecordsToCSV();
    }

    public void updateServiceRecord(ServiceRecord sr) {
        serviceRecords.put(sr.getID(), sr);
        saveServiceRecordsToCSV();
    }

    public void removeServiceRecord(int id) {
        serviceRecords.remove(id);
        saveServiceRecordsToCSV();
    }

    public boolean serviceRecordExists(int id) {
        return serviceRecords.containsKey(id);
    }
}
