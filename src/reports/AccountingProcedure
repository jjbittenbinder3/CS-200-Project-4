//Author: Jackson Lammons
package reports;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AccountingProcedure {

    private static final String MEMBER_FILE = "data/members.csv";
    private static final String PROVIDER_FILE = "data/providers.csv";

    private MemberReport memberReport = new MemberReport();
    private ProviderReport providerReport = new ProviderReport();
    private SummaryReport summaryReport = new SummaryReport();
    private EFTReport eftReport = new EFTReport();

    // RUNS ALL REQUIRED REPORTS
    public void runWeeklyProcedure() {

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-YYYY"));

        System.out.println("\n===== RUNNING MAIN ACCOUNTING PROCEDURE =====");
        System.out.println("Date: " + date);
        System.out.println("Generating Member Reports...");
        runMemberReports(date);

        System.out.println("Generating Provider Reports...");
        runProviderReports(date);

        System.out.println("Generating Summary Report...");
        summaryReport.generateSummaryReport();

        System.out.println("Generating EFT Report...");
        eftReport.generateEFTReport(date);

        System.out.println("===== ACCOUNTING PROCEDURE COMPLETE =====\n");
    }
    // GENERATE MEMBER REPORT FOR ALL MEMBERS WITH ACTIVITY
    private void runMemberReports(String date) {
        Set<Integer> memberIDs = getAllMemberIDs();

        for (int id : memberIDs) {
            memberReport.generateMemberReport(id);
        }
    }
    // GENERATE PROVIDER REPORT FOR ALL PROVIDERS WITH ACTIVITY
    private void runProviderReports(String date) {
        Set<Integer> providerIDs = getAllProviderIDs();

        for (int id : providerIDs) {
            providerReport.generateProviderReport(id);
        }
    }

    // LOAD MEMBER IDs FROM CSV
    private Set<Integer> getAllMemberIDs() {
        Set<Integer> set = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length > 1) {
                    int id = Integer.parseInt(p[0].trim());
                    set.add(id);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading member list.");
        }

        return set;
    }

    // LOAD PROVIDER IDs FROM CSV
    private Set<Integer> getAllProviderIDs() {
        Set<Integer> set = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PROVIDER_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length > 1) {
                    int id = Integer.parseInt(p[0].trim());
                    set.add(id);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading provider list.");
        }

        return set;
    }
}
