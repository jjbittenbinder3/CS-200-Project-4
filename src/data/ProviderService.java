/*
  Author: Jackson Lammons
*/

package data;

import model.Provider;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ProviderService {

    private HashMap<Integer, Member> providers = new HashMap<>();
    private final String FILE_NAME = "data/providers.csv";

    public ProviderService() {
        loadProvidersFromCSV();
    }

    private void loadProvidersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

                if (p.length != 8) continue;

                int id = Integer.parseInt(p[0]);
                String name = p[1];
                String password = p[2];
                String address = p[3];
                String city = p[4];
                String state = p[5];
                String zip = p[6];
                boolean suspended = Boolean.parseBoolean(p[7]);

                Provider p = new Provider(name, id, password, suspended);
                m.setAddress(address, city, state, zip);

                providers.put(id, m);
            }

        } catch (IOException e) {
            System.out.println("providers.csv not found or unreadable.");
        }
    }

    private void saveProvidersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Provider p : providers.values()) {
                bw.write(
                    p.getID() + "," +
                    p.getName() + "," +
                    p.getPassword() + "," +
                    p.getAddress() + "," +
                    p.getCity() + "," +
                    p.getState() + "," +
                    p.getZip() + "," +
                    p.getSuspended()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving providers.csv");
        }
    }

    public Provider getProviders(int id) {
        return providers.get(id);
    }

    public void addProvider(Provider p) {
        providers.put(p.getID(), p);
        saveProvidersToCSV();
    }

    public void updateProvider(Provider p) {
        providers.put(p.getID(), p);
        saveProvidersToCSV();
    }

    public void removeProvider(int id) {
        providers.remove(id);
        saveProvidersToCSV();
    }

    public boolean providerExists(int id) {
        return providers.containsKey(id);
    }
}
