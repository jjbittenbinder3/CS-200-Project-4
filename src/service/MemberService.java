//Author: Jackson Lammons
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class MemberService {

    private HashMap<String, Member> members = new HashMap<>();
    private final String FILE_NAME = "members.csv";

    public MemberService() {
        loadMembersFromCSV();
    }
    // LOAD MEMBERS FROM CSV
    private void loadMembersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

                if (p.length != 10) continue; // skip bad rows

                Member m = new Member(
                    p[0],   // ID
                    p[1],   // Name
                    p[2],   // Street
                    p[3],   // City
                    p[4],   // State
                    p[5],   // ZIP
                    p[6],   // Email
                    p[7],   // Phone
                    p[8],   // Status
                    Double.parseDouble(p[9]) // Balance
                );

                members.put(p[0], m);
            }

        } catch (IOException e) {
            System.out.println("Could not read members.csv — file may not exist yet.");
        }
    }
    // SAVE MEMBERS TO CSV
    private void saveMembersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Member m : members.values()) {
                bw.write(
                        m.getId() + "," +
                        m.getName() + "," +
                        m.getStreet() + "," +
                        m.getCity() + "," +
                        m.getState() + "," +
                        m.getZip() + "," +
                        m.getEmail() + "," +
                        m.getPhone() + "," +
                        m.getStatus() + "," +
                        m.getBalance()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving members.csv");
        }
    }
    // MEMBER FUNCTIONS
    public Member getMember(String id) {
        return members.get(id);
    }

    public void addMember(Member m) {
        members.put(m.getId(), m);
        saveMembersToCSV();
    }

    public void updateMember(Member m) {
        members.put(m.getId(), m);
        saveMembersToCSV();
    }

    public void removeMember(String id) {
        members.remove(id);
        saveMembersToCSV();
    }

    public boolean memberExists(String id) {
        return members.containsKey(id);
    }
}

