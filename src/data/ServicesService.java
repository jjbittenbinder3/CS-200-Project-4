/*
  Author: James Pepper
*/

package data;

import model.Service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class ServicesService {

    private Vector<Service> services = new Vector<Service>();
    private final String FILE_NAME = "data/services.csv";

    public ServicesService() {
        loadServicesfromCSV();
    }

    private void loadServicesfromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");

                if (s.length != 3) continue;
              
                String code = s[0].trim();
                String name = s[1].trim();
                String fee = s[2].trim();

                Service sv = new Service(name, Integer.parseInt(code), Double.parseDouble(fee));

                services.add(sv);
            }

        } catch (IOException e) {
            System.out.println("services.csv not found or unreadable.");
        }
    }

    private void saveServicesToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Service sv : services) {
                bw.write(
                    sv.getCode() + "," +
                    sv.getName() + "," +
                    sv.getFee()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving services.csv");
        }
    }

    public void addService(Service sv) {
        services.add(sv);
        saveServicesToCSV();
    }

    public Service getService(int code) {
        for (Service sv : services) {
            if (sv.getCode() == code) {
                return sv;
            }
        }
        return null;
    }
}
