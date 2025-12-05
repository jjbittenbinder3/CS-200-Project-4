// Author: Jackson Lammons
package reports;

import model.Member;
import model.Provider;
import model.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MemberReport {

    private final String MEMBER_FILE = "data/members.csv";
    private final String PROVIDER_FILE = "data/providers.csv";
    private final String SERVICE_FILE = "data/services.csv";
    private final String SERVICE_RECORDS_FILE = "data/ServiceRecords.csv";
    private final String REPORT_FOLDER = "data/reports/";

    // PUBLIC METHOD TO GENERATE REPORT
    public void generateMemberReport(int memberID) {
        try {
            Map<Integer, Member> members = loadMembers();
            Map<Integer, Provider> providers = loadProviders();
            Map<Integer, Service> services = loadServices();
            List<String[]> records = loadServiceRecords();

            if (!members.containsKey(memberID)) {
                System.out.println("Member not found.");
                return;
            }

            Member member = members.get(memberID);

            // Filter all records for this member
            List<String[]> memberServices = new ArrayList<>();
            for (String[] r : records) {
                int rMemberID = Integer.parseInt(r[1]);
                if (rMemberID == memberID) {
                    memberServices.add(r);
                }
            }

            if (memberServices.isEmpty()) {
                System.out.println("No services found for this member.");
                return;
            }

            // Prepare file
            LocalDate today = LocalDate.now();
            String dateStr = today.format(DateTimeFormatter.ofPattern("MM-dd-YYYY"));
            String fileName = REPORT_FOLDER + member.getName().replace(" ", "_") + "_" + dateStr + ".txt";

            File folder = new File(REPORT_FOLDER);
            if (!folder.exists()) folder.mkdirs();

            try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {

                // HEADER
                out.println("Member Report");
                out.println("==============================");
                out.println("Name: " + member.getName());
                out.println("Member Number: " + member.getID());
                out.println("Address: " + member.getAddress());
                out.println("City: " + member.getCity());
                out.println("State: " + member.getState());
                out.println("ZIP: " + member.getZip());
                out.println();
                out.println("Services Received:");
                out.println("------------------------------");

                // BODY
                for (String[] r : memberServices) {
                    int providerID = Integer.parseInt(r[0]);
                    int serviceCode = Integer.parseInt(r[2]);
                    String dateProvided = r[3];

                    Provider provider = providers.get(providerID);
                    Service service = services.get(serviceCode);

                    out.println("Date of Service: " + dateProvided);
                    out.println("Provider Name: " + (provider != null ? provider.getName() : "UNKNOWN"));
                    out.println("Service Name: " + (service != null ? service.getName() : "UNKNOWN"));
                    out.println();
                }
            }

            System.out.println("Member report created: " + fileName);

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
    // LOAD SERVICE RECORDS
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

