package smartcity.buildings;

import java.util.Arrays;

public class ResidentialUnit extends Building {
    private String[] residents;
    public double[] energyUsage; // package-public for demonstration; could be private with accessors

    public ResidentialUnit(String address, int unitCount, String[] residents) {
        super(address, unitCount);
        this.residents = residents == null ? new String[0] : residents.clone();
        this.energyUsage = new double[this.residents.length];
    }

    // log usage to a resident index (thread-safe)
    public synchronized void logUsage(int index, double kWh) {
        if (index < 0 || index >= energyUsage.length) {
            throw new IndexOutOfBoundsException("Resident index out of range");
        }
        energyUsage[index] += kWh;
    }

    public void printUsage() {
        for (int i = 0; i < residents.length; i++) {
            System.out.printf("%s: %.3f kWh%n", residents[i], energyUsage[i]);
        }
    }

    public double totalUsage() {
        return Arrays.stream(energyUsage).sum();
    }

    public String[] getResidents() {
        return residents.clone();
    }
}