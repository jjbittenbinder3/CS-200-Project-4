package data;

import model.Member;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class MemberService {

    private HashMap<Integer, Member> members = new HashMap<>();
    private final String FILE_NAME = "members.csv";

    public MemberService() {
        loadMembersFromCSV();
    }

    private void loadMembersFromCSV() {
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

                Member m = new Member(name, id, password, suspended);
                m.setAddress(address, city, state, zip);

                members.put(id, m);
            }

        } catch (IOException e) {
            System.out.println("members.csv not found or unreadable.");
        }
    }

    private void saveMembersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Member m : members.values()) {
                bw.write(
                    m.getID() + "," +
                    m.getName() + "," +
                    m.getPassword() + "," +
                    m.getAddress() + "," +
                    m.getCity() + "," +
                    m.getState() + "," +
                    m.getZip() + "," +
                    m.getSuspended()
                );
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving members.csv");
        }
    }

    public Member getMember(int id) {
        return members.get(id);
    }

    public void addMember(Member m) {
        members.put(m.getID(), m);
        saveMembersToCSV();
    }

    public void updateMember(Member m) {
        members.put(m.getID(), m);
        saveMembersToCSV();
    }

    public void removeMember(int id) {
        members.remove(id);
        saveMembersToCSV();
    }

    public boolean memberExists(int id) {
        return members.containsKey(id);
    }
}
