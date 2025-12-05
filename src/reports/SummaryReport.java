//Author: Jackson Lammons
package reports;

import model.Member;
import model.Provider;
import model.Service;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SummaryReport {

    private final String MEMBER_FILE = "data/members.csv";
    private final String PROVIDER_FILE = "data/providers.csv";
    private final String SERVICE_FILE = "data/services.csv";
    private final String SERVICE_RECORDS_FILE = "data/ServiceRecords.csv";
    private final String REPORT_FOLDER = "data/reports/";
    // PUBLIC: GENERATE SUMMARY REPORT
    public void generateSummaryReport() {
        try {
            Map<Integer, Provider> providers = loadProviders();
            Map<Integer, Service> services = loadServices();
            List<String[]> records = loadServiceRecords();

            // providerID → number of consultations
            Map<Integer, Integer> consultationCount = new HashMap<>();

            // providerID → total fees
            Map<Integer, Double> feeTotals = new HashMap<>();

            // Process each service record
            for (String[] r : records) {
                int providerID = Integer.parseInt(r[0]);
                int serviceCode = Integer.parseInt(r[2]);

                consultationCount.put(providerID,
                    consultationCount.getOrDefault(providerID, 0) + 1);

                Service service = services.get(serviceCode);
                double fee = (service != null) ? service.getFee() : 0;

                feeTotals.put(providerID,
                    feeTotals.getOrDefault(providerID, 0.0) + fee);
            }

            // Prepare file
            LocalDate today = LocalDate.now();
            String dateStr = today.format(DateTimeFormatter.ofPattern("MM-dd-YYYY"));
            String fileName = REPORT_FOLDER + "SummaryReport_" + dateStr + ".txt";

            File folder = new File(REPORT_FOLDER);
            if (!folder.exists()) folder.mkdirs();

            PrintWriter out = new PrintWriter(new FileWriter(fileName));

            out.println("ChocAn Summary Report");
            out.println("=====================================");
            out.println("Generated: " + dateStr);
            out.println();

            int totalProviders = 0;
            int totalConsultations = 0;
            double grandTotalFees = 0;

            // Write provider-by-provider breakdown
            for (Integer providerID : consultationCount.keySet()) {
                Provider p = providers.get(providerID);
                int count = consultationCount.get(providerID);
                double total = feeTotals.get(providerID);

                if (p != null) {
                    out.println("Provider Name: " + p.getName());
                    out.println("Provider Number: " + providerID);
                } else {
                    out.println("Provider Name: UNKNOWN");
                    out.println("Provider Number: " + providerID);
                }

                out.println("Consultations: " + count);
                out.println("Total Fees: $" + total);
                out.println("-------------------------------------");

                totalProviders++;
                totalConsultations += count;
                grandTotalFees += total;
            }

            // Overall totals
            out.println();
            out.println("========== OVERALL TOTALS ==========");
            out.println("Total Providers: " + totalProviders);
            out.println("Total Consultations: " + totalConsultations);
            out.println("Total Fees: $" + grandTotalFees);
            out.println("=====================================");

            out.close();
            System.out.println("Summary report created: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // LOAD PROVIDERS
    private Map<Integer, Provider> loadProviders() throws IOException {
        Map<Integer, Provider> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(PROVIDER_FILE));

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

            Provider provider = new Provider(name, id, password);
            provider.setAddress(address, city, state, zip);

            map.put(id, provider);
        }
        br.close();
        return map;
    }
    // LOAD SERVICES
    private Map<Integer, Service> loadServices() throws IOException {
        Map<Integer, Service> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(SERVICE_FILE));

        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split(",");
            if (p.length != 3) continue;

            int code = Integer.parseInt(p[0]);
            String name = p[1];
            double fee = Double.parseDouble(p[2]);

            map.put(code, new Service(code, name, fee));
        }
        br.close();
        return map;
    }
    // LOAD SERVICE RECORDS (TAB SEPARATED)
    private List<String[]> loadServiceRecords() throws IOException {
        List<String[]> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(SERVICE_RECORDS_FILE));

        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split("\\t");
            if (p.length != 6) continue;
            list.add(p);
        }
        br.close();
        return list;
    }
}
