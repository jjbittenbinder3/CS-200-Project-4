// Author: Jackson Lammons
package reports;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EFTReport {

    private static final String SERVICE_RECORDS_FILE = "data/ServiceRecords.csv";
    private static final String SERVICES_FILE = "data/services.csv";
    private static final String PROVIDERS_FILE = "data/providers.csv";

    public void generateEFTReport() {

        Map<Integer, Double> providerTotals = new HashMap<>();
        Map<Integer, String> providerNames = loadProviderNames();
        Map<Integer, Double> serviceFees = loadServiceFees();

        try (BufferedReader br = new BufferedReader(new FileReader(SERVICE_RECORDS_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(","); // tab-separated
                if (p.length != 6) continue;

                int providerID = Integer.parseInt(p[0]);
                int serviceCode = Integer.parseInt(p[2]);

                double fee = serviceFees.getOrDefault(serviceCode, 0.0);

                providerTotals.put(providerID,
                        providerTotals.getOrDefault(providerID, 0.0) + fee);
            }

        } catch (IOException e) {
            System.out.println("Error reading ServiceRecords.csv");
            return;
        }

        // Write report
        LocalDate today = LocalDate.now();
        String dateString = today.format(DateTimeFormatter.ofPattern("MM-dd-YYYY"));
        String filename = "data/reports/EFT_" + dateString + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            bw.write("===== Electronic Funds Transfer Report =====\n\n");

            double grandTotal = 0;
            int providerCount = 0;

            for (var entry : providerTotals.entrySet()) {
                int providerID = entry.getKey();
                double amount = entry.getValue();
                grandTotal += amount;
                providerCount++;

                bw.write("Provider Name: " + providerNames.getOrDefault(providerID, "UNKNOWN") + "\n");
                bw.write("Provider Number: " + providerID + "\n");
                bw.write("Total Amount to Transfer: $" + String.format("%.2f", amount) + "\n\n");
            }

            bw.write("=============================================\n");
            bw.write("Total Providers Paid: " + providerCount + "\n");
            bw.write("Overall Amount: $" + String.format("%.2f", grandTotal) + "\n");

            System.out.println("EFT Report written to: " + filename);

        } catch (IOException e) {
            System.out.println("Error writing EFT report.");
        }
    }

    private Map<Integer, Double> loadServiceFees() {
        Map<Integer, Double> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(SERVICES_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 3) {
                    int code = Integer.parseInt(p[0]);
                    double fee = Double.parseDouble(p[2]);
                    map.put(code, fee);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading services.csv");
        }

        return map;
    }

    private Map<Integer, String> loadProviderNames() {
        Map<Integer, String> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PROVIDERS_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 2) {
                    int id = Integer.parseInt(p[0]);
                    map.put(id, p[1]);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading providers.csv");
        }

        return map;
    }
}
