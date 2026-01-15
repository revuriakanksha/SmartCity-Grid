package smartcity.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportWriter {

    /**
     * Write content to filename. Overwrites if exists.
     */
    public static void writeReport(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("Report written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }

    /**
     * Utility: generates a timestamped filename using pattern: report_yyyy_MM_dd_HH_mm_ss.txt
     */
    public static String timestampedFilename(String prefix) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        String ts = LocalDateTime.now().format(fmt);
        return String.format("%s_%s.txt", prefix, ts);
    }
}
