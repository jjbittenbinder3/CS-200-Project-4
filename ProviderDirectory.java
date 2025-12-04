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

    // Full Directory of services
    {
        addService("Session with Dietitian", 598470, 85.00);
        addService("Aerobics Exercise Session", 883948, 65.00);
        addService("Group Counseling", 402782, 40.00);
        addService("Session with Internist", 349287, 85.00);
        addService("Session with Health Care", 126159, 110.00);
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
     * Return the service name for a given code, or Optional.empty() if not found.
     */
    public Optional<String> getServiceName(int code) {
        return services.stream()
                .filter(s -> s.getCode() == code)
                .map(Service::getName)
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
    public void writeDirectoryToFile(Path outputPath) throws IOException {
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
        System.out.println(dir.getServiceName(598470).orElse("Not found"));

        // Write to file example (change path as needed):
        try {
            dir.writeDirectoryToFile(Path.of("provider_directory.txt"));
            System.out.println("Written provider_directory.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} */
}