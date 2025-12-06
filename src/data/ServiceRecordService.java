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
import java.util.Vector;

public class ServiceRecordService {

    private Vector<ServiceRecord> serviceRecords = new Vector<ServiceRecord>();
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
              
                String provID = s[0];
                String memID = s[1];
                String servCode = s[2];
                String servDate = s[3];
                String billDate = s[4];
                String comments = s[5];

                ServiceRecord sr = new ServiceRecord(servDate, provID, memID, Integer.parseInt(servCode), comments);
                sr.setDateBilled(billDate);

                serviceRecords.add(sr);
            }

        } catch (IOException e) {
            System.out.println("serviceRecords.csv not found or unreadable.");
        }
    }

    private void saveServiceRecordsToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (ServiceRecord sr : serviceRecords) {
                bw.write(
                    sr.getProvider() + "," +
                    sr.getMember() + "," +
                    sr.getService() + "," +
                    sr.getDateProvided() + "," +
                    sr.getDateBilled() + "," +
                    sr.getComments()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving serviceRecords.csv");
        }
    }

    public void addServiceRecord(ServiceRecord sr) {
        serviceRecords.add(sr);
        saveServiceRecordsToCSV();
    }
}
