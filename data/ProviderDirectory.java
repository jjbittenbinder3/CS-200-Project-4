/*
Author: Lucas Cottrell
*/

package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * ProviderDirectory manages a collection of services (name, code, fee).
 * You can add services manually, look up a name by code, and write the
 * directory to a text file sorted alphabetically by service name.
 */
public class ProviderDirectory {

    private final List<Service> services = new ArrayList<>();
    private final DecimalFormat feeFormat = new DecimalFormat("#0.00");

    // Initialize directory from services.csv if present, otherwise fall back to defaults
    {
        Path db = Path.of("data/services.csv");
        if (Files.exists(db)) {
            try (java.util.stream.Stream<String> stream = Files.lines(db, StandardCharsets.UTF_8)) {
                stream.map(String::trim)
                      .filter(l -> !l.isEmpty() && !l.startsWith("#"))
                      .forEach(l -> {
                          String[] parts = l.split(",", 3); // format: code,name,fee
                          if (parts.length < 3) {
                              System.err.println("Skipping malformed line: " + l);
                              return;
                          }
                          String codeStr = parts[0].trim();
                          String name = parts[1].trim();
                          String feeStr = parts[2].trim();
                          try {
                              int code = Integer.parseInt(codeStr);
                              double fee = Double.parseDouble(feeStr);
                              addService(name, code, fee);
                          } catch (NumberFormatException e) {
                              System.err.println("Skipping line with invalid number(s): " + l);
                          }
                      });
            } catch (IOException e) {
                System.err.println("Failed to read services.csv: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to find services.csv.");
        }
    }

    public static class Service {
        private final String name;
        private final int code;
        private final double fee;

        public Service(String name, int code, double fee) {
            this.name = name;
            this.code = code;
            this.fee = fee;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        public double getFee() {
            return fee;
        }

        @Override
        public String toString() {
            return name + ", " + code + ", " + new DecimalFormat("#0.00").format(fee);
        }
    }

    /**
     * Add a service to the directory. You can call this repeatedly to fill services.
     */
    public void addService(String name, int code, double fee) {
        services.add(new Service(name, code, fee));
    }

    /**
     * Return the service name for a given code, or Optional.empty() if not found.
     */
    public String enterServiceCode(int code) {
        return services.stream()
                .filter(s -> s.getCode() == code)
                .map(Service::getName)
                .findFirst()
                .orElse("error");
    }
    /**
     * Return the fee for a given service code, or Optional.empty() if not found.
     */
    public Optional<Double> getServiceFee(int code) {
        return services.stream()
                       .filter(s -> s.getCode() == code)
                       .map(Service::getFee)
                       .findFirst();
    }

    /**
     * Write the directory to a text file at the given path.
     * Each line will contain: name, code, fee
     * Lines are sorted alphabetically by service name (case-insensitive).
     *
     * Example line:
     *   Basic Consultation, 101, 75.00
     *
     * Throws IOException on failure.
     */
    public void sendDirectoryRequest(Path outputPath) throws IOException {
        List<Service> sorted = new ArrayList<>(services);
        sorted.sort(Comparator.comparing(Service::getName, String.CASE_INSENSITIVE_ORDER));

        try (BufferedWriter w = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
            for (Service s : sorted) {
                w.write(s.getName() + ", " + s.getCode() + ", $" + feeFormat.format(s.getFee()));
                w.newLine();
            }
        }
    }

    // Optional helper to get all services (immutable copy)
    public List<Service> getAllServices() {
        return new ArrayList<>(services);
    }
    
/* Example main (remove or adapt as needed) demonstrating manual population and file write:
    public static void main(String[] args) {
        ProviderDirectory dir = new ProviderDirectory();

        // Lookup example:
        System.out.println(dir.enterServiceCode(000000));

        // Write to file example (change path as needed):
        try {
            dir.sendDirectoryRequest(Path.of("provider_directory.txt"));
            System.out.println("Written provider_directory.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}  */
}

