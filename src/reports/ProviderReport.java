//Author Jackson Lammons & James Zittlow
package reports;

import model.Member;
import model.Provider;
import model.Service;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProviderReport {

    private final String MEMBER_FILE = "data/members.csv";
    private final String PROVIDER_FILE = "data/providers.csv";
    private final String SERVICE_FILE = "data/services.csv";
    private final String SERVICE_RECORDS_FILE = "data/ServiceRecords.csv";
    private final String REPORT_FOLDER = "data/reports/";

    // GENERATE PROVIDER REPORT
    public void generateProviderReport(int providerID) {
        try {
            Map<Integer, Member> members = loadMembers();
            Map<Integer, Provider> providers = loadProviders();
            Map<Integer, Service> services = loadServices();
            List<String[]> records = loadServiceRecords();

            if (!providers.containsKey(providerID)) {
                System.out.println("Provider not found.");
                return;
            }

            Provider provider = providers.get(providerID);

            // Get services this provider performed
            List<String[]> providerServices = new ArrayList<>();
            for (String[] r : records) {
                int rProvider = Integer.parseInt(r[0]);
                if (rProvider == providerID) {
                    providerServices.add(r);
                }
            }

            if (providerServices.isEmpty()) {
                System.out.println("Provider has no services for this period.");
                return;
            }

            // Prepare report file
            LocalDate today = LocalDate.now();
            String dateStr = today.format(DateTimeFormatter.ofPattern("MM-dd-YYYY"));
            String fileName = REPORT_FOLDER + provider.getName().replace(" ", "_") + "_" + dateStr + ".txt";

            File folder = new File(REPORT_FOLDER);
            if (!folder.exists()) folder.mkdirs();

            PrintWriter out = new PrintWriter(new FileWriter(fileName));

            // HEADER 
            out.println("Provider Report");
            out.println("==============================");
            out.println("Provider Name: " + provider.getName());
            out.println("Provider Number: " + provider.getID());
            out.println("Address: " + provider.getAddress());
            out.println("City: " + provider.getCity());
            out.println("State: " + provider.getState());
            out.println("ZIP: " + provider.getZip());
            out.println();
            out.println("Services Provided:");
            out.println("------------------------------");

            int consultationCount = 0;
            double totalFees = 0;

            // BODY
            for (String[] r : providerServices) {
                int memberID = Integer.parseInt(r[1]);
                int serviceCode = Integer.parseInt(r[2]);
                String dateProvided = r[3];
                String dateRecorded = r[4];
                String comments = r[5];

                Member member = members.get(memberID);
                Service service = services.get(serviceCode);

                consultationCount++;
                if (service != null) {
                    totalFees += service.getFee();
                }

                out.println("Date of Service: " + dateProvided);
                out.println("Date/Time Received: " + dateRecorded);
                out.println("Member Name: " + (member != null ? member.getName() : "UNKNOWN"));
                out.println("Member Number: " + memberID);
                out.println("Service Code: " + serviceCode);
                out.println("Fee: $" + (service != null ? service.getFee() : 0));
                out.println("Comments: " + comments);
                out.println();
            }

            //FOOTER
            out.println("------------------------------");
            out.println("Total Consultations: " + consultationCount);
            out.println("Total Fee for Week: $" + totalFees);

            out.close();

            System.out.println("Provider report created: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // LOAD MEMBERS
    private Map<Integer, Member> loadMembers() throws IOException {
        Map<Integer, Member> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE));

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

            Member m = new Member(name, id, password, suspended);
            m.setAddress(address, city, state, zip);
            map.put(id, m);
        }
        br.close();
        return map;
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

            Provider provider = new Provider(name, id, password, false);
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

            map.put(code, new Service(name, code, fee));
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
            String[] p = line.split(",");
            if (p.length != 6) continue;
            list.add(p);
        }
        br.close();
        return list;
    }
}
