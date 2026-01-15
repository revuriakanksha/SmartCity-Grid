package smartcity.app;

import smartcity.buildings.SmartUnit;
import smartcity.io.ReportWriter;
import smartcity.threads.LoadBalancer;
import smartcity.exceptions.OverloadException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Demo application that assembles SmartUnits, simulates random energy usage,
 * writes a report, and launches load balancer threads.
 */
public class Main {
    public static void main(String[] args) {
        // Configuration
        double overloadThreshold = 25.0; // kWh per unit (example threshold)
        int daysToSimulate = 1; // keep small for demo
        Random rnd = new Random();

        // Create some smart units (multiple buildings)
        SmartUnit[] units = new SmartUnit[] {
            new SmartUnit("12A Maple St", 3, new String[] {"Alice", "Bob", "Carol"}),
            new SmartUnit("88B Oak Ave", 2, new String[] {"Dave", "Eva"}),
            new SmartUnit("101 Central Blvd", 4, new String[] {"Fay", "George", "Hank", "Ivy"})
        };

        StringBuilder report = new StringBuilder();
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        report.append("SmartCity Grid Daily Report\n");
        report.append("Generated at: ").append(LocalDateTime.now().format(dateFmt)).append("\n\n");

        // Connect all units to grid
        for (SmartUnit su : units) {
            su.connectToGrid();
        }

        // Simulate daily logs
        for (int day = 0; day < daysToSimulate; day++) {
            report.append("Day ").append(day + 1).append(":\n");
            for (SmartUnit su : units) {
                // Random usage per resident (0.1 - 5.0 kWh)
                String[] residents = su.getResidents();
                for (int i = 0; i < residents.length; i++) {
                    double usage = 0.1 + rnd.nextDouble() * 5.0;
                    su.logUsage(i, usage);
                }
                double total = su.totalUsage();
                report.append(String.format("Unit %s total: %.3f kWh%n", su.getAddress(), total));

                // Transmit usage
                su.transmitUsage();

                // Check overload with try-catch
                try {
                    su.checkForOverload(overloadThreshold);
                } catch (OverloadException e) {
                    report.append("!!! OVERLOAD WARNING: ").append(e.getMessage()).append("\n");
                    System.out.println("Caught OverloadException: " + e.getMessage());
                } finally {
                    // Optionally run any cleanup/logging
                }
            }
            report.append("\n");
        }

        // Disconnect a unit to show interface usage
        units[0].disconnectFromGrid();

        // Create timestamped report file name
        String filename = ReportWriter.timestampedFilename("smartcity_report");
        ReportWriter.writeReport(filename, report.toString());

        // Launch load balancers for each building (simulate different zone loads)
        LoadBalancer[] balancers = new LoadBalancer[units.length];
        for (int i = 0; i < units.length; i++) {
            double zoneLoad = units[i].totalUsage(); // use total usage as load measure
            balancers[i] = new LoadBalancer("Zone-" + (i+1), zoneLoad);
            balancers[i].start();
        }

        // Wait for all balancers to finish (.join())
        for (LoadBalancer lb : balancers) {
            try {
                lb.join();
            } catch (InterruptedException e) {
                System.out.println("Main interrupted while waiting for balancers.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Simulation complete. Report: " + filename);
    }
}